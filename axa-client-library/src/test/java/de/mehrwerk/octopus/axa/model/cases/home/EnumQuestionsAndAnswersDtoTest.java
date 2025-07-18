package de.mehrwerk.octopus.axa.model.cases.home;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class EnumQuestionsAndAnswersDtoTest {

    private Validator validator;
    private EnumerationAnswersDto validAnswer;

    @BeforeEach
    void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        validAnswer = new EnumerationAnswersDto()
                .setAnswerId("answer1")
                .setAnswerText("Valid Answer")
                .setIsSelected(true);
    }

    @Test
    void testQuestionType_Valid() {
        EnumQuestionsAndAnswersDto dto = new EnumQuestionsAndAnswersDto()
                .setQuestionType("ENUM");
        Set<ConstraintViolation<EnumQuestionsAndAnswersDto>> violations = validator.validateProperty(dto, "questionType");
        assertThat(violations).isEmpty();
    }

    @Test
    void testQuestionType_Null() {
        EnumQuestionsAndAnswersDto dto = new EnumQuestionsAndAnswersDto()
                .setQuestionType(null);
        Set<ConstraintViolation<EnumQuestionsAndAnswersDto>> violations = validator.validateProperty(dto, "questionType");
        assertThat(violations).isNotEmpty();
    }

    @Test
    void testQuestionType_Blank() {
        EnumQuestionsAndAnswersDto dto = new EnumQuestionsAndAnswersDto()
                .setQuestionType("   ");
        Set<ConstraintViolation<EnumQuestionsAndAnswersDto>> violations = validator.validateProperty(dto, "questionType");
        assertThat(violations).isNotEmpty();
    }

    @Test
    void testEnumerationAnswers_Valid() {
        EnumQuestionsAndAnswersDto dto = new EnumQuestionsAndAnswersDto()
                .setEnumerationAnswers(List.of(validAnswer));
        Set<ConstraintViolation<EnumQuestionsAndAnswersDto>> violations = validator.validateProperty(dto, "enumerationAnswers");
        assertThat(violations).isEmpty();
    }

    @Test
    void testEnumerationAnswers_Null() {
        EnumQuestionsAndAnswersDto dto = new EnumQuestionsAndAnswersDto()
                .setEnumerationAnswers(null);
        Set<ConstraintViolation<EnumQuestionsAndAnswersDto>> violations = validator.validateProperty(dto, "enumerationAnswers");
        assertThat(violations).isNotEmpty();
    }

    @Test
    void testEnumerationAnswers_Empty() {
        EnumQuestionsAndAnswersDto dto = new EnumQuestionsAndAnswersDto()
                .setEnumerationAnswers(Collections.emptyList());
        Set<ConstraintViolation<EnumQuestionsAndAnswersDto>> violations = validator.validateProperty(dto, "enumerationAnswers");
        assertThat(violations).isNotEmpty();
    }

    @Test
    void testEnumerationAnswers_TooMany() {
        List<EnumerationAnswersDto> tooManyAnswers = new ArrayList<>();
        for (int i = 0; i < 21; i++) {
            tooManyAnswers.add(new EnumerationAnswersDto()
                    .setAnswerId("answer" + i)
                    .setAnswerText("Answer " + i)
                    .setIsSelected(false));
        }

        EnumQuestionsAndAnswersDto dto = new EnumQuestionsAndAnswersDto()
                .setEnumerationAnswers(tooManyAnswers);
        Set<ConstraintViolation<EnumQuestionsAndAnswersDto>> violations = validator.validateProperty(dto, "enumerationAnswers");
        assertThat(violations).isNotEmpty();
    }
}