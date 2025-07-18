package de.mehrwerk.octopus.axa.model.cases.home;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.mehrwerk.octopus.axa.jackson.annotation.DecimalScale;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * Monetary amount dto
 */
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AnsweredAmountDto {
    /**
     * <p>Amount, numeric, with 2 or 3 decimals.</p>
     */
    @JsonProperty("value")
    @NotNull
    @DecimalScale(allowedScales = {2, 3})
    private BigDecimal value;
    /**
     * <p>Currency of the amount. ISO 4217 format (3-letter code)<br/>
     * <a href="https://en.wikipedia.org/wiki/ISO_4217">ISO 4217</a></p>
     */
    @JsonProperty("currency")
    @Size(min = 3, max = 3)
    @NotBlank
    private String currency;
}
