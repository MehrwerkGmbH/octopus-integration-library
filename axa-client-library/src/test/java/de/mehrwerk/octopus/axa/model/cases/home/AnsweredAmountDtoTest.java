package de.mehrwerk.octopus.axa.model.cases.home;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class AnsweredAmountDtoTest {

    private Validator validator;
    private AnsweredAmountDto dto;

    @BeforeEach
    void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        dto = new AnsweredAmountDto()
                .setValue(new BigDecimal("100.00"))
                .setCurrency("EUR");
    }

    @Test
    void whenAllFieldsValid_thenNoViolations() {
        Set<ConstraintViolation<AnsweredAmountDto>> violations = validator.validate(dto);
        assertThat(violations).isEmpty();
    }

    @Test
    void whenValueIsNull_thenViolation() {
        dto.setValue(null);
        Set<ConstraintViolation<AnsweredAmountDto>> violations = validator.validate(dto);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getPropertyPath().toString()).isEqualTo("value");
    }

    @ParameterizedTest
    @ValueSource(strings = {"100.00", "100.000"})
    void whenValueHasValidDecimals_thenNoViolations(String value) {
        dto.setValue(new BigDecimal(value));
        Set<ConstraintViolation<AnsweredAmountDto>> violations = validator.validate(dto);
        assertThat(violations).isEmpty();
    }

    @Test
    void whenValueHasInvalidDecimals_thenViolation() {
        dto.setValue(new BigDecimal("100.0000"));
        Set<ConstraintViolation<AnsweredAmountDto>> violations = validator.validate(dto);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getPropertyPath().toString()).isEqualTo("value");
    }

    @Test
    void whenCurrencyIsNull_thenViolation() {
        dto.setCurrency(null);
        Set<ConstraintViolation<AnsweredAmountDto>> violations = validator.validate(dto);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getPropertyPath().toString()).isEqualTo("currency");
    }

    @Test
    void whenCurrencyIsEmpty_thenViolation() {
        dto.setCurrency("");
        Set<ConstraintViolation<AnsweredAmountDto>> violations = validator.validate(dto);
        assertThat(violations).hasSize(2);
        assertThat(violations.iterator().next().getPropertyPath().toString()).isEqualTo("currency");
    }

    @Test
    void whenCurrencyIsInvalidLength_thenViolation() {
        dto.setCurrency("EURO");
        Set<ConstraintViolation<AnsweredAmountDto>> violations = validator.validate(dto);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getPropertyPath().toString()).isEqualTo("currency");
    }

    @ParameterizedTest
    @ValueSource(strings = {"USD", "EUR", "GBP"})
    void whenCurrencyIsValid_thenNoViolations(String currency) {
        dto.setCurrency(currency);
        Set<ConstraintViolation<AnsweredAmountDto>> violations = validator.validate(dto);
        assertThat(violations).isEmpty();
    }
}