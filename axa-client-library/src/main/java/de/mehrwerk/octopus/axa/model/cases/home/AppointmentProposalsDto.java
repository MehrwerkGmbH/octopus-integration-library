package de.mehrwerk.octopus.axa.model.cases.home;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.OffsetDateTime;

/**
 * Represents an appointment proposal
 */
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AppointmentProposalsDto {
    /**
     * <p>Starting datetime of the proposed appointment. UTC datetime, RFC3339 format (YYYY-MM-DDTHH:mm:ssZ).<br/>
     * <a href="https://datatracker.ietf.org/doc/html/rfc3339">RFC 3339</a></p>
     */
    @JsonProperty("start_at")
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private OffsetDateTime startAt;
    /**
     * <p>Ending datetime of the proposed appointment. UTC datetime, RFC3339 format (YYYY-MM-DDTHH:mm:ssZ).<br/>
     * <a href="https://datatracker.ietf.org/doc/html/rfc3339">RFC 3339</a></p>
     */
    @JsonProperty("end_at")
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private OffsetDateTime endAt;
    /**
     * <p>The contact preferred appointment order. Lower number, higher preference</p>
     */
    @JsonProperty("preferred_order")
    @Min(1)
    @Max(20)
    private Integer preferredOrder;
}
