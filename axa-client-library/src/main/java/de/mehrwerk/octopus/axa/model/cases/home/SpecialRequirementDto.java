package de.mehrwerk.octopus.axa.model.cases.home;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Represents special requirements
 */
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SpecialRequirementDto {
    /**
     * <p>
     * boolean
     * <p>
     * Indicates if the contact requested to have a checkup of its installation performed once the assistance is finished</p>
     */
    @JsonProperty("has_requested_check_up")
    @NotNull
    private Boolean hasRequestedCheckUp;
}
