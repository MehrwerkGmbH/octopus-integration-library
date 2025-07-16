package de.mehrwerk.octopus.axa.model.cases.home;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SpecialRequirementDtoTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void testHasRequestedCheckUp_ValidFalse() {
        SpecialRequirementDto dto = new SpecialRequirementDto().setHasRequestedCheckUp(false);
        Set<ConstraintViolation<SpecialRequirementDto>> violations = validator.validateProperty(dto, "hasRequestedCheckUp");
        assertThat(violations).isEmpty();
    }

    @Test
    void testHasRequestedCheckUp_Null() {
        SpecialRequirementDto dto = new SpecialRequirementDto().setHasRequestedCheckUp(null);
        Set<ConstraintViolation<SpecialRequirementDto>> violations = validator.validateProperty(dto, "hasRequestedCheckUp");
        assertThat(violations).isNotEmpty();
    }

    @Test
    void testSpecialRequirementDto_Valid() {
        SpecialRequirementDto dto = new SpecialRequirementDto().setHasRequestedCheckUp(true);
        Set<ConstraintViolation<SpecialRequirementDto>> violations = validator.validate(dto);
        assertThat(violations).isEmpty();
    }

    @Test
    void testSpecialRequirementDto_Invalid_Null() {
        SpecialRequirementDto dto = new SpecialRequirementDto().setHasRequestedCheckUp(null);
        Set<ConstraintViolation<SpecialRequirementDto>> violations = validator.validate(dto);
        assertThat(violations).anyMatch(v -> v.getPropertyPath().toString().equals("hasRequestedCheckUp"));
    }
}