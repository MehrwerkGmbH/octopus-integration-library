package de.mehrwerk.octopus.axa.model.cases.home;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class AbstractHomeCaseDtoTest {

    private Validator validator;
    private AbstractHomeCaseDto validDto;
    private IncidentDto validIncident;
    private AbstractPolicyDto validPolicy;
    private ContactDto validContact;
    private AbstractQuestionsAndAnswersDto validQuestionAnswer;
    private SpecialRequirementDto validSpecialRequirement;

    @BeforeEach
    void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();

        validIncident = new IncidentDto()
                .setCause(new CauseDto())
                .setOccurredAt(OffsetDateTime.now())
                .setLocation(new LocationDto());

        validPolicy = new PolicyPartnerReferenceDto()
                .setPolicyHolder(new PolicyHolderPersonalDto());

        validContact = new ContactDto();
        validQuestionAnswer = new TextQuestionsAndAnswersDto();
        validSpecialRequirement = new SpecialRequirementDto();

        validDto = new HomeCaseRequestDto()
                .setCaseOrigin(AbstractHomeCaseDto.CaseOrigin.PHONE)
                .setIncident(validIncident)
                .setPolicy(validPolicy)
                .setContact(validContact)
                .setQuestionsAndAnswers(Arrays.asList(validQuestionAnswer))
                .setSpecialRequirement(validSpecialRequirement);
    }

    @Test
    void testIncident_Null() {
        AbstractHomeCaseDto dto = new HomeCaseRequestDto()
                .setIncident(null);
        Set<ConstraintViolation<AbstractHomeCaseDto>> violations = validator.validate(dto);
        assertThat(violations).hasSize(1);
        assertThat(violations).anyMatch(v -> v.getPropertyPath().toString().equals("incident"));
    }

    @Test
    void testCaseOrigin_AllValues() {
        for (AbstractHomeCaseDto.CaseOrigin origin : AbstractHomeCaseDto.CaseOrigin.values()) {
            AbstractHomeCaseDto dto = new HomeCaseRequestDto()
                    .setCaseOrigin(origin)
                    .setIncident(validIncident);
            Set<ConstraintViolation<AbstractHomeCaseDto>> violations = validator.validate(dto);
            assertThat(violations.size()).isEqualTo(2);
        }
    }

    @Test
    void testPolicy_Invalid() {
        AbstractPolicyDto invalidPolicy = new PolicyPartnerReferenceDto();
        AbstractHomeCaseDto dto = new HomeCaseRequestDto()
                .setIncident(validIncident)
                .setPolicy(invalidPolicy);

        Set<ConstraintViolation<AbstractHomeCaseDto>> violations = validator.validate(dto);
        assertThat(violations.size()).isEqualTo(2);
    }

    @Test
    void testJsonIncludeNonNull() {
        AbstractHomeCaseDto dto = new HomeCaseRequestDto();
        assertThat(dto.getClass().isAnnotationPresent(JsonInclude.class)).isTrue();
        JsonInclude annotation = dto.getClass().getAnnotation(JsonInclude.class);
        assertThat(annotation.value()).isEqualTo(JsonInclude.Include.NON_NULL);
    }
}