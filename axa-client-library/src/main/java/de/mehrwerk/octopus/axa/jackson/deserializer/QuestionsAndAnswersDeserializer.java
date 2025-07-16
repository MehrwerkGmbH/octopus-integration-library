package de.mehrwerk.octopus.axa.jackson.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import de.mehrwerk.octopus.axa.model.cases.home.*;

import java.io.IOException;

/**
 * Custom deserializer for {@link AbstractQuestionsAndAnswersDto} to handle different question and answer types
 * based on the presence of specific fields in the JSON.
 */
public class QuestionsAndAnswersDeserializer extends JsonDeserializer<AbstractQuestionsAndAnswersDto> {
    @Override
    public AbstractQuestionsAndAnswersDto deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException {
        JsonNode node = p.getCodec().readTree(p);

        if (node.has("answered_boolean")) {
            return p.getCodec().treeToValue(node, BooleanQuestionsAndAnswersDto.class);
        } else if (node.has("answered_date")) {
            return p.getCodec().treeToValue(node, DateQuestionsAndAnswersDto.class);
        } else if (node.has("answered_date_time")) {
            return p.getCodec().treeToValue(node, DateTimeQuestionsAndAnswersDto.class);
        } else if (node.has("enumeration_answers")) {
            return p.getCodec().treeToValue(node, EnumQuestionsAndAnswersDto.class);
        } else if (node.has("answered_text")) {
            return p.getCodec().treeToValue(node, TextQuestionsAndAnswersDto.class);
        } else if (node.has("answered_amount")) {
            return p.getCodec().treeToValue(node, AmountQuestionsAndAnswersDto.class);
        } else if (node.has("answered_number")) {
            return p.getCodec().treeToValue(node, NumberQuestionsAndAnswersDto.class);
        }

        throw new JsonMappingException(p, "Cannot determine QuestionsAndAnswers type from JSON");
    }
}
