package de.mehrwerk.octopus.axa.jackson.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import de.mehrwerk.octopus.axa.model.cases.home.AbstractPolicyDto;
import de.mehrwerk.octopus.axa.model.cases.home.PolicyEligibilityTokenDto;
import de.mehrwerk.octopus.axa.model.cases.home.PolicyPartnerIdDto;
import de.mehrwerk.octopus.axa.model.cases.home.PolicyPartnerReferenceDto;

import java.io.IOException;

/**
 * Custom deserializer for {@link AbstractPolicyDto} to handle different policy types based on the presence of specific fields.
 */
public class PolicyDeserializer extends JsonDeserializer<AbstractPolicyDto> {
    private static final ObjectMapper mapper = new ObjectMapper()
            .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

    @Override
    public AbstractPolicyDto deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException {
        JsonNode node = p.getCodec().readTree(p);

        if (node.isTextual()) {
            node = mapper.readTree(node.asText());
        }

        if (node.has("external_policy_number") && node.has("partner_reference") && node.has("package_id")) {
            return mapper.treeToValue(node, PolicyPartnerReferenceDto.class);
        } else if (node.has("external_policy_number") && node.has("partner_id") && node.has("package_id")) {
            return mapper.treeToValue(node, PolicyPartnerIdDto.class);
        } else if (node.has("policy_id") && node.has("eligibility_token")) {
            return mapper.treeToValue(node, PolicyEligibilityTokenDto.class);
        }

        throw new JsonMappingException(p, "Cannot determine Policy type from JSON");
    }
}
