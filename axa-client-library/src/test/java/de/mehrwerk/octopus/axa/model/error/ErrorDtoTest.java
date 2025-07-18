package de.mehrwerk.octopus.axa.model.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class ErrorDtoTest {

    private Validator validator;
    private ErrorDto validDto;

    @BeforeEach
    void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        validDto = new ErrorDto();
        validDto.setError("test-error");
        validDto.setErrorDescription("test-description");
        validDto.setStatusCode("400");
    }

    @Test
    void testErrorDto_Valid() {
        Set<ConstraintViolation<ErrorDto>> violations = validator.validate(validDto);
        assertThat(violations).isEmpty();
    }

    @Test
    void testError_MaxSize() {
        ErrorDto dto = new ErrorDto();
        dto.setError("x".repeat(51));

        Set<ConstraintViolation<ErrorDto>> violations = validator.validate(dto);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getPropertyPath().toString()).isEqualTo("error");
    }

    @Test
    void testErrorDescription_MaxSize() {
        ErrorDto dto = new ErrorDto();
        dto.setErrorDescription("x".repeat(4001));

        Set<ConstraintViolation<ErrorDto>> violations = validator.validate(dto);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getPropertyPath().toString()).isEqualTo("errorDescription");
    }

    @Test
    void testStatusCode_MaxSize() {
        ErrorDto dto = new ErrorDto();
        dto.setStatusCode("1234");

        Set<ConstraintViolation<ErrorDto>> violations = validator.validate(dto);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getPropertyPath().toString()).isEqualTo("statusCode");
    }

    @Test
    void testJsonPropertyAnnotations() throws NoSuchFieldException {
        Class<ErrorDto> clazz = ErrorDto.class;

        JsonProperty errorAnnotation = clazz.getDeclaredField("error").getAnnotation(JsonProperty.class);
        JsonProperty descriptionAnnotation = clazz.getDeclaredField("errorDescription").getAnnotation(JsonProperty.class);
        JsonProperty statusAnnotation = clazz.getDeclaredField("statusCode").getAnnotation(JsonProperty.class);

        assertThat(errorAnnotation.value()).isEqualTo("error");
        assertThat(descriptionAnnotation.value()).isEqualTo("error_description");
        assertThat(statusAnnotation.value()).isEqualTo("status_code");
    }
}