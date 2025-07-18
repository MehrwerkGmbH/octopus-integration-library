package de.mehrwerk.octopus.axa.model.cases.home;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class PolicyEligibilityTokenDtoTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void testPolicyId_Valid() {
        PolicyEligibilityTokenDto dto = new PolicyEligibilityTokenDto()
                .setPolicyId("POLICY123");
        Set<ConstraintViolation<PolicyEligibilityTokenDto>> violations = validator.validateProperty(dto, "policyId");
        assertThat(violations).isEmpty();
    }

    @Test
    void testPolicyId_Null() {
        PolicyEligibilityTokenDto dto = new PolicyEligibilityTokenDto()
                .setPolicyId(null);
        Set<ConstraintViolation<PolicyEligibilityTokenDto>> violations = validator.validateProperty(dto, "policyId");
        assertThat(violations).isNotEmpty();
    }

    @Test
    void testPolicyId_Blank() {
        PolicyEligibilityTokenDto dto = new PolicyEligibilityTokenDto()
                .setPolicyId("   ");
        Set<ConstraintViolation<PolicyEligibilityTokenDto>> violations = validator.validateProperty(dto, "policyId");
        assertThat(violations).isNotEmpty();
    }

    @Test
    void testPolicyId_TooLong() {
        String longPolicyId = "P".repeat(151);
        PolicyEligibilityTokenDto dto = new PolicyEligibilityTokenDto()
                .setPolicyId(longPolicyId);
        Set<ConstraintViolation<PolicyEligibilityTokenDto>> violations = validator.validateProperty(dto, "policyId");
        assertThat(violations).isNotEmpty();
    }

    @Test
    void testEligibilityToken_Valid() {
        PolicyEligibilityTokenDto dto = new PolicyEligibilityTokenDto()
                .setEligibilityToken("TOKEN123");
        Set<ConstraintViolation<PolicyEligibilityTokenDto>> violations = validator.validateProperty(dto, "eligibilityToken");
        assertThat(violations).isEmpty();
    }

    @Test
    void testEligibilityToken_Null() {
        PolicyEligibilityTokenDto dto = new PolicyEligibilityTokenDto()
                .setEligibilityToken(null);
        Set<ConstraintViolation<PolicyEligibilityTokenDto>> violations = validator.validateProperty(dto, "eligibilityToken");
        assertThat(violations).isNotEmpty();
    }

    @Test
    void testEligibilityToken_Blank() {
        PolicyEligibilityTokenDto dto = new PolicyEligibilityTokenDto()
                .setEligibilityToken("   ");
        Set<ConstraintViolation<PolicyEligibilityTokenDto>> violations = validator.validateProperty(dto, "eligibilityToken");
        assertThat(violations).isNotEmpty();
    }

    @Test
    void testEligibilityToken_TooLong() {
        String longToken = "T".repeat(2001);
        PolicyEligibilityTokenDto dto = new PolicyEligibilityTokenDto()
                .setEligibilityToken(longToken);
        Set<ConstraintViolation<PolicyEligibilityTokenDto>> violations = validator.validateProperty(dto, "eligibilityToken");
        assertThat(violations).isNotEmpty();
    }

    @Test
    void testPolicyEligibilityTokenDto_Valid() {
        PolicyEligibilityTokenDto dto = new PolicyEligibilityTokenDto()
                .setPolicyId("POLICY123")
                .setEligibilityToken("TOKEN123");
        Set<ConstraintViolation<PolicyEligibilityTokenDto>> violations = validator.validate(dto);
        assertThat(violations).isEmpty();
    }

    @Test
    void testPolicyEligibilityTokenDto_AllNull() {
        PolicyEligibilityTokenDto dto = new PolicyEligibilityTokenDto();
        Set<ConstraintViolation<PolicyEligibilityTokenDto>> violations = validator.validate(dto);
        assertThat(violations).hasSize(2);
        assertThat(violations).anyMatch(v -> v.getPropertyPath().toString().equals("policyId"));
        assertThat(violations).anyMatch(v -> v.getPropertyPath().toString().equals("eligibilityToken"));
    }
}