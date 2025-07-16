package de.mehrwerk.octopus.axa.model.cases.home;

import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * Abstract class representing a policyholder DTO.
 * There are 2 types of policyholders:
 * {@link PolicyHolderCompanyDto}
 * {@link PolicyHolderPersonalDto}
 */
@Getter
@Accessors(chain = true)
public abstract class AbstractPolicyHolderDto {
}
