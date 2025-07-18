package de.mehrwerk.octopus.axa.model.cases.home;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import de.mehrwerk.octopus.axa.jackson.deserializer.PolicyDeserializer;
import de.mehrwerk.octopus.axa.jackson.deserializer.RequestAndResponseDeserializer;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * Abstract class representing a home case DTO.
 * This class contains common fields for request and response dto.
 */
@Getter
@Setter
@Accessors(chain = true)
@JsonDeserialize(using = RequestAndResponseDeserializer.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class AbstractHomeCaseDto {
    /**
     * <p>List of case origin, first interaction channel.</p>
     */
    @JsonProperty("case_origin")
    public CaseOrigin caseOrigin;
    /**
     * <p>Incident details</p>
     */
    @JsonProperty("incident")
    @NotNull
    @Valid
    public IncidentDto incident;
    /**
     * <p>Policy details</p>
     */
    @JsonDeserialize(using = PolicyDeserializer.class)
    @JsonProperty("policy")
    @Valid
    public AbstractPolicyDto policy;
    /**
     * <p>Information related to the contact for the case</p>
     */
    @JsonProperty("contact")
    @Valid
    public ContactDto contact;
    /**
     * <p>Information related to the questions and answers provided by the contact</p>
     */
    @JsonProperty("questions_and_answers")
    @Valid
    @Size(max = 100)
    public List<AbstractQuestionsAndAnswersDto> questionsAndAnswers;
    /**
     * <p>Special requirement for the handling of the case</p>
     */
    @JsonProperty("special_requirement")
    @Valid
    public SpecialRequirementDto specialRequirement;

    /**
     * <p>Email, Home Manager, NEO, Phone, Satellite (Crashbox), Web Partner</p>
     */
    public enum CaseOrigin {
        EMAIL,
        HOME_MANAGER,
        NEO,
        PHONE,
        SATELLITE,
        WEB_PARTNER;

        @Override
        public String toString() {
            return switch (this) {
                case EMAIL -> "EMAIL";
                case HOME_MANAGER -> "HOME_MANAGER";
                case NEO -> "NEO";
                case PHONE -> "PHONE";
                case SATELLITE -> "SATELLITE";
                case WEB_PARTNER -> "WEB_PARTNER";
            };
        }
    }
}
