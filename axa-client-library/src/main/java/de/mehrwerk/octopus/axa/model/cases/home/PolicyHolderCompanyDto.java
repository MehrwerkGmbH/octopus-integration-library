package de.mehrwerk.octopus.axa.model.cases.home;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Policyholder company related information
 */
@Getter
@Setter
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PolicyHolderCompanyDto extends AbstractPolicyHolderDto {
    /**
     * <p>The legal name of the company</p>
     */
    @JsonProperty("company_legal_name")
    @Size(max = 150)
    @NotBlank
    private String companyLegalName;
    /**
     * <p>Registration of the policyholder</p>
     */
    @JsonProperty("company_registration")
    @Valid
    @NotNull
    private CompanyRegistrationDto companyRegistration;
}
