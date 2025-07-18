package de.mehrwerk.octopus.axa.model.cases.home;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class PolicyHolderCompanyDtoTest {

    private Validator validator;
    private PolicyHolderCompanyDto dto;
    private CompanyRegistrationDto validRegistration;

    @BeforeEach
    void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        validRegistration = new CompanyRegistrationDto()
                .setRegistrationType(CompanyRegistrationDto.RegistrationType.SIRET)
                .setValue("12345678901234");

        dto = new PolicyHolderCompanyDto()
                .setCompanyLegalName("Test Company GmbH")
                .setCompanyRegistration(validRegistration);
    }

    @Test
    void whenAllFieldsValid_thenNoViolations() {
        Set<ConstraintViolation<PolicyHolderCompanyDto>> violations = validator.validate(dto);
        assertThat(violations).isEmpty();
    }

    @Test
    void whenCompanyLegalNameIsNull_thenViolation() {
        dto.setCompanyLegalName(null);
        Set<ConstraintViolation<PolicyHolderCompanyDto>> violations = validator.validate(dto);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getPropertyPath().toString())
                .isEqualTo("companyLegalName");
    }

    @Test
    void whenCompanyLegalNameIsBlank_thenViolation() {
        dto.setCompanyLegalName("   ");
        Set<ConstraintViolation<PolicyHolderCompanyDto>> violations = validator.validate(dto);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getPropertyPath().toString())
                .isEqualTo("companyLegalName");
    }

    @Test
    void whenCompanyLegalNameExceedsMaxLength_thenViolation() {
        dto.setCompanyLegalName("a".repeat(151));
        Set<ConstraintViolation<PolicyHolderCompanyDto>> violations = validator.validate(dto);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getPropertyPath().toString())
                .isEqualTo("companyLegalName");
    }

    @Test
    void whenCompanyRegistrationIsNull_thenViolation() {
        dto.setCompanyRegistration(null);
        Set<ConstraintViolation<PolicyHolderCompanyDto>> violations = validator.validate(dto);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getPropertyPath().toString())
                .isEqualTo("companyRegistration");
    }

    @Test
    void whenCompanyRegistrationIsInvalid_thenViolation() {
        CompanyRegistrationDto invalidRegistration = new CompanyRegistrationDto(); // missing required fields
        dto.setCompanyRegistration(invalidRegistration);

        Set<ConstraintViolation<PolicyHolderCompanyDto>> violations = validator.validate(dto);
        assertThat(violations).isNotEmpty();
        assertThat(violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().startsWith("companyRegistration")))
                .isTrue();
    }
}