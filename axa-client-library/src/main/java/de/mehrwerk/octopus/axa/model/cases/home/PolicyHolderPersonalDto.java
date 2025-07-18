package de.mehrwerk.octopus.axa.model.cases.home;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Represents the personal details of a policyholder
 */
@Getter
@Setter
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PolicyHolderPersonalDto extends AbstractPolicyHolderDto {
    /**
     * <p>States if the policyholder is the property owner:
     * <p>
     * true: the policyholder is the property owner;
     * false: the policyholder is the tenant.</p>
     */
    @JsonProperty("is_property_owner")
    private Boolean isPropertyOwner;
    /**
     * <p>First name of the policyholder</p>
     */
    @JsonProperty("first_name")
    @Size(max = 100)
    private String firstName;
    /**
     * <p>Last name of the policyholder</p>
     */
    @JsonProperty("last_name")
    @Size(max = 100)
    private String lastName;
    /**
     * <p>Address postal code of the policyholder</p>
     */
    @JsonProperty("address_postal_code")
    @Size(max = 20)
    private String addressPostalCode;
    /**
     * <p>Registration of the policyholder</p>
     */
    @JsonProperty("person_registration")
    @Valid
    private PersonRegistrationDto personRegistration;
}
