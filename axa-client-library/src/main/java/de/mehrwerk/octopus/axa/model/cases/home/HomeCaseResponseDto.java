package de.mehrwerk.octopus.axa.model.cases.home;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.mehrwerk.octopus.axa.jackson.annotation.ValidExclusionReason;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * Represents a response DTO for home cases in the AXA system.
 */
@Getter
@Accessors(chain = true)
@ValidExclusionReason
public class HomeCaseResponseDto extends AbstractHomeCaseDto {
    /**
     * <p>Identifier of the case</p>
     */
    @JsonProperty("case_id")
    @Size(max = 150)
    @NotBlank
    public String caseId;
    /**
     * <p>Short and readable identifier of the case</p>
     */
    @JsonProperty("case_reference")
    @Size(max = 20)
    @NotBlank
    public String caseReference;
    /**
     * <p>Status of the case:
     */
    @JsonProperty("status")
    @NotNull
    public Status status;
    /**
     * <p>Reason why the case is excluded. This field must be present only if $status equals "EXCLUDED"</p>
     */
    @JsonProperty("exclusion_reason")
    @Size(max = 2000)
    public String exclusionReason;

    /**
     * <p>
     * CREATED: The case has been created without any actions expected yet.
     * ONGOING: The case has to be treated, actions are ongoing.
     * EXCLUDED: The case has been created but the customer is not covered by its policy for the incident.
     * </p>
     */
    public enum Status {
        CREATED,
        ONGOING,
        EXCLUDED;

        @Override
        public String toString() {
            return switch (this) {
                case CREATED -> "CREATED";
                case ONGOING -> "ONGOING";
                case EXCLUDED -> "EXCLUDED";
            };
        }
    }
}
