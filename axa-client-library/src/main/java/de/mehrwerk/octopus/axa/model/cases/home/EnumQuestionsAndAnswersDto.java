package de.mehrwerk.octopus.axa.model.cases.home;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * <p>Data transfer object for enum questions and answers in AXA home cases.</p>
 * <p>This class extends {@link AbstractQuestionsAndAnswersDto} and provides a specific implementation for enum questions.</p>
 */
@Getter
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EnumQuestionsAndAnswersDto extends AbstractQuestionsAndAnswersDto {
    /**
     * <p>The question type</p>
     */
    @JsonProperty("question_type")
    @NotBlank
    public String questionType = "ENUM";
    /**
     * <p>List of the answers</p>
     * <p>Contains a list of {@link EnumerationAnswersDto} objects, which represent the possible answers to the enum question.</p>
     */
    @JsonProperty("enumeration_answers")
    @Valid
    @NotNull
    @Size(min = 1, max = 20)
    public List<EnumerationAnswersDto> enumerationAnswers;
}
