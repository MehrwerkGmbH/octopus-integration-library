package de.mehrwerk.octopus.axa.exception;

import de.mehrwerk.octopus.axa.model.error.ErrorDto;
import lombok.Getter;
import org.springframework.http.HttpStatusCode;

/**
 * Custom exception class for handling API errors from the AXA service.
 */
@Getter
public class AxaApiException extends RuntimeException {
    private final HttpStatusCode statusCode;
    private final ErrorDto errorResponse;
    private final String rawError;

    public AxaApiException(String message, HttpStatusCode statusCode, ErrorDto errorResponse, String rawError) {
        super(message);
        this.statusCode = statusCode;
        this.errorResponse = errorResponse;
        this.rawError = rawError;
    }
}
