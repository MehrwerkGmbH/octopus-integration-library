package de.mehrwerk.octopus.axa.model.cases.home;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * DTO for policy eligibility token, containing the policy ID and the eligibility token.
 * This is used to verify the eligibility of a policyholder for a specific policy.
 */
@Getter
@Accessors(chain = true)
public class PolicyEligibilityTokenDto extends AbstractPolicyDto {
    /**
     * <p>Identifier of the policy that enables the claim creation</p>
     */
    @JsonProperty("policy_id")
    @Size(max = 150)
    @NotBlank
    public String policyId;
    /**
     * <p>Token that proves that the contact is eligible to the claim creation</p>
     */
    @JsonProperty("eligibility_token")
    @Size(max = 2000)
    @NotBlank
    public String eligibilityToken;
}
