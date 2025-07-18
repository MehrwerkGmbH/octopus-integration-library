package de.mehrwerk.octopus.axa.model.cases.home;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class AbstractPolicyDtoTest {

    private Validator validator;
    private AbstractPolicyDto validDto;
    private AbstractPolicyHolderDto validPolicyHolder;

    @BeforeEach
    void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        validPolicyHolder = new PolicyHolderPersonalDto()
                .setFirstName("John")
                .setLastName("Doe");
        validDto = new PolicyPartnerReferenceDto()
                .setPolicyHolder(validPolicyHolder);
    }

    @Test
    void testPolicyDto_Valid() {
        Set<ConstraintViolation<AbstractPolicyDto>> violations = validator.validate(validDto);
        assertThat(violations).isEmpty();
    }

    @Test
    void testPolicyDto_NullPolicyHolder() {
        AbstractPolicyDto dto = new PolicyPartnerReferenceDto()
                .setPolicyHolder(null);
        Set<ConstraintViolation<AbstractPolicyDto>> violations = validator.validate(dto);
        assertThat(violations).isEmpty();
    }

    @Test
    void testPolicyDto_InvalidPolicyHolder() {
        AbstractPolicyHolderDto invalidPolicyHolder = new PolicyHolderPersonalDto();
        AbstractPolicyDto dto = new PolicyPartnerReferenceDto()
                .setPolicyHolder(invalidPolicyHolder);
        Set<ConstraintViolation<AbstractPolicyDto>> violations = validator.validate(dto);
        assertThat(violations).isEmpty();
    }

    @Test
    void testJsonIncludeNonNull() {
        AbstractPolicyDto dto = new PolicyPartnerReferenceDto();
        assertThat(dto.getClass().isAnnotationPresent(JsonInclude.class)).isTrue();
        JsonInclude annotation = dto.getClass().getAnnotation(JsonInclude.class);
        assertThat(annotation.value()).isEqualTo(JsonInclude.Include.NON_NULL);
    }
}