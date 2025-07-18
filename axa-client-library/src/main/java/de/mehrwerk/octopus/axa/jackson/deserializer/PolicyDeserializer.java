package de.mehrwerk.octopus.axa.jackson.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.mehrwerk.octopus.axa.model.cases.home.AbstractPolicyDto;
import de.mehrwerk.octopus.axa.model.cases.home.PolicyEligibilityTokenDto;
import de.mehrwerk.octopus.axa.model.cases.home.PolicyPartnerIdDto;
import de.mehrwerk.octopus.axa.model.cases.home.PolicyPartnerReferenceDto;

import java.io.IOException;

/**
 * Custom deserializer for {@link AbstractPolicyDto} to handle different policy types based on the presence of specific fields.
 */
public class PolicyDeserializer extends JsonDeserializer<AbstractPolicyDto> {
    @Override
    public AbstractPolicyDto deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException {
        ObjectMapper mapper = (ObjectMapper) p.getCodec();
        JsonNode node = p.getCodec().readTree(p);

        if (node.has("partner_id")) {
            return mapper.treeToValue(node, PolicyPartnerIdDto.class);
        } else if (node.has("policy_id")) {
            return mapper.treeToValue(node, PolicyEligibilityTokenDto.class);
        } else {
            return mapper.treeToValue(node, PolicyPartnerReferenceDto.class);
        }
    }
}
