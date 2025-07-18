package de.mehrwerk.octopus.axa.jackson.module;

import com.fasterxml.jackson.databind.module.SimpleModule;
import de.mehrwerk.octopus.axa.jackson.deserializer.RequestAndResponseDeserializer;
import de.mehrwerk.octopus.axa.model.cases.home.AbstractHomeCaseDto;

public class RequestAndResponseModule extends SimpleModule {
    public RequestAndResponseModule() {
        addDeserializer(AbstractHomeCaseDto.class, new RequestAndResponseDeserializer());
    }
}
