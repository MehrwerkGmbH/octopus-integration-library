package de.mehrwerk.octopus.axa.model;

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
    private String accessToken;
    /**
     * <p>Type of the token, typically "Bearer"</p>
     */
    private String tokenType;
    /**
     * <p>Expiration time of the token in seconds</p>
     */
    private Long expiresIn;
}
