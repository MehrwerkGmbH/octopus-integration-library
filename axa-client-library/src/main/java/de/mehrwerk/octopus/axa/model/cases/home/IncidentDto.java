package de.mehrwerk.octopus.axa.model.cases.home;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.OffsetDateTime;

@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IncidentDto {
    /**
     * <p>Incident cause</p>
     */
    @JsonProperty("cause")
    @NotNull
    @Valid
    private CauseDto cause;
    /**
     * <p>Date-time at which the incident occurred.</p>
     * <p>Format: ISO 8601 <a href="https://en.wikipedia.org/wiki/ISO_8601">ISO 8601</a></p>
     */
    @JsonProperty("occurred_at")
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private OffsetDateTime occurredAt;
    /**
     * <p>Location where the incident happened</p>
     */
    @JsonProperty("location")
    @NotNull
    @Valid
    private LocationDto location;
    /**
     * <p>Incident description</p>
     */
    @JsonProperty("description")
    @Size(max = 2000)
    private String description;
    /**
     * <p>Define who is responsible from the insurance point of view Possible values:</p>
     */
    @JsonProperty("insurance_accountability")
    private InsuranceAccountability insuranceAccountability;

    /**
     * <p>ACCOUNTABLE: From the insurance perspective, the policyholder is accountable of the incident (e.g. key broke in the lock, broken sink...)<br/>
     * NOT_ACCOUNTABLE: From the insurance perspective, the policyholder is not accountable of the incident (e.g. thief attempt, weather conditions, water leakage from neighbor)
     * UNDETERMINED: The origin of the incident is yet to be confirmed
     * </p>
     */
    public enum InsuranceAccountability {
        UNDETERMINED,
        ACCOUNTABLE,
        NOT_ACCOUNTABLE;

        @Override
        public String toString() {
            return switch (this) {
                case UNDETERMINED -> "UNDETERMINED";
                case ACCOUNTABLE -> "ACCOUNTABLE";
                case NOT_ACCOUNTABLE -> "NOT_ACCOUNTABLE";
                default -> "UNDETERMINED";
            };
        }
    }
}
