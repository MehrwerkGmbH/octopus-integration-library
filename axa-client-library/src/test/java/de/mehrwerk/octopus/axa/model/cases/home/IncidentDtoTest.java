package de.mehrwerk.octopus.axa.model.cases.home;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class IncidentDtoTest {

    private Validator validator;
    private CauseDto validCause;
    private LocationDto validLocation;
    private OffsetDateTime validDateTime;

    @BeforeEach
    void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        validCause = new CauseDto();
        validLocation = new LocationDto();
        validDateTime = OffsetDateTime.now();
    }

    @Test
    void testCause_Valid() {
        IncidentDto dto = new IncidentDto()
                .setCause(validCause);
        Set<ConstraintViolation<IncidentDto>> violations = validator.validateProperty(dto, "cause");
        assertThat(violations).isEmpty();
    }

    @Test
    void testCause_Null() {
        IncidentDto dto = new IncidentDto()
                .setCause(null);
        Set<ConstraintViolation<IncidentDto>> violations = validator.validateProperty(dto, "cause");
        assertThat(violations).isNotEmpty();
    }

    @Test
    void testOccurredAt_Valid() {
        IncidentDto dto = new IncidentDto()
                .setOccurredAt(validDateTime);
        Set<ConstraintViolation<IncidentDto>> violations = validator.validateProperty(dto, "occurredAt");
        assertThat(violations).isEmpty();
    }

    @Test
    void testOccurredAt_Null() {
        IncidentDto dto = new IncidentDto()
                .setOccurredAt(null);
        Set<ConstraintViolation<IncidentDto>> violations = validator.validateProperty(dto, "occurredAt");
        assertThat(violations).isNotEmpty();
    }

    @Test
    void testLocation_Valid() {
        IncidentDto dto = new IncidentDto()
                .setLocation(validLocation);
        Set<ConstraintViolation<IncidentDto>> violations = validator.validateProperty(dto, "location");
        assertThat(violations).isEmpty();
    }

    @Test
    void testLocation_Null() {
        IncidentDto dto = new IncidentDto()
                .setLocation(null);
        Set<ConstraintViolation<IncidentDto>> violations = validator.validateProperty(dto, "location");
        assertThat(violations).isNotEmpty();
    }

    @Test
    void testDescription_Valid() {
        IncidentDto dto = new IncidentDto()
                .setDescription("Valid description");
        Set<ConstraintViolation<IncidentDto>> violations = validator.validateProperty(dto, "description");
        assertThat(violations).isEmpty();
    }

    @Test
    void testDescription_TooLong() {
        String longDescription = "D".repeat(2001);
        IncidentDto dto = new IncidentDto()
                .setDescription(longDescription);
        Set<ConstraintViolation<IncidentDto>> violations = validator.validateProperty(dto, "description");
        assertThat(violations).isNotEmpty();
    }

    @Test
    void testInsuranceAccountability_Valid() {
        IncidentDto dto = new IncidentDto()
                .setInsuranceAccountability(IncidentDto.InsuranceAccountability.ACCOUNTABLE);
        Set<ConstraintViolation<IncidentDto>> violations = validator.validateProperty(dto, "insuranceAccountability");
        assertThat(violations).isEmpty();
    }

    @Test
    void testIncidentDto_Valid() {
        IncidentDto dto = new IncidentDto()
                .setCause(validCause)
                .setOccurredAt(validDateTime)
                .setLocation(validLocation)
                .setDescription("Valid description")
                .setInsuranceAccountability(IncidentDto.InsuranceAccountability.ACCOUNTABLE);
        Set<ConstraintViolation<IncidentDto>> violations = validator.validate(dto);
        assertThat(violations.size()).isEqualTo(2);
    }

    @Test
    void testIncidentDto_MissingRequiredFields() {
        IncidentDto dto = new IncidentDto();
        Set<ConstraintViolation<IncidentDto>> violations = validator.validate(dto);
        assertThat(violations).hasSize(3);
        assertThat(violations).anyMatch(v -> v.getPropertyPath().toString().equals("cause"));
        assertThat(violations).anyMatch(v -> v.getPropertyPath().toString().equals("occurredAt"));
        assertThat(violations).anyMatch(v -> v.getPropertyPath().toString().equals("location"));
    }
}