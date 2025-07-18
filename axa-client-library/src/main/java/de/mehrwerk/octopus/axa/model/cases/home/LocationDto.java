package de.mehrwerk.octopus.axa.model.cases.home;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>Location dto</p>
 */
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LocationDto {
    /**
     * <p>Location address</p>
     */
    @JsonProperty("address")
    @NotNull
    @Valid
    public AddressDto address;
    /**
     * <p>Additional information about the location</p>
     */
    @JsonProperty("additional_information")
    @Size(max = 2000)
    public String additionalInformation;
}
