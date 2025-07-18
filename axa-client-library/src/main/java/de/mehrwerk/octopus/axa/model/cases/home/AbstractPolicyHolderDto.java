package de.mehrwerk.octopus.axa.model.cases.home;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Abstract class representing a policyholder DTO.
 * There are 2 types of policyholders:
 * {@link PolicyHolderCompanyDto}
 * {@link PolicyHolderPersonalDto}
 */
@Getter
@Setter
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class AbstractPolicyHolderDto {
}
