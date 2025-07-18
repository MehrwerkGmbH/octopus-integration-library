package de.mehrwerk.octopus.axa.model.cases.home;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;

/**
 * <p>Data transfer object for date questions and answers in AXA home cases.</p>
 * <p>This class extends {@link AbstractQuestionsAndAnswersDto} and provides a specific implementation for date questions.</p>
 */
@Getter
@Setter
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DateQuestionsAndAnswersDto extends AbstractQuestionsAndAnswersDto {
    /**
     * <p>The question type</p>
     */
    @JsonProperty("question_type")
    @NotBlank
    private String questionType = "DATE";
    /**
     * <p>Answered date. ISO 8601 format (YYYY-MM-DD)<br/>
     * <a href="https://en.wikipedia.org/wiki/ISO_8601">ISO 8601</a></p>
     */
    @JsonProperty("answered_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull
    private LocalDate answeredDate;
}
