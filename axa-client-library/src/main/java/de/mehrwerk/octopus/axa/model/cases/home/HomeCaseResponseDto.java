package de.mehrwerk.octopus.axa.model.cases.home;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.mehrwerk.octopus.axa.jackson.annotation.ValidExclusionReason;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Represents a response DTO for home cases in the AXA system.
 */
@Getter
@Setter
@Accessors(chain = true)
@ValidExclusionReason
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HomeCaseResponseDto extends AbstractHomeCaseDto {
    /**
     * <p>Identifier of the case</p>
     */
    @JsonProperty("case_id")
    @Size(max = 150)
    @NotBlank
    private String caseId;
    /**
     * <p>Short and readable identifier of the case</p>
     */
    @JsonProperty("case_reference")
    @Size(max = 20)
    @NotBlank
    private String caseReference;
    /**
     * <p>Status of the case:
     */
    @JsonProperty("status")
    @NotNull
    private Status status;
    /**
     * <p>Reason why the case is excluded. This field must be present only if $status equals "EXCLUDED"</p>
     */
    @JsonProperty("exclusion_reason")
    @Size(max = 2000)
    private String exclusionReason;

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
