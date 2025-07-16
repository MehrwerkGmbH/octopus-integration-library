package de.mehrwerk.octopus.axa.model.cases.home;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import de.mehrwerk.octopus.axa.jackson.deserializer.PolicyHolderDeserializer;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * Abstract class representing a policy DTO.
 * This class contains common fields for policy information.
 * There are 3 types of policies:
 * {@link PolicyPartnerReferenceDto}
 * {@link PolicyPartnerIdDto}
 * {@link PolicyEligibilityTokenDto}
 */
@Getter
@Accessors(chain = true)
public abstract class AbstractPolicyDto {
    /**
     * <p>Information related to the holder of the policy</p>
     */
    @JsonDeserialize(using = PolicyHolderDeserializer.class)
    @JsonProperty("policy_holder")
    @Valid
    public AbstractPolicyHolderDto policyHolder;
}
