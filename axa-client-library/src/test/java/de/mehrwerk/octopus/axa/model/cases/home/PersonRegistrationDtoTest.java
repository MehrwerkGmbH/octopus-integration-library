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

class PersonRegistrationDtoTest {

    private Validator validator;
    private PersonRegistrationDto dto;

    @BeforeEach
    void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        dto = new PersonRegistrationDto()
                .setRegistrationType(PersonRegistrationDto.RegistrationType.PASSPORT)
                .setValue("AB123456");
    }

    @Test
    void whenAllFieldsValid_thenNoViolations() {
        Set<ConstraintViolation<PersonRegistrationDto>> violations = validator.validate(dto);
        assertThat(violations).isEmpty();
    }

    @Test
    void whenRegistrationTypeIsNull_thenViolation() {
        dto.setRegistrationType(null);
        Set<ConstraintViolation<PersonRegistrationDto>> violations = validator.validate(dto);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getPropertyPath().toString())
                .isEqualTo("registrationType");
    }

    @ParameterizedTest
    @EnumSource(PersonRegistrationDto.RegistrationType.class)
    void whenRegistrationTypeIsValid_thenNoViolations(PersonRegistrationDto.RegistrationType type) {
        dto.setRegistrationType(type);
        Set<ConstraintViolation<PersonRegistrationDto>> violations = validator.validate(dto);
        assertThat(violations).isEmpty();
    }

    @Test
    void whenValueIsNull_thenViolation() {
        dto.setValue(null);
        Set<ConstraintViolation<PersonRegistrationDto>> violations = validator.validate(dto);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getPropertyPath().toString())
                .isEqualTo("value");
    }

    @Test
    void whenValueIsBlank_thenViolation() {
        dto.setValue("   ");
        Set<ConstraintViolation<PersonRegistrationDto>> violations = validator.validate(dto);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getPropertyPath().toString())
                .isEqualTo("value");
    }

    @Test
    void whenValueIsEmpty_thenViolation() {
        dto.setValue("");
        Set<ConstraintViolation<PersonRegistrationDto>> violations = validator.validate(dto);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getPropertyPath().toString())
                .isEqualTo("value");
    }

    @Test
    void whenValueExceedsMaxLength_thenViolation() {
        dto.setValue("a".repeat(21));
        Set<ConstraintViolation<PersonRegistrationDto>> violations = validator.validate(dto);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getPropertyPath().toString())
                .isEqualTo("value");
    }

    @Test
    void whenValueIsValidLength_thenNoViolation() {
        dto.setValue("a".repeat(20));
        Set<ConstraintViolation<PersonRegistrationDto>> violations = validator.validate(dto);
        assertThat(violations).isEmpty();
    }

    @Test
    void testRegistrationTypeToString() {
        assertThat(PersonRegistrationDto.RegistrationType.PASSPORT.toString())
                .isEqualTo("PASSPORT");
        assertThat(PersonRegistrationDto.RegistrationType.DRIVER_LICENSE.toString())
                .isEqualTo("DRIVER_LICENSE");
        assertThat(PersonRegistrationDto.RegistrationType.ID_CARD.toString())
                .isEqualTo("ID_CARD");
        assertThat(PersonRegistrationDto.RegistrationType.PHONE.toString())
                .isEqualTo("PHONE");
        assertThat(PersonRegistrationDto.RegistrationType.RESIDENCE_PERMIT.toString())
                .isEqualTo("RESIDENCE_PERMIT");
        assertThat(PersonRegistrationDto.RegistrationType.TAX_REGISTRATION.toString())
                .isEqualTo("TAX_REGISTRATION");
    }
}