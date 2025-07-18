package de.mehrwerk.octopus.axa.jackson.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import de.mehrwerk.octopus.axa.model.cases.home.*;

import java.io.IOException;

/**
 * Custom deserializer for {@link AbstractQuestionsAndAnswersDto} to handle different question and answer types
 * based on the presence of specific fields in the JSON.
 */
public class QuestionsAndAnswersDeserializer extends JsonDeserializer<AbstractQuestionsAndAnswersDto> {
    private static final ObjectMapper mapper = new ObjectMapper()
            .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

    @Override
    public AbstractQuestionsAndAnswersDto deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException {
        JsonNode node = p.getCodec().readTree(p);

        if (node.isTextual()) {
            node = mapper.readTree(node.asText());
        }

        if (node.has("answered_boolean")) {
            return mapper.treeToValue(node, BooleanQuestionsAndAnswersDto.class);
        } else if (node.has("answered_date")) {
            return mapper.treeToValue(node, DateQuestionsAndAnswersDto.class);
        } else if (node.has("answered_date_time")) {
            return mapper.treeToValue(node, DateTimeQuestionsAndAnswersDto.class);
        } else if (node.has("enumeration_answers")) {
            return mapper.treeToValue(node, EnumQuestionsAndAnswersDto.class);
        } else if (node.has("answered_text")) {
            return mapper.treeToValue(node, TextQuestionsAndAnswersDto.class);
        } else if (node.has("answered_amount")) {
            return mapper.treeToValue(node, AmountQuestionsAndAnswersDto.class);
        } else if (node.has("answered_number")) {
            return mapper.treeToValue(node, NumberQuestionsAndAnswersDto.class);
        }

        throw new JsonMappingException(p, "Cannot determine QuestionsAndAnswers type from JSON");
    }
}
