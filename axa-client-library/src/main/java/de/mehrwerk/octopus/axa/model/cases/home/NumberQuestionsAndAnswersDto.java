package de.mehrwerk.octopus.axa.model.cases.home;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>Data transfer object for number questions and answers in AXA home cases.</p>
 * <p>This class extends {@link AbstractQuestionsAndAnswersDto} and provides a specific implementation for number questions.</p>
 */
@Getter
@Setter
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NumberQuestionsAndAnswersDto extends AbstractQuestionsAndAnswersDto {
    /**
     * <p>The question type</p>
     */
    @JsonProperty("question_type")
    @NotBlank
    private String questionType = "NUMBER";
    /**
     * <p>Answered number</p>
     */
    @JsonProperty("answered_number")
    @NotNull
    @Valid
    private Integer answeredNumber;
}
