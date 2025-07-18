package de.mehrwerk.octopus.axa.model.cases.home;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AddressDtoTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void testValidDto() {
        AddressDto dto = new AddressDto()
                .setStreetAddress("Test address");

        Set<ConstraintViolation<AddressDto>> violations = validator.validate(dto);

        assertTrue(violations.isEmpty(), "Should have no violations");
    }

    @Test
    void testInvalidDto_NullAddress() {
        AddressDto dto = new AddressDto();

        Set<ConstraintViolation<AddressDto>> violations = validator.validate(dto);

        assertFalse(violations.isEmpty(), "Should have violations");
        assertEquals(1, violations.size());
    }

    @Test
    void testStreetAddress_Valid() {
        AddressDto dto = new AddressDto()
                .setStreetAddress("Valid Street 123");

        Set<ConstraintViolation<AddressDto>> violations = validator.validate(dto);

        assertTrue(violations.stream().noneMatch(v -> v.getPropertyPath().toString().equals("streetAddress")));
    }

    @Test
    void testStreetAddress_Blank() {
        AddressDto dto = new AddressDto()
                .setStreetAddress("");

        Set<ConstraintViolation<AddressDto>> violations = validator.validate(dto);

        assertTrue(violations.stream().anyMatch(v ->
                v.getPropertyPath().toString().equals("streetAddress") &&
                        v.getMessage().contains("blank")));
    }

    @Test
    void testStreetAddress_TooLong() {
        AddressDto dto = new AddressDto()
                .setStreetAddress("a".repeat(256));

        Set<ConstraintViolation<AddressDto>> violations = validator.validate(dto);

        assertTrue(violations.stream().anyMatch(v ->
                v.getPropertyPath().toString().equals("streetAddress") &&
                        v.getMessage().contains("size")));
    }

    @Test
    void testCountry_InvalidPattern() {
        AddressDto dto = new AddressDto()
                .setStreetAddress("Valid Street")
                .setCountry("usa");

        Set<ConstraintViolation<AddressDto>> violations = validator.validate(dto);

        assertTrue(violations.stream().anyMatch(v ->
                v.getPropertyPath().toString().equals("country") &&
                        v.getMessage().contains("uppercase letters")));
    }

    @Test
    void testCountry_Valid() {
        AddressDto dto = new AddressDto()
                .setStreetAddress("Valid Street")
                .setCountry("US");

        Set<ConstraintViolation<AddressDto>> violations = validator.validate(dto);

        assertTrue(violations.stream().noneMatch(v -> v.getPropertyPath().toString().equals("country")));
    }

    @Test
    void testPostalCode_Valid() {
        AddressDto dto = new AddressDto()
                .setStreetAddress("Valid Street")
                .setPostalCode("12345");
        Set<ConstraintViolation<AddressDto>> violations = validator.validate(dto);
        assertTrue(violations.stream().noneMatch(v -> v.getPropertyPath().toString().equals("postalCode")));
    }

    @Test
    void testPostalCode_TooLong() {
        AddressDto dto = new AddressDto()
                .setStreetAddress("Valid Street")
                .setPostalCode("1".repeat(21));
        Set<ConstraintViolation<AddressDto>> violations = validator.validate(dto);
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("postalCode")));
    }

    @Test
    void testLocality_Valid() {
        AddressDto dto = new AddressDto()
                .setStreetAddress("Valid Street")
                .setLocality("Some City");
        Set<ConstraintViolation<AddressDto>> violations = validator.validate(dto);
        assertTrue(violations.stream().noneMatch(v -> v.getPropertyPath().toString().equals("locality")));
    }

    @Test
    void testLocality_TooLong() {
        AddressDto dto = new AddressDto()
                .setStreetAddress("Valid Street")
                .setLocality("a".repeat(101));
        Set<ConstraintViolation<AddressDto>> violations = validator.validate(dto);
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("locality")));
    }

    @Test
    void testState_Valid() {
        AddressDto dto = new AddressDto()
                .setStreetAddress("Valid Street")
                .setState("Some State");
        Set<ConstraintViolation<AddressDto>> violations = validator.validate(dto);
        assertTrue(violations.stream().noneMatch(v -> v.getPropertyPath().toString().equals("state")));
    }

    @Test
    void testState_TooLong() {
        AddressDto dto = new AddressDto()
                .setStreetAddress("Valid Street")
                .setState("a".repeat(101));
        Set<ConstraintViolation<AddressDto>> violations = validator.validate(dto);
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("state")));
    }

    @Test
    void testSubdivision_Valid() {
        AddressDto dto = new AddressDto()
                .setStreetAddress("Valid Street")
                .setSubdivision("Subdivision Name");
        Set<ConstraintViolation<AddressDto>> violations = validator.validate(dto);
        assertTrue(violations.stream().noneMatch(v -> v.getPropertyPath().toString().equals("subdivision")));
    }

    @Test
    void testSubdivision_TooLong() {
        AddressDto dto = new AddressDto()
                .setStreetAddress("Valid Street")
                .setSubdivision("a".repeat(101));
        Set<ConstraintViolation<AddressDto>> violations = validator.validate(dto);
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("subdivision")));
    }
}