package de.mehrwerk.octopus.axa.model.cases.home;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>Data transfer object for boolean questions and answers in AXA home cases.</p>
 * <p>This class extends {@link AbstractQuestionsAndAnswersDto} and provides a specific implementation for boolean questions.</p>
 */
@Getter
@Setter
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BooleanQuestionsAndAnswersDto extends AbstractQuestionsAndAnswersDto {
    /**
     * <p>The question type</p>
     */
    @JsonProperty("question_type")
    @NotBlank
    private String questionType = "BOOLEAN";
    /**
     * <p>Answered boolean</p>
     */
    @JsonProperty("answered_boolean")
    @NotBlank
    private Boolean answeredBoolean;
}
