package de.mehrwerk.octopus.axa.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.mehrwerk.octopus.axa.client.AxaTokenClient;
import de.mehrwerk.octopus.axa.exception.AxaApiException;
import de.mehrwerk.octopus.axa.model.error.ErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * Interceptor for adding authentication headers to requests and handling errors
 * for the AXA service user token.
 */
@Slf4j
public class AxaServiceUserTokenInterceptor implements ClientHttpRequestInterceptor {
    private final AxaTokenClient tokenClient;
    private final ObjectMapper objectMapper;

    private static final Set<HttpStatusCode> ERROR_STATUS_CODES = Set.of(
            HttpStatus.BAD_REQUEST,
            HttpStatus.UNAUTHORIZED,
            HttpStatus.FORBIDDEN,
            HttpStatus.UNPROCESSABLE_ENTITY,
            HttpStatus.INTERNAL_SERVER_ERROR,
            HttpStatus.SERVICE_UNAVAILABLE);

    public AxaServiceUserTokenInterceptor(AxaTokenClient tokenClient, ObjectMapper objectMapper) {
        Assert.notNull(tokenClient, "TokenService must not be null");
        Assert.notNull(objectMapper, "ObjectMapper must not be null");

        this.tokenClient = tokenClient;
        this.objectMapper = objectMapper;
    }

    /**
     * Adds default headers to the request if they are not already set.
     *
     * @param request the HttpRequest to which headers will be added
     */
    private void addDefaultHeaders(HttpRequest request) {
        HttpHeaders headers = request.getHeaders();

        if (!headers.containsKey(HttpHeaders.CONTENT_TYPE)) {
            headers.setContentType(MediaType.APPLICATION_JSON);
        }

        if (!headers.containsKey(HttpHeaders.ACCEPT)) {
            headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        }

        headers.set("x-environment", tokenClient.getXEnvironment());
        headers.set("x-tenant-id", tokenClient.getXTenantId());
    }

    /**
     * Handles error responses from the AXA API.
     *
     * @param request  the HttpRequest that caused the error
     * @param response the ClientHttpResponse containing the error details
     * @throws IOException if an error occurs while reading the response body
     */
    private void handleErrorResponse(HttpRequest request, ClientHttpResponse response)
            throws IOException {

        String responseBody = "";
        try {
            responseBody = new String(response.getBody().readAllBytes());
        } catch (Exception e) {
            log.warn("Could not read error response body", e);
        }

        ErrorDto errorResponse = null;

        try {
            if (!responseBody.isEmpty()) {
                errorResponse = objectMapper.readValue(responseBody, ErrorDto.class);
                log.debug("Parsed error response: {}", errorResponse);
            }
        } catch (Exception e) {
            log.warn("Could not parse error response body. Raw response is: {}", responseBody);
            log.debug("Parse error details", e);
        }

        String errorMessage = String.format(
                "AXA API returned error status %s for request %s %s",
                response.getStatusCode(),
                request.getMethod(),
                request.getURI()
        );

        if (errorResponse != null && errorResponse.getErrorDescription() != null) {
            errorMessage += ". Error: " + errorResponse.getErrorDescription();
        }

        log.error(errorMessage);

        if (response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
            // Token might be expired, invalidate cache
            tokenClient.invalidateToken();
            log.warn("Token expired or invalid, invalidated cache");
        }

        throw new AxaApiException(errorMessage, response.getStatusCode(), errorResponse, responseBody);
    }

    /**
     * Intercepts the HTTP request to add authentication headers and handle errors.
     *
     * @param request   the HttpRequest to intercept
     * @param body      the body of the request
     * @param execution the execution context for the request
     * @return the ClientHttpResponse from the execution
     */
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) {
        addDefaultHeaders(request);

        String token = tokenClient.getToken();

        if (token != null && !token.isEmpty()) {
            request.getHeaders().setBearerAuth(token);
        }

        log.debug("Executing request: {} {}", request.getMethod(), request.getURI());

        try {
            ClientHttpResponse response = execution.execute(request, body);

            if (ERROR_STATUS_CODES.contains(response.getStatusCode())) {
                handleErrorResponse(request, response);
            }

            log.debug("Request completed successfully with status: {}", response.getStatusCode());
            return response;

        } catch (IOException e) {
            String message = String.format(
                    "Failed to execute request to URI: %s. Error: %s",
                    request.getURI(),
                    e.getMessage()
            );
            log.error(message, e);
            throw new AxaApiException(message, null, null, null);
        }
    }
}
