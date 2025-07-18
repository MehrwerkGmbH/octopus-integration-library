package de.mehrwerk.octopus.axa.model.cases.home;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Address dto
 */
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressDto {
    /**
     * <p>Street address</p>
     */
    @JsonProperty("street_address")
    @Size(max = 255)
    @NotBlank
    public String streetAddress;
    /**
     * <p>Postal code or zip code</p>
     */
    @JsonProperty("postal_code")
    @Size(max = 20)
    public String postalCode;
    /**
     * <p>Locality</p>
     */
    @JsonProperty("locality")
    @Size(max = 100)
    public String locality;
    /**
     * <p>Label of the state</p>
     */
    @JsonProperty("state")
    @Size(max = 100)
    public String state;
    /**
     * <p>Country. ISO-3166-1 alpha-2 format (2-letter codes)</p>
     * <p>The two-character country ISO code as defined in <a href="https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO-3166-1 alpha-2</a></p>
     */
    @JsonProperty("country")
    @Size(min = 2, max = 2)
    @Pattern(regexp = "^[A-Z]{2}$", message = "Country code must be 2 uppercase letters (ISO-3166-1 alpha-2)")
    public String country;
    /**
     * <p>Subdivision</p>
     */
    @JsonProperty("subdivision")
    @Size(max = 100)
    public String subdivision;
}
