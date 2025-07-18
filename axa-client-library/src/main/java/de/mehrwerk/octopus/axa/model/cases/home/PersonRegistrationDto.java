package de.mehrwerk.octopus.axa.model.cases.home;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Represents the registration details of a person, such as a policyholder.
 */
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonRegistrationDto {
    /**
     * <p>Type of person registration</p>
     */
    @JsonProperty("registration_type")
    @NotNull
    public RegistrationType registrationType;
    /**
     * <p>Value of the registration</p>
     */
    @JsonProperty("value")
    @Size(max = 20)
    @NotBlank
    public String value;

    /**
     * <p>Registration type</p>
     */
    public enum RegistrationType {
        DRIVER_LICENSE,
        ID_CARD,
        PASSPORT,
        PHONE,
        RESIDENCE_PERMIT,
        TAX_REGISTRATION;

        @Override
        public String toString() {
            return switch (this) {
                case DRIVER_LICENSE -> "DRIVER_LICENSE";
                case ID_CARD -> "ID_CARD";
                case PASSPORT -> "PASSPORT";
                case PHONE -> "PHONE";
                case RESIDENCE_PERMIT -> "RESIDENCE_PERMIT";
                case TAX_REGISTRATION -> "TAX_REGISTRATION";
            };
        }
    }
}
