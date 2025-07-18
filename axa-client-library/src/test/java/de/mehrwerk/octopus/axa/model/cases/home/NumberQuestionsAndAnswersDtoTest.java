package de.mehrwerk.octopus.axa.model.cases.home;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class NumberQuestionsAndAnswersDtoTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void testQuestionType_Valid() {
        NumberQuestionsAndAnswersDto dto = new NumberQuestionsAndAnswersDto()
                .setQuestionType("NUMBER");
        Set<ConstraintViolation<NumberQuestionsAndAnswersDto>> violations = validator.validateProperty(dto, "questionType");
        assertThat(violations).isEmpty();
    }

    @Test
    void testQuestionType_Null() {
        NumberQuestionsAndAnswersDto dto = new NumberQuestionsAndAnswersDto()
                .setQuestionType(null);
        Set<ConstraintViolation<NumberQuestionsAndAnswersDto>> violations = validator.validateProperty(dto, "questionType");
        assertThat(violations).isNotEmpty();
    }

    @Test
    void testQuestionType_Blank() {
        NumberQuestionsAndAnswersDto dto = new NumberQuestionsAndAnswersDto()
                .setQuestionType("   ");
        Set<ConstraintViolation<NumberQuestionsAndAnswersDto>> violations = validator.validateProperty(dto, "questionType");
        assertThat(violations).isNotEmpty();
    }

    @Test
    void testAnsweredNumber_Valid() {
        NumberQuestionsAndAnswersDto dto = new NumberQuestionsAndAnswersDto()
                .setAnsweredNumber(42);
        Set<ConstraintViolation<NumberQuestionsAndAnswersDto>> violations = validator.validateProperty(dto, "answeredNumber");
        assertThat(violations).isEmpty();
    }

    @Test
    void testAnsweredNumber_Null() {
        NumberQuestionsAndAnswersDto dto = new NumberQuestionsAndAnswersDto()
                .setAnsweredNumber(null);
        Set<ConstraintViolation<NumberQuestionsAndAnswersDto>> violations = validator.validateProperty(dto, "answeredNumber");
        assertThat(violations).isNotEmpty();
    }
}