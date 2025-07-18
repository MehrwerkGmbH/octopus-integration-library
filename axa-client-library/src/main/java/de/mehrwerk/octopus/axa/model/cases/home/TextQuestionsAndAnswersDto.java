package de.mehrwerk.octopus.axa.model.cases.home;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>Data transfer object for text questions and answers in AXA home cases.</p>
 * <p>This class extends {@link AbstractQuestionsAndAnswersDto} and provides a specific implementation for text questions.</p>
 */
@Getter
@Setter
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TextQuestionsAndAnswersDto extends AbstractQuestionsAndAnswersDto {
    /**
     * <p>The question type</p>
     */
    @JsonProperty("question_type")
    @NotNull
    private QuestionTypeEnum questionType;
    /**
     * <p>Text of the answer</p>
     */
    @JsonProperty("answered_text")
    @NotBlank
    @Size(max = 2000)
    private String answeredText;

    /**
     * <p>Location or String</p>
     */
    public enum QuestionTypeEnum {
        LOCATION,
        STRING;

        @Override
        public String toString() {
            return switch (this) {
                case LOCATION -> "LOCATION";
                case STRING -> "STRING";
            };
        }
    }
}
