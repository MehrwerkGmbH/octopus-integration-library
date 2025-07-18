package de.mehrwerk.octopus.axa.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class JwtTokenResponseTest {

    @Test
    void testJwtTokenResponse_AllFields() {
        JwtTokenResponse response = new JwtTokenResponse();
        response.setAccessToken("test-token");
        response.setTokenType("Bearer");
        response.setExpiresIn(3600L);
        response.setScope("api");

        assertThat(response.getAccessToken()).isEqualTo("test-token");
        assertThat(response.getTokenType()).isEqualTo("Bearer");
        assertThat(response.getExpiresIn()).isEqualTo(3600L);
        assertThat(response.getScope()).isEqualTo("api");
    }

    @Test
    void testJwtTokenResponse_NoArgsConstructor() {
        JwtTokenResponse response = new JwtTokenResponse();

        assertThat(response.getAccessToken()).isNull();
        assertThat(response.getTokenType()).isNull();
        assertThat(response.getExpiresIn()).isNull();
        assertThat(response.getScope()).isNull();
    }

    @SneakyThrows
    @Test
    void testJwtTokenResponse_JsonPropertyAnnotations() throws NoSuchFieldException {
        Class<JwtTokenResponse> clazz = JwtTokenResponse.class;

        try {
            assertThat(clazz.getDeclaredField("accessToken").getAnnotation(JsonProperty.class).value())
                    .isEqualTo("access_token");
        } catch (NoSuchFieldException | SecurityException e) {
            throw new RuntimeException(e);
        }
        assertThat(clazz.getDeclaredField("tokenType").getAnnotation(JsonProperty.class).value())
                .isEqualTo("token_type");
        try {
            assertThat(clazz.getDeclaredField("expiresIn").getAnnotation(JsonProperty.class).value())
                    .isEqualTo("expires_in");
        } catch (NoSuchFieldException | SecurityException e) {
            throw new RuntimeException(e);
        }
        assertThat(clazz.getDeclaredField("scope").getAnnotation(JsonProperty.class).value())
                .isEqualTo("scope");
    }
}