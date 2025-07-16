package de.mehrwerk.octopus.axa.model.cases.home;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * <p>Data transfer object for monetary_amount questions and answers in AXA home cases.</p>
 * <p>This class extends {@link AbstractQuestionsAndAnswersDto} and provides a specific implementation for monetary_amount questions.</p>
 */
@Getter
@Accessors(chain = true)
public class AmountQuestionsAndAnswersDto extends AbstractQuestionsAndAnswersDto {
    /**
     * <p>The question type</p>
     */
    @JsonProperty("question_type")
    @NotBlank
    public String questionType = "MONETARY_AMOUNT";
    /**
     * <p>Answered monitary amount</p>
     */
    @JsonProperty("answered_amount")
    @NotNull
    @Valid
    public AnsweredAmountDto answeredAmount;
}
