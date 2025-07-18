package de.mehrwerk.octopus.axa.model.cases.home;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class LocationDtoTest {

    private Validator validator;
    private LocationDto dto;
    private AddressDto validAddress;

    @BeforeEach
    void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        validAddress = new AddressDto()
                .setStreetAddress("Test Street 123");
        dto = new LocationDto()
                .setAddress(validAddress)
                .setAdditionalInformation("Additional info");
    }

    @Test
    void whenAllFieldsValid_thenNoViolations() {
        Set<ConstraintViolation<LocationDto>> violations = validator.validate(dto);
        assertThat(violations).isEmpty();
    }

    @Test
    void whenAddressIsNull_thenViolation() {
        dto.setAddress(null);
        Set<ConstraintViolation<LocationDto>> violations = validator.validate(dto);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getPropertyPath().toString()).isEqualTo("address");
    }

    @Test
    void whenAddressIsInvalid_thenViolation() {
        AddressDto invalidAddress = new AddressDto(); // missing required streetAddress
        dto.setAddress(invalidAddress);

        Set<ConstraintViolation<LocationDto>> violations = validator.validate(dto);
        assertThat(violations).isNotEmpty();
        assertThat(violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().startsWith("address")))
                .isTrue();
    }

    @Test
    void whenAdditionalInformationIsNull_thenNoViolation() {
        dto.setAdditionalInformation(null);
        Set<ConstraintViolation<LocationDto>> violations = validator.validate(dto);
        assertThat(violations).isEmpty();
    }

    @Test
    void whenAdditionalInformationExceedsMaxLength_thenViolation() {
        dto.setAdditionalInformation("a".repeat(2001));
        Set<ConstraintViolation<LocationDto>> violations = validator.validate(dto);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getPropertyPath().toString())
                .isEqualTo("additionalInformation");
    }

    @Test
    void whenAdditionalInformationIsValidLength_thenNoViolation() {
        dto.setAdditionalInformation("a".repeat(2000));
        Set<ConstraintViolation<LocationDto>> violations = validator.validate(dto);
        assertThat(violations).isEmpty();
    }
}