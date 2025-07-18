package de.mehrwerk.octopus.axa.model.cases.home;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import de.mehrwerk.octopus.axa.jackson.deserializer.PolicyHolderDeserializer;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;
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
@Setter
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class AbstractPolicyDto {
    /**
     * <p>Information related to the holder of the policy</p>
     */
    @JsonDeserialize(using = PolicyHolderDeserializer.class)
    @JsonProperty("policy_holder")
    @Valid
    private AbstractPolicyHolderDto policyHolder;
}
