package de.mehrwerk.octopus.axa.model.cases.home;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class AbstractPolicyHolderDtoTest {

    private Validator validator;
    private PolicyHolderPersonalDto validDto;

    @BeforeEach
    void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        validDto = new PolicyHolderPersonalDto()
                .setFirstName("John")
                .setLastName("Doe")
                .setIsPropertyOwner(true)
                .setAddressPostalCode("12345");
    }

    @Test
    void testPolicyHolderDto_Valid() {
        Set<ConstraintViolation<AbstractPolicyHolderDto>> violations = validator.validate(validDto);
        assertThat(violations).isEmpty();
    }

    @Test
    void testPolicyHolderDto_AllFieldsNull() {
        PolicyHolderPersonalDto dto = new PolicyHolderPersonalDto();
        Set<ConstraintViolation<AbstractPolicyHolderDto>> violations = validator.validate(dto);
        assertThat(violations).isEmpty();
    }

    @Test
    void testJsonIncludeNonNull() {
        PolicyHolderPersonalDto dto = new PolicyHolderPersonalDto();
        assertThat(dto.getClass().isAnnotationPresent(JsonInclude.class)).isTrue();
        JsonInclude annotation = dto.getClass().getAnnotation(JsonInclude.class);
        assertThat(annotation.value()).isEqualTo(JsonInclude.Include.NON_NULL);
    }
}