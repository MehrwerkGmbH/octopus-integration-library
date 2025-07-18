package de.mehrwerk.octopus.axa.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a response containing JWT token information.
 * <p>
 * This class is used to encapsulate the JWT token response from the AXA API.
 * </p>
 */
@Getter
@Setter
@NoArgsConstructor
public class JwtTokenResponse {
    /**
     * <p>Access token for the JWT</p>
     */
    @JsonProperty("access_token")
    private String accessToken;
    /**
     * <p>Type of the token, typically "Bearer"</p>
     */
    @JsonProperty("token_type")
    private String tokenType;
    /**
     * <p>Expiration time of the token in seconds</p>
     */
    @JsonProperty("expires_in")
    private Long expiresIn;
    /**
     * <p>Scope of the token, defining the permissions granted</p>
     */
    @JsonProperty("scope")
    private String scope;
}
