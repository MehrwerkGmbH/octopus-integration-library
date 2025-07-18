package de.mehrwerk.octopus.axa.model.cases.home;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class PolicyPartnerReferenceDtoTest {

    private Validator validator;
    private String validPartnerReference;

    @BeforeEach
    void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        validPartnerReference = "REF123";
    }

    @Test
    void testPartnerReference_Valid() {
        PolicyPartnerReferenceDto dto = new PolicyPartnerReferenceDto()
                .setPartnerReference(validPartnerReference);
        Set<ConstraintViolation<PolicyPartnerReferenceDto>> violations = validator.validateProperty(dto, "partnerReference");
        assertThat(violations).isEmpty();
    }

    @Test
    void testPartnerReference_TooLong() {
        String longReference = "R".repeat(51);
        PolicyPartnerReferenceDto dto = new PolicyPartnerReferenceDto()
                .setPartnerReference(longReference);
        Set<ConstraintViolation<PolicyPartnerReferenceDto>> violations = validator.validateProperty(dto, "partnerReference");
        assertThat(violations).isNotEmpty();
    }

    @Test
    void testExternalPolicyNumber_Valid() {
        PolicyPartnerReferenceDto dto = new PolicyPartnerReferenceDto()
                .setExternalPolicyNumber("POLICY123");
        Set<ConstraintViolation<PolicyPartnerReferenceDto>> violations = validator.validateProperty(dto, "externalPolicyNumber");
        assertThat(violations).isEmpty();
    }

    @Test
    void testExternalPolicyNumber_TooLong() {
        String longPolicyNumber = "P".repeat(151);
        PolicyPartnerReferenceDto dto = new PolicyPartnerReferenceDto()
                .setExternalPolicyNumber(longPolicyNumber);
        Set<ConstraintViolation<PolicyPartnerReferenceDto>> violations = validator.validateProperty(dto, "externalPolicyNumber");
        assertThat(violations).isNotEmpty();
    }

    @Test
    void testPackageId_Valid() {
        PolicyPartnerReferenceDto dto = new PolicyPartnerReferenceDto()
                .setPackageId("PACKAGE123");
        Set<ConstraintViolation<PolicyPartnerReferenceDto>> violations = validator.validateProperty(dto, "packageId");
        assertThat(violations).isEmpty();
    }

    @Test
    void testPackageId_TooLong() {
        String longPackageId = "P".repeat(151);
        PolicyPartnerReferenceDto dto = new PolicyPartnerReferenceDto()
                .setPackageId(longPackageId);
        Set<ConstraintViolation<PolicyPartnerReferenceDto>> violations = validator.validateProperty(dto, "packageId");
        assertThat(violations).isNotEmpty();
    }

    @Test
    void testPolicyPartnerReferenceDto_Valid() {
        PolicyPartnerReferenceDto dto = new PolicyPartnerReferenceDto()
                .setPartnerReference(validPartnerReference)
                .setExternalPolicyNumber("POLICY123")
                .setPackageId("PACKAGE123");
        Set<ConstraintViolation<PolicyPartnerReferenceDto>> violations = validator.validate(dto);
        assertThat(violations).isEmpty();
    }

    @Test
    void testPolicyPartnerReferenceDto_AllNull() {
        PolicyPartnerReferenceDto dto = new PolicyPartnerReferenceDto();
        Set<ConstraintViolation<PolicyPartnerReferenceDto>> violations = validator.validate(dto);
        assertThat(violations).isEmpty();
    }
}