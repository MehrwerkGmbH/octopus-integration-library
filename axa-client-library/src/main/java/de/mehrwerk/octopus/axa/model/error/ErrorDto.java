package de.mehrwerk.octopus.axa.model.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;
import lombok.Getter;

/**
 * Represents an error response from the AXA API.
 * <p>
 * This class is used to map the error response fields from the AXA API.
 * </p>
 * <p>
 * Followings are the official status codes.
 * 400 Bad Request, 401 Unauthorized, 403 Forbidden, 422 Unprocessable Entity and anything else
 */
@Getter
public class ErrorDto {
    /**
     * <p>Identifier of the error</p>
     */
    @JsonProperty("error")
    @Size(max = 50)
    public String error;
    /**
     * <p>Short and readable description of the error</p>
     */
    @JsonProperty("error_description")
    @Size(max = 4000)
    public String errorDescription;
    /**
     * <p>Status code of the error</p>
     * <p>
     * Followings are the official status codes.
     * 400 Bad Request, 401 Unauthorized, 403 Forbidden, 422 Unprocessable Entity and anything else
     * </p>
     */
    @Size(max = 3)
    @JsonProperty("status_code")
    public String statusCode;
}
