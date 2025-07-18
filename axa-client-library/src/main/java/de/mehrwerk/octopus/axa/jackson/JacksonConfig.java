package de.mehrwerk.octopus.axa.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import de.mehrwerk.octopus.axa.jackson.module.RequestAndResponseModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for setting up custom Jackson ObjectMapper.
 */
@Configuration
public class JacksonConfig {
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new RequestAndResponseModule());
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }
}
