package de.mehrwerk.octopus.axa.model.cases.home;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class PhoneDtoTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void testInternationalPrefix_Valid() {
        PhoneDto dto = new PhoneDto().setInternationalPrefix("0049").setNumber("123456");
        Set<ConstraintViolation<PhoneDto>> violations = validator.validate(dto);
        assertThat(violations).noneMatch(v -> v.getPropertyPath().toString().equals("internationalPrefix"));
    }

    @Test
    void testInternationalPrefix_TooLong() {
        PhoneDto dto = new PhoneDto().setInternationalPrefix("123456").setNumber("123456");
        Set<ConstraintViolation<PhoneDto>> violations = validator.validate(dto);
        assertThat(violations).anyMatch(v -> v.getPropertyPath().toString().equals("internationalPrefix"));
    }

    @Test
    void testNumber_Valid() {
        PhoneDto dto = new PhoneDto().setInternationalPrefix("0049").setNumber("1234567890");
        Set<ConstraintViolation<PhoneDto>> violations = validator.validate(dto);
        assertThat(violations).noneMatch(v -> v.getPropertyPath().toString().equals("number"));
    }

    @Test
    void testNumber_TooLong() {
        PhoneDto dto = new PhoneDto().setInternationalPrefix("0049").setNumber("1".repeat(21));
        Set<ConstraintViolation<PhoneDto>> violations = validator.validate(dto);
        assertThat(violations).anyMatch(v -> v.getPropertyPath().toString().equals("number"));
    }

    @Test
    void testNumber_Blank() {
        PhoneDto dto = new PhoneDto().setInternationalPrefix("0049").setNumber("   ");
        Set<ConstraintViolation<PhoneDto>> violations = validator.validate(dto);
        assertThat(violations).anyMatch(v -> v.getPropertyPath().toString().equals("number"));
    }

    @Test
    void testNumber_Null() {
        PhoneDto dto = new PhoneDto().setInternationalPrefix("0049").setNumber(null);
        Set<ConstraintViolation<PhoneDto>> violations = validator.validate(dto);
        assertThat(violations).anyMatch(v -> v.getPropertyPath().toString().equals("number"));
    }

    @Test
    void testPhoneDto_Valid() {
        PhoneDto dto = new PhoneDto().setInternationalPrefix("0049").setNumber("1234567890");
        Set<ConstraintViolation<PhoneDto>> violations = validator.validate(dto);
        assertThat(violations).isEmpty();
    }

    @Test
    void testPhoneDto_Invalid_BothFields() {
        PhoneDto dto = new PhoneDto().setInternationalPrefix("123456").setNumber("");
        Set<ConstraintViolation<PhoneDto>> violations = validator.validate(dto);
        assertThat(violations).anyMatch(v -> v.getPropertyPath().toString().equals("internationalPrefix"));
        assertThat(violations).anyMatch(v -> v.getPropertyPath().toString().equals("number"));
    }

}