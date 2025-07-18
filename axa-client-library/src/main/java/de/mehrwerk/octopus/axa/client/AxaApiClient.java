package de.mehrwerk.octopus.axa.client;

import de.mehrwerk.octopus.axa.model.cases.home.HomeCaseRequestDto;
import de.mehrwerk.octopus.axa.model.cases.home.HomeCaseResponseDto;
import de.mehrwerk.octopus.axa.security.AxaServiceUserTokenInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestClient;

import java.net.http.HttpClient;
import java.time.Duration;

/**
 * Client for the AXA API.
 * This client is used to interact with the AXA API.
 * Authentication is handled by the AxaServiceUserTokenInterceptor and AxaTokenClient.
 * Axa has longer waiting time. Therefore, timeout is set to 30 seconds for connection and 20 seconds for reading.
 */
@Slf4j
@Service
public class AxaApiClient {
    private final RestClient restClient;

    public AxaApiClient(
            @Value("${axa.api.api-base-url}") String apiBaseUrl,
            @Value("${axa.api.connect-timeout}") Integer connectTimeout,
            @Value("${axa.api.read-timeout}") Integer readTimeout,
            AxaServiceUserTokenInterceptor tokenInterceptor) {

        Assert.notNull(apiBaseUrl, "Api base url must not be null");
        Assert.notNull(connectTimeout, "Connection timeout must not be null");
        Assert.notNull(readTimeout, "Read timeout must not be null");

        HttpClient httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(connectTimeout))
                .build();

        JdkClientHttpRequestFactory requestFactory = new JdkClientHttpRequestFactory(httpClient);
        requestFactory.setReadTimeout(Duration.ofSeconds(readTimeout));

        this.restClient = RestClient.builder()
                .baseUrl(apiBaseUrl)
                .requestInterceptor(tokenInterceptor)
                .requestFactory(requestFactory)
                .build();
    }

    /**
     * Creates a new home case in the AXA API.
     *
     * @param userRequest the request object containing the details of the home case to be created
     * @return the response object containing the details of the created home case
     */
    public HomeCaseResponseDto createHomeCase(HomeCaseRequestDto userRequest) {
        return restClient.post()
                .uri("/assistance/home/v4/cases")
                .body(userRequest)
                .retrieve()
                .body(HomeCaseResponseDto.class);
    }
}
