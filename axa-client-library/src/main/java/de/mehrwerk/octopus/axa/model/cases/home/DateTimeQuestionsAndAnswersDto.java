package de.mehrwerk.octopus.axa.model.cases.home;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.time.OffsetDateTime;

/**
 * <p>Data transfer object for datetime questions and answers in AXA home cases.</p>
 * <p>This class extends {@link AbstractQuestionsAndAnswersDto} and provides a specific implementation for datetime questions.</p>
 */
@Getter
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DateTimeQuestionsAndAnswersDto extends AbstractQuestionsAndAnswersDto {
    /**
     * <p>The question type</p>
     */
    @JsonProperty("question_type")
    @NotBlank
    private String questionType = "DATE_TIME";
    /**
     * <p>Answered date-time.
     * <p>
     * UTC datetime, RFC3339 format (YYYY-MM-DDTHH:mm:ssZ).<br/>
     * <a href="https://datatracker.ietf.org/doc/html/rfc3339">RFC 3339</a></p>
     */
    @JsonProperty("answered_date_time")
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private OffsetDateTime answeredDateTime;
}
