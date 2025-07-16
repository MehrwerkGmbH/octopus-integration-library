package de.mehrwerk.octopus.axa.jackson.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import de.mehrwerk.octopus.axa.model.cases.home.AbstractPolicyHolderDto;
import de.mehrwerk.octopus.axa.model.cases.home.PolicyHolderCompanyDto;
import de.mehrwerk.octopus.axa.model.cases.home.PolicyHolderPersonalDto;

import java.io.IOException;

/**
 * Custom deserializer for {@link AbstractPolicyHolderDto} to handle different policyholder types based on the presence of specific fields.
 */
public class PolicyHolderDeserializer extends JsonDeserializer<AbstractPolicyHolderDto> {
    @Override
    public AbstractPolicyHolderDto deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException {
        JsonNode node = p.getCodec().readTree(p);

        if (node.has("company_legal_name") || node.has("company_registration")) {
            return p.getCodec().treeToValue(node, PolicyHolderCompanyDto.class);
        } else if (node.has("first_name") || node.has("last_name") || node.has("person_registration")) {
            return p.getCodec().treeToValue(node, PolicyHolderPersonalDto.class);
        }

        throw new JsonMappingException(p, "Cannot determine PolicyHolder type from JSON");
    }
}
