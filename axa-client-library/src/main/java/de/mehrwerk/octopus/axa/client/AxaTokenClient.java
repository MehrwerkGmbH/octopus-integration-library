package de.mehrwerk.octopus.axa.client;

import de.mehrwerk.octopus.axa.exception.AxaApiException;
import de.mehrwerk.octopus.axa.model.JwtTokenResponse;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestClient;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;

/**
 * Client for managing JWT tokens for AXA API authentication.
 * This client handles token retrieval, caching, and refreshing.
 * Environment variables has to be set in order to use this client:
 * - axa.api.username: Username for AXA API authentication
 * - axa.api.password: Password for AXA API authentication
 * - axa.api.token-endpoint: Endpoint for retrieving the JWT token
 * - axa.api.auth-base-url: Base URL for the AXA API authentication service
 * - axa.api.x-environment: Custom header for the AXA API environment
 * - axa.api.x-tenant-id: Custom header for the AXA API tenant ID
 */
@Slf4j
@Service
@Getter
public class AxaTokenClient {

    private final String username;
    private final String password;
    private final String tokenEndpoint;
    private final String xEnvironment;
    private final String xTenantId;
    private final Duration tokenRefreshBuffer;

    private final RestClient authRestClient;
    private String cachedToken;
    private Instant tokenExpiry;

    public AxaTokenClient(
            @Value("${axa.api.username}") String username,
            @Value("${axa.api.password}") String password,
            @Value("${axa.api.token-endpoint}") String tokenEndpoint,
            @Value("${axa.api.auth-base-url}") String authBaseUrl,
            @Value("${axa.api.x-environment}") String xEnvironment,
            @Value("${axa.api.x-tenant-id}") String xTenantId,
            @Value("${axa.api.token-refresh-buffer}") Integer tokenRefreshBuffer) {

        Assert.notNull(username, "Username must not be null");
        Assert.notNull(password, "Password must not be null");
        Assert.notNull(tokenEndpoint, "Token endpoint must not be null");
        Assert.notNull(authBaseUrl, "Auth base URL must not be null");
        Assert.notNull(xEnvironment, "X-Environment header must not be null");
        Assert.notNull(xTenantId, "X-Tenant-ID header must not be null");
        Assert.notNull(tokenRefreshBuffer, "Token refresh buffer must not be null");
        Assert.isTrue(tokenRefreshBuffer > 0, "Token refresh buffer must be greater than 0");

        this.username = username;
        this.password = password;
        this.tokenEndpoint = tokenEndpoint;
        this.xEnvironment = xEnvironment;
        this.xTenantId = xTenantId;
        this.tokenRefreshBuffer = Duration.ofMinutes(tokenRefreshBuffer);

        this.authRestClient = RestClient.builder()
                .baseUrl(authBaseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    /**
     * Retrieves the cached JWT token, refreshing it if necessary.
     * If the token is expired or not cached, it will be refreshed.
     *
     * @return the current JWT token
     */
    public String getToken() {
        if (isTokenExpired()) {
            refreshToken();
        }
        return cachedToken;
    }

    /**
     * Invalidates the cached JWT token.
     * This method clears the cached token and its expiry time,
     * forcing a refresh on the next call to getToken().
     */
    public void invalidateToken() {
        log.debug("Invalidating cached token");
        this.cachedToken = null;
        this.tokenExpiry = null;
    }

    /**
     * Checks if the cached token is expired or not available.
     * The token is considered expired if it is null, the expiry time is null,
     * or the current time is after the expiry time minus the refresh buffer.
     *
     * @return true if the token is expired, false otherwise
     */
    private boolean isTokenExpired() {
        return cachedToken == null ||
                tokenExpiry == null ||
                Instant.now().isAfter(tokenExpiry.minus(tokenRefreshBuffer));
    }

    /**
     * Refreshes the JWT token by making a request to the AXA API authentication endpoint.
     * The new token is cached and its expiry time is set.
     * If the request fails, an AxaApiException is thrown.
     */
    private void refreshToken() {
        try {
            log.debug("Refreshing JWT token for AXA API");

            Map<String, String> authRequest = Map.of(
                    "grant_type", "client_credentials"
            );

            JwtTokenResponse tokenResponse = authRestClient.post()
                    .uri(tokenEndpoint)
                    .headers(headers -> headers.setBasicAuth(username, password))
                    .body(authRequest)
                    .retrieve()
                    .onStatus(HttpStatusCode::isError, (request, response) -> {
                        String errorBody = new String(response.getBody().readAllBytes());
                        throw new AxaApiException(
                                "Failed to authenticate with AXA API",
                                response.getStatusCode(),
                                null,
                                errorBody
                        );
                    })
                    .body(JwtTokenResponse.class);

            if (tokenResponse == null || tokenResponse.getAccessToken() == null) {
                throw new AxaApiException("Invalid token response from AXA API", null, null, null);
            }

            this.cachedToken = tokenResponse.getAccessToken();
            this.tokenExpiry = Instant.now().plusSeconds(
                    tokenResponse.getExpiresIn() != null ? tokenResponse.getExpiresIn() : 3600
            );

            log.debug("Successfully refreshed JWT token, expires at: {}", tokenExpiry);

        } catch (Exception e) {
            log.error("Failed to refresh JWT token", e);
            if (e instanceof AxaApiException) {
                throw e;
            }
            throw new AxaApiException("Authentication failed", null, null, null);
        }
    }
}
