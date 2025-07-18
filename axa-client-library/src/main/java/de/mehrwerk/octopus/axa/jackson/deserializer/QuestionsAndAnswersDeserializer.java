package de.mehrwerk.octopus.axa.jackson.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import de.mehrwerk.octopus.axa.model.cases.home.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Custom deserializer for {@link AbstractQuestionsAndAnswersDto} to handle different question and answer types
 * based on the presence of specific fields in the JSON.
 */
public class QuestionsAndAnswersDeserializer extends JsonDeserializer<List<AbstractQuestionsAndAnswersDto>> {
    @Override
    public List<AbstractQuestionsAndAnswersDto> deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException {
        ObjectMapper mapper = (ObjectMapper) p.getCodec();
        JsonNode node = p.getCodec().readTree(p);

        List<AbstractQuestionsAndAnswersDto> result = new ArrayList<>();

        if (node.isArray()) {
            for (JsonNode item : node) {
                if (item.has("answered_boolean")) {
                    result.add(mapper.treeToValue(item, BooleanQuestionsAndAnswersDto.class));
                } else if (item.has("answered_date")) {
                    result.add(mapper.treeToValue(item, DateQuestionsAndAnswersDto.class));
                } else if (item.has("answered_date_time")) {
                    result.add(mapper.treeToValue(item, DateTimeQuestionsAndAnswersDto.class));
                } else if (item.has("enumeration_answers")) {
                    result.add(mapper.treeToValue(item, EnumQuestionsAndAnswersDto.class));
                } else if (item.has("answered_text")) {
                    result.add(mapper.treeToValue(item, TextQuestionsAndAnswersDto.class));
                } else if (item.has("answered_amount")) {
                    result.add(mapper.treeToValue(item, AmountQuestionsAndAnswersDto.class));
                } else if (item.has("answered_number")) {
                    result.add(mapper.treeToValue(item, NumberQuestionsAndAnswersDto.class));
                } else {
                    throw new JsonMappingException(p, "Cannot determine QuestionsAndAnswers type from JSON item: " + item);
                }
            }
            return result;
        }

        throw new JsonMappingException(p, "Cannot determine QuestionsAndAnswers type from JSON");
    }
}
