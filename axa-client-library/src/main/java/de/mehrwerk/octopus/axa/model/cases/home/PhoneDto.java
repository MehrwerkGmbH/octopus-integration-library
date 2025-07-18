package de.mehrwerk.octopus.axa.model.cases.home;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Represents a phone number with an international prefix.
 */
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PhoneDto {
    /**
     * <p>Prefix of the phone number</p>
     */
    @JsonProperty("international_prefix")
    @Size(max = 5)
    public String internationalPrefix;
    /**
     * <p>Phone number</p>
     */
    @JsonProperty("number")
    @Size(max = 20)
    @NotBlank
    public String number;
}
