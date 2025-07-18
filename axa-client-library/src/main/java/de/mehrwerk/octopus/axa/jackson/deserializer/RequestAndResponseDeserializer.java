package de.mehrwerk.octopus.axa.jackson.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import de.mehrwerk.octopus.axa.model.cases.home.AbstractHomeCaseDto;
import de.mehrwerk.octopus.axa.model.cases.home.HomeCaseRequestDto;
import de.mehrwerk.octopus.axa.model.cases.home.HomeCaseResponseDto;

import java.io.IOException;

/**
 * Custom deserializer for {@link AbstractHomeCaseDto} to handle different case request and response types
 * based on the presence of specific fields in the JSON.
 */
public class RequestAndResponseDeserializer extends JsonDeserializer<AbstractHomeCaseDto> {
    private static final ObjectMapper mapper = new ObjectMapper()
            .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

    @Override
    public AbstractHomeCaseDto deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException {
        JsonNode node = p.getCodec().readTree(p);

        if (node.isTextual()) {
            node = mapper.readTree(node.asText());
        }

        if (node.has("case_id") && node.has("case_reference")
                && node.has("status")) {
            return mapper.treeToValue(node, HomeCaseResponseDto.class);
        } else {
            return mapper.treeToValue(node, HomeCaseRequestDto.class);
        }
    }
}