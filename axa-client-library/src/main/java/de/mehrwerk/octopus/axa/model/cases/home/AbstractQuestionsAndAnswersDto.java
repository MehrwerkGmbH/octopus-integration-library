package de.mehrwerk.octopus.axa.model.cases.home;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import de.mehrwerk.octopus.axa.jackson.deserializer.QuestionsAndAnswersDeserializer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * Abstract class representing a questions and answers DTO.
 * This class contains common fields for questions and answers provided by the contact.
 * There are 7 types of questions and answers:
 * - {@link AmountQuestionsAndAnswersDto}
 * - {@link DateQuestionsAndAnswersDto}
 * - {@link DateTimeQuestionsAndAnswersDto}
 * - {@link BooleanQuestionsAndAnswersDto}
 * - {@link TextQuestionsAndAnswersDto}
 * - {@link NumberQuestionsAndAnswersDto}
 * - {@link EnumQuestionsAndAnswersDto}
 */
@Getter
@Accessors(chain = true)
@JsonDeserialize(using = QuestionsAndAnswersDeserializer.class)
public abstract class AbstractQuestionsAndAnswersDto {
    /**
     * <p>Identifier of the question</p>
     */
    @JsonProperty("question_id")
    @Size(max = 150)
    @NotBlank
    public String questionId;
    /**
     * <p>Text of the question</p>
     */
    @JsonProperty("question_text")
    @Size(max = 255)
    @NotBlank
    public String questionText;
    /**
     * <p>The priority of the question, the lower, the higher. <br/>
     * N.B. 0 means that the question has no priority</p>
     */
    @JsonProperty("priority")
    @NotBlank
    public Integer priority;
}
