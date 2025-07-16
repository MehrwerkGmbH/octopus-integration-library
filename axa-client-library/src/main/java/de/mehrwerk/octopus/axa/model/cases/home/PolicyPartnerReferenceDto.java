package de.mehrwerk.octopus.axa.model.cases.home;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * Represents a policy with an external policy number and a partner reference.
 * This DTO is used to transfer policy information related to a specific partner reference.
 */
@Getter
@Accessors(chain = true)
public class PolicyPartnerReferenceDto extends AbstractPolicyDto {
    /**
     * <p>Identifier of the policy that enables the claim creation</p>
     */
    @JsonProperty("external_policy_number")
    @Size(max = 150)
    public String externalPolicyNumber;
    /**
     * <p>Reference of the partner.
     * <p>
     * N.B. The access to a specific partner is made by configuration per OAuth2 application. In case the OAuth2 application is not allowed to access the specified partner, the request will be rejected.
     * </p>
     */
    @JsonProperty("partner_reference")
    @Size(max = 50)
    public String partnerReference;
    /**
     * <p>Identifier of the package</p>
     */
    @JsonProperty("package_id")
    @Size(max = 150)
    public String packageId;
}
