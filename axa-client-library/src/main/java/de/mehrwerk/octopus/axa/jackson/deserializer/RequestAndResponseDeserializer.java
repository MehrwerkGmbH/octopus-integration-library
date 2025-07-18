package de.mehrwerk.octopus.axa.jackson.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.mehrwerk.octopus.axa.model.cases.home.AbstractHomeCaseDto;
import de.mehrwerk.octopus.axa.model.cases.home.HomeCaseRequestDto;
import de.mehrwerk.octopus.axa.model.cases.home.HomeCaseResponseDto;

import java.io.IOException;

/**
 * Custom deserializer for {@link AbstractHomeCaseDto} to handle different case request and response types
 * based on the presence of specific fields in the JSON.
 */
public class RequestAndResponseDeserializer extends JsonDeserializer<AbstractHomeCaseDto> {
    @Override
    public AbstractHomeCaseDto deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException {
        ObjectMapper mapper = (ObjectMapper) p.getCodec();
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