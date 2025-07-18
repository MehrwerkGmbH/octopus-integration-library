package de.mehrwerk.octopus.axa.model.cases.home;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class PolicyHolderPersonalDtoTest {

    private Validator validator;
    private PolicyHolderPersonalDto dto;
    private PersonRegistrationDto validRegistration;

    @BeforeEach
    void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        validRegistration = new PersonRegistrationDto()
                .setRegistrationType(PersonRegistrationDto.RegistrationType.PASSPORT)
                .setValue("AB123456");

        dto = new PolicyHolderPersonalDto()
                .setFirstName("John")
                .setLastName("Doe")
                .setAddressPostalCode("12345")
                .setIsPropertyOwner(true)
                .setPersonRegistration(validRegistration);
    }

    @Test
    void whenAllFieldsValid_thenNoViolations() {
        Set<ConstraintViolation<PolicyHolderPersonalDto>> violations = validator.validate(dto);
        assertThat(violations).isEmpty();
    }

    @Test
    void whenFirstNameExceedsMaxLength_thenViolation() {
        dto.setFirstName("a".repeat(101));
        Set<ConstraintViolation<PolicyHolderPersonalDto>> violations = validator.validate(dto);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getPropertyPath().toString()).isEqualTo("firstName");
    }

    @Test
    void whenLastNameExceedsMaxLength_thenViolation() {
        dto.setLastName("a".repeat(101));
        Set<ConstraintViolation<PolicyHolderPersonalDto>> violations = validator.validate(dto);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getPropertyPath().toString()).isEqualTo("lastName");
    }

    @Test
    void whenPostalCodeExceedsMaxLength_thenViolation() {
        dto.setAddressPostalCode("a".repeat(21));
        Set<ConstraintViolation<PolicyHolderPersonalDto>> violations = validator.validate(dto);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getPropertyPath().toString()).isEqualTo("addressPostalCode");
    }

    @Test
    void whenPersonRegistrationIsInvalid_thenViolation() {
        PersonRegistrationDto invalidRegistration = new PersonRegistrationDto(); // missing required fields
        dto.setPersonRegistration(invalidRegistration);

        Set<ConstraintViolation<PolicyHolderPersonalDto>> violations = validator.validate(dto);
        assertThat(violations).isNotEmpty();
        assertThat(violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().startsWith("personRegistration")))
                .isTrue();
    }

    @Test
    void whenOptionalFieldsAreNull_thenNoViolation() {
        dto.setFirstName(null);
        dto.setLastName(null);
        dto.setAddressPostalCode(null);
        dto.setIsPropertyOwner(null);
        dto.setPersonRegistration(null);

        Set<ConstraintViolation<PolicyHolderPersonalDto>> violations = validator.validate(dto);
        assertThat(violations).isEmpty();
    }
}