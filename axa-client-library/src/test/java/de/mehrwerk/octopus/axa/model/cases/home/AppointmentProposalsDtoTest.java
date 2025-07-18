package de.mehrwerk.octopus.axa.model.cases.home;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.OffsetDateTime;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class AppointmentProposalsDtoTest {

    private Validator validator;
    private AppointmentProposalsDto dto;
    private final OffsetDateTime validStartTime = OffsetDateTime.parse("2024-01-01T10:00:00+01:00");
    private final OffsetDateTime validEndTime = OffsetDateTime.parse("2024-01-01T11:00:00+01:00");

    @BeforeEach
    void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        dto = new AppointmentProposalsDto()
                .setStartAt(validStartTime)
                .setEndAt(validEndTime)
                .setPreferredOrder(1);
    }

    @Test
    void whenAllFieldsValid_thenNoViolations() {
        Set<ConstraintViolation<AppointmentProposalsDto>> violations = validator.validate(dto);
        assertThat(violations).isEmpty();
    }

    @Test
    void whenStartAtIsNull_thenViolation() {
        dto.setStartAt(null);
        Set<ConstraintViolation<AppointmentProposalsDto>> violations = validator.validate(dto);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getPropertyPath().toString()).isEqualTo("startAt");
    }

    @Test
    void whenEndAtIsNull_thenViolation() {
        dto.setEndAt(null);
        Set<ConstraintViolation<AppointmentProposalsDto>> violations = validator.validate(dto);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getPropertyPath().toString()).isEqualTo("endAt");
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 10, 20})
    void whenPreferredOrderIsValid_thenNoViolations(int order) {
        dto.setPreferredOrder(order);
        Set<ConstraintViolation<AppointmentProposalsDto>> violations = validator.validate(dto);
        assertThat(violations).isEmpty();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 21, 100})
    void whenPreferredOrderIsInvalid_thenViolation(int order) {
        dto.setPreferredOrder(order);
        Set<ConstraintViolation<AppointmentProposalsDto>> violations = validator.validate(dto);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getPropertyPath().toString()).isEqualTo("preferredOrder");
    }

    @Test
    void whenEndTimeIsBeforeStartTime_thenShouldAllowIt() {
        // Note: The current implementation doesn't validate this business rule
        dto.setStartAt(validEndTime);
        dto.setEndAt(validStartTime);
        Set<ConstraintViolation<AppointmentProposalsDto>> violations = validator.validate(dto);
        assertThat(violations).isEmpty();
    }

    @Test
    void whenPreferredOrderIsNull_thenNoViolation() {
        dto.setPreferredOrder(null);
        Set<ConstraintViolation<AppointmentProposalsDto>> violations = validator.validate(dto);
        assertThat(violations).isEmpty();
    }
}