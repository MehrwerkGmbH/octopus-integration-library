package de.mehrwerk.octopus.axa.model.cases.home;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Company registration information
 */
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompanyRegistrationDto {
    /**
     * <p>Type of company registration. Allowed values are:
     * <p>
     * SIRET: Stands for "Système d'Identification du Répertoire des Entreprises et de leurs Établissements" and can be translated as “Directory of Establishments Identification System”. Only used in France.</p>
     */
    @JsonProperty("registration_type")
    @Size(max = 150)
    public RegistrationType registrationType;
    /**
     * <p>Value of the identifier</p>
     */
    @JsonProperty("value")
    @Size(max = 20)
    @NotBlank
    public String value;

    /**
     * <p>Registration type</p>
     */
    public enum RegistrationType {
        SIRET;

        @Override
        public String toString() {
            return switch (this) {
                case SIRET -> "SIRET";
            };
        }
    }
}
