package de.mehrwerk.octopus.axa.model.cases.home;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class CauseDtoTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void testCode_Valid() {
        CauseDto dto = new CauseDto().setCode("VALID_CODE");
        Set<ConstraintViolation<CauseDto>> violations = validator.validateProperty(dto, "code");
        assertThat(violations).isEmpty();
    }

    @Test
    void testCode_Blank() {
        CauseDto dto = new CauseDto().setCode("   ");
        Set<ConstraintViolation<CauseDto>> violations = validator.validateProperty(dto, "code");
        assertThat(violations).isNotEmpty();
    }

    @Test
    void testCode_Null() {
        CauseDto dto = new CauseDto().setCode(null);
        Set<ConstraintViolation<CauseDto>> violations = validator.validateProperty(dto, "code");
        assertThat(violations).isNotEmpty();
    }

    @Test
    void testCode_TooLong() {
        CauseDto dto = new CauseDto().setCode("a".repeat(101));
        Set<ConstraintViolation<CauseDto>> violations = validator.validateProperty(dto, "code");
        assertThat(violations).isNotEmpty();
    }

    @Test
    void testDescription_Valid() {
        CauseDto dto = new CauseDto().setDescription("A valid description");
        Set<ConstraintViolation<CauseDto>> violations = validator.validateProperty(dto, "description");
        assertThat(violations).isEmpty();
    }

    @Test
    void testDescription_Null() {
        CauseDto dto = new CauseDto().setDescription(null);
        Set<ConstraintViolation<CauseDto>> violations = validator.validateProperty(dto, "description");
        assertThat(violations).isEmpty();
    }

    @Test
    void testDescription_TooLong() {
        CauseDto dto = new CauseDto().setDescription("a".repeat(256));
        Set<ConstraintViolation<CauseDto>> violations = validator.validateProperty(dto, "description");
        assertThat(violations).isNotEmpty();
    }

    @Test
    void testCauseDto_Valid() {
        CauseDto dto = new CauseDto()
                .setCode("CODE")
                .setDescription("Some description");
        Set<ConstraintViolation<CauseDto>> violations = validator.validate(dto);
        assertThat(violations).isEmpty();
    }

    @Test
    void testCauseDto_Invalid_BothFields() {
        CauseDto dto = new CauseDto()
                .setCode("")
                .setDescription("a".repeat(256));
        Set<ConstraintViolation<CauseDto>> violations = validator.validate(dto);
        assertThat(violations).anyMatch(v -> v.getPropertyPath().toString().equals("code"));
        assertThat(violations).anyMatch(v -> v.getPropertyPath().toString().equals("description"));
    }
}