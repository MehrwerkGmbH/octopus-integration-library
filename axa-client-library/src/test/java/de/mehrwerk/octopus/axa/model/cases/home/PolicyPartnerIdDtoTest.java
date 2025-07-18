package de.mehrwerk.octopus.axa.model.cases.home;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class PolicyPartnerIdDtoTest {

    private Validator validator;
    private String validPartnerId;

    @BeforeEach
    void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        validPartnerId = "PARTNER123";
    }

    @Test
    void testPartnerId_Valid() {
        PolicyPartnerIdDto dto = new PolicyPartnerIdDto()
                .setPartnerId(validPartnerId);
        Set<ConstraintViolation<PolicyPartnerIdDto>> violations = validator.validateProperty(dto, "partnerId");
        assertThat(violations).isEmpty();
    }

    @Test
    void testPartnerId_Null() {
        PolicyPartnerIdDto dto = new PolicyPartnerIdDto()
                .setPartnerId(null);
        Set<ConstraintViolation<PolicyPartnerIdDto>> violations = validator.validateProperty(dto, "partnerId");
        assertThat(violations).isNotEmpty();
    }

    @Test
    void testPartnerId_Blank() {
        PolicyPartnerIdDto dto = new PolicyPartnerIdDto()
                .setPartnerId("   ");
        Set<ConstraintViolation<PolicyPartnerIdDto>> violations = validator.validateProperty(dto, "partnerId");
        assertThat(violations).isNotEmpty();
    }

    @Test
    void testPartnerId_TooLong() {
        String longPartnerId = "P".repeat(151);
        PolicyPartnerIdDto dto = new PolicyPartnerIdDto()
                .setPartnerId(longPartnerId);
        Set<ConstraintViolation<PolicyPartnerIdDto>> violations = validator.validateProperty(dto, "partnerId");
        assertThat(violations).isNotEmpty();
    }

    @Test
    void testExternalPolicyNumber_Valid() {
        PolicyPartnerIdDto dto = new PolicyPartnerIdDto()
                .setExternalPolicyNumber("POLICY123");
        Set<ConstraintViolation<PolicyPartnerIdDto>> violations = validator.validateProperty(dto, "externalPolicyNumber");
        assertThat(violations).isEmpty();
    }

    @Test
    void testExternalPolicyNumber_TooLong() {
        String longPolicyNumber = "P".repeat(151);
        PolicyPartnerIdDto dto = new PolicyPartnerIdDto()
                .setExternalPolicyNumber(longPolicyNumber);
        Set<ConstraintViolation<PolicyPartnerIdDto>> violations = validator.validateProperty(dto, "externalPolicyNumber");
        assertThat(violations).isNotEmpty();
    }

    @Test
    void testPackageId_Valid() {
        PolicyPartnerIdDto dto = new PolicyPartnerIdDto()
                .setPackageId("PACKAGE123");
        Set<ConstraintViolation<PolicyPartnerIdDto>> violations = validator.validateProperty(dto, "packageId");
        assertThat(violations).isEmpty();
    }

    @Test
    void testPackageId_TooLong() {
        String longPackageId = "P".repeat(151);
        PolicyPartnerIdDto dto = new PolicyPartnerIdDto()
                .setPackageId(longPackageId);
        Set<ConstraintViolation<PolicyPartnerIdDto>> violations = validator.validateProperty(dto, "packageId");
        assertThat(violations).isNotEmpty();
    }

    @Test
    void testPolicyPartnerIdDto_Valid() {
        PolicyPartnerIdDto dto = new PolicyPartnerIdDto()
                .setPartnerId(validPartnerId)
                .setExternalPolicyNumber("POLICY123")
                .setPackageId("PACKAGE123");
        Set<ConstraintViolation<PolicyPartnerIdDto>> violations = validator.validate(dto);
        assertThat(violations).isEmpty();
    }

    @Test
    void testPolicyPartnerIdDto_MissingRequiredField() {
        PolicyPartnerIdDto dto = new PolicyPartnerIdDto();
        Set<ConstraintViolation<PolicyPartnerIdDto>> violations = validator.validate(dto);
        assertThat(violations).anyMatch(v -> v.getPropertyPath().toString().equals("partnerId"));
    }
}