package de.mehrwerk.octopus.axa.model.cases.home;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Enumeration answers that used in question and answers
 */
@Data
@Accessors(chain = true)
public class EnumerationAnswersDto {
    /**
     * <p>Identifier of the answer</p>
     */
    @JsonProperty("answer_id")
    @Size(max = 150)
    @NotBlank
    public String answerId;
    /**
     * <p>The text of the answer to be displayed to the user</p>
     */
    @JsonProperty("answer_text")
    @Size(max = 100)
    @NotBlank
    public String answerText;
    /**
     * <p>Indicates if the answer was selected by the reporter:
     * <p>
     * true: the answer was selected by the reporter;
     * false: the answer wasn't selected by the reporter.</p>
     */
    @JsonProperty("is_selected")
    public Boolean isSelected = false;
}
