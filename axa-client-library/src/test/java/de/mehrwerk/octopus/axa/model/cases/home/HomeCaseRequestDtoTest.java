package de.mehrwerk.octopus.axa.model.cases.home;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;

class HomeCaseRequestDtoTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }
/*
    @Test
    void testValidDto() {
        HomeCaseRequestDto dto = new HomeCaseRequestDto()
                .setIncident(new IncidentDto()); // assuming you have this

        Set<ConstraintViolation<HomeCaseRequestDto>> violations = validator.validate(dto);

        assertTrue(violations.isEmpty(), "Should have no violations");
    }

    @Test
    void testInvalidDto_NullIncident() {
        HomeCaseRequestDto dto = new HomeCaseRequestDto();

        Set<ConstraintViolation<HomeCaseRequestDto>> violations = validator.validate(dto);

        assertFalse(violations.isEmpty(), "Should have violations");
        assertEquals(1, violations.size());
    }*/
}