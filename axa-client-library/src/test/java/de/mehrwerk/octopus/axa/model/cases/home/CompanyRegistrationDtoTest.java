package de.mehrwerk.octopus.axa.model.cases.home;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class CompanyRegistrationDtoTest {

    private Validator validator;
    private CompanyRegistrationDto dto;

    @BeforeEach
    void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        dto = new CompanyRegistrationDto()
                .setRegistrationType(CompanyRegistrationDto.RegistrationType.SIRET)
                .setValue("12345678901234");
    }

    @Test
    void whenAllFieldsValid_thenNoViolations() {
        Set<ConstraintViolation<CompanyRegistrationDto>> violations = validator.validate(dto);
        assertThat(violations).isEmpty();
    }

    @ParameterizedTest
    @EnumSource(CompanyRegistrationDto.RegistrationType.class)
    void whenRegistrationTypeIsValid_thenNoViolations(CompanyRegistrationDto.RegistrationType type) {
        dto.setRegistrationType(type);
        Set<ConstraintViolation<CompanyRegistrationDto>> violations = validator.validate(dto);
        assertThat(violations).isEmpty();
    }

    @Test
    void whenRegistrationTypeExceedsMaxLength_thenViolation() {
        dto.setRegistrationType(null);
        dto.setValue("a".repeat(151));
        Set<ConstraintViolation<CompanyRegistrationDto>> violations = validator.validate(dto);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getPropertyPath().toString())
                .isEqualTo("registrationType");
    }

    @Test
    void whenValueIsNull_thenViolation() {
        dto.setValue(null);
        Set<ConstraintViolation<CompanyRegistrationDto>> violations = validator.validate(dto);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getPropertyPath().toString())
                .isEqualTo("value");
    }

    @Test
    void whenValueIsBlank_thenViolation() {
        dto.setValue("   ");
        Set<ConstraintViolation<CompanyRegistrationDto>> violations = validator.validate(dto);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getPropertyPath().toString())
                .isEqualTo("value");
    }

    @Test
    void whenValueIsEmpty_thenViolation() {
        dto.setValue("");
        Set<ConstraintViolation<CompanyRegistrationDto>> violations = validator.validate(dto);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getPropertyPath().toString())
                .isEqualTo("value");
    }

    @Test
    void whenValueExceedsMaxLength_thenViolation() {
        dto.setValue("a".repeat(21));
        Set<ConstraintViolation<CompanyRegistrationDto>> violations = validator.validate(dto);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getPropertyPath().toString())
                .isEqualTo("value");
    }

    @Test
    void testRegistrationTypeToString() {
        assertThat(CompanyRegistrationDto.RegistrationType.SIRET.toString())
                .isEqualTo("SIRET");
    }

    @Test
    void testEqualsAndHashCode() {
        CompanyRegistrationDto dto1 = new CompanyRegistrationDto()
                .setRegistrationType(CompanyRegistrationDto.RegistrationType.SIRET)
                .setValue("12345678901234");

        CompanyRegistrationDto dto2 = new CompanyRegistrationDto()
                .setRegistrationType(CompanyRegistrationDto.RegistrationType.SIRET)
                .setValue("12345678901234");

        CompanyRegistrationDto dto3 = new CompanyRegistrationDto()
                .setRegistrationType(CompanyRegistrationDto.RegistrationType.SIRET)
                .setValue("98765432109876");

        assertThat(dto1).isEqualTo(dto2);
        assertThat(dto1).hasSameHashCodeAs(dto2);
        assertThat(dto1).isNotEqualTo(dto3);
        assertThat(dto1.hashCode()).isNotEqualTo(dto3.hashCode());
    }

    @Test
    void testToString() {
        String expectedString = "CompanyRegistrationDto(registrationType=SIRET, value=12345678901234)";
        assertThat(dto.toString()).isEqualTo(expectedString);
    }
}