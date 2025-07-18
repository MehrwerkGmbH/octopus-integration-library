package de.mehrwerk.octopus.axa.model.cases.home;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>Incident cause</p>
 */
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CauseDto {
    /**
     * <p>Breakdown diagnostic code</p>
     */
    @JsonProperty("code")
    @Size(max = 100)
    @NotBlank
    private String code;
    /**
     * <p>Breakdown diagnostic description</p>
     */
    @JsonProperty("description")
    @Size(max = 255)
    private String description;
}
