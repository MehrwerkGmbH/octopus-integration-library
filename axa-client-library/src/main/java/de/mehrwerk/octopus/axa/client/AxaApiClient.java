package de.mehrwerk.octopus.axa.client;

import de.mehrwerk.octopus.client.core.security.TokenInterceptor;
import org.springframework.web.client.RestClient;

@SuppressWarnings("unused")
public class AxaApiClient {

    private final RestClient restClient;

    public AxaApiClient(
            String rootUri,
            TokenInterceptor tokenInterceptor) {

        this.restClient = RestClient.builder()
                .requestInterceptor(tokenInterceptor)
                .baseUrl(rootUri)
                .build();
    }
}
