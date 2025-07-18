package de.mehrwerk.octopus.axa.model.cases.home;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class AbstractQuestionsAndAnswersDtoTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void testQuestionId_Valid() {
        AbstractQuestionsAndAnswersDto dto = new TextQuestionsAndAnswersDto()
                .setQuestionId("Q123");
        Set<ConstraintViolation<AbstractQuestionsAndAnswersDto>> violations = validator.validateProperty(dto, "questionId");
        assertThat(violations).isEmpty();
    }

    @Test
    void testQuestionId_Null() {
        AbstractQuestionsAndAnswersDto dto = new TextQuestionsAndAnswersDto()
                .setQuestionId(null);
        Set<ConstraintViolation<AbstractQuestionsAndAnswersDto>> violations = validator.validateProperty(dto, "questionId");
        assertThat(violations).isNotEmpty();
    }

    @Test
    void testQuestionId_Blank() {
        AbstractQuestionsAndAnswersDto dto = new TextQuestionsAndAnswersDto()
                .setQuestionId("   ");
        Set<ConstraintViolation<AbstractQuestionsAndAnswersDto>> violations = validator.validateProperty(dto, "questionId");
        assertThat(violations).isNotEmpty();
    }

    @Test
    void testQuestionId_TooLong() {
        AbstractQuestionsAndAnswersDto dto = new TextQuestionsAndAnswersDto()
                .setQuestionId("Q".repeat(151));
        Set<ConstraintViolation<AbstractQuestionsAndAnswersDto>> violations = validator.validateProperty(dto, "questionId");
        assertThat(violations).isNotEmpty();
    }

    @Test
    void testQuestionText_Valid() {
        AbstractQuestionsAndAnswersDto dto = new TextQuestionsAndAnswersDto()
                .setQuestionText("Test Question");
        Set<ConstraintViolation<AbstractQuestionsAndAnswersDto>> violations = validator.validateProperty(dto, "questionText");
        assertThat(violations).isEmpty();
    }

    @Test
    void testQuestionText_Null() {
        AbstractQuestionsAndAnswersDto dto = new TextQuestionsAndAnswersDto()
                .setQuestionText(null);
        Set<ConstraintViolation<AbstractQuestionsAndAnswersDto>> violations = validator.validateProperty(dto, "questionText");
        assertThat(violations).isNotEmpty();
    }

    @Test
    void testQuestionText_Blank() {
        AbstractQuestionsAndAnswersDto dto = new TextQuestionsAndAnswersDto()
                .setQuestionText("   ");
        Set<ConstraintViolation<AbstractQuestionsAndAnswersDto>> violations = validator.validateProperty(dto, "questionText");
        assertThat(violations).isNotEmpty();
    }

    @Test
    void testQuestionText_TooLong() {
        AbstractQuestionsAndAnswersDto dto = new TextQuestionsAndAnswersDto()
                .setQuestionText("T".repeat(256));
        Set<ConstraintViolation<AbstractQuestionsAndAnswersDto>> violations = validator.validateProperty(dto, "questionText");
        assertThat(violations).isNotEmpty();
    }
}