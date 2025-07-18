package de.mehrwerk.octopus.axa.model.cases.home;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class TextQuestionsAndAnswersDtoTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void testQuestionType_Valid() {
        TextQuestionsAndAnswersDto dto = new TextQuestionsAndAnswersDto()
                .setQuestionType(TextQuestionsAndAnswersDto.QuestionTypeEnum.STRING);
        Set<ConstraintViolation<TextQuestionsAndAnswersDto>> violations = validator.validateProperty(dto, "questionType");
        assertThat(violations).isEmpty();
    }

    @Test
    void testQuestionType_Null() {
        TextQuestionsAndAnswersDto dto = new TextQuestionsAndAnswersDto()
                .setQuestionType(null);
        Set<ConstraintViolation<TextQuestionsAndAnswersDto>> violations = validator.validateProperty(dto, "questionType");
        assertThat(violations).isNotEmpty();
    }

    @Test
    void testAnsweredText_Valid() {
        TextQuestionsAndAnswersDto dto = new TextQuestionsAndAnswersDto()
                .setAnsweredText("A valid answer");
        Set<ConstraintViolation<TextQuestionsAndAnswersDto>> violations = validator.validateProperty(dto, "answeredText");
        assertThat(violations).isEmpty();
    }

    @Test
    void testAnsweredText_Blank() {
        TextQuestionsAndAnswersDto dto = new TextQuestionsAndAnswersDto()
                .setAnsweredText("   ");
        Set<ConstraintViolation<TextQuestionsAndAnswersDto>> violations = validator.validateProperty(dto, "answeredText");
        assertThat(violations).isNotEmpty();
    }

    @Test
    void testAnsweredText_Null() {
        TextQuestionsAndAnswersDto dto = new TextQuestionsAndAnswersDto()
                .setAnsweredText(null);
        Set<ConstraintViolation<TextQuestionsAndAnswersDto>> violations = validator.validateProperty(dto, "answeredText");
        assertThat(violations).isNotEmpty();
    }

    @Test
    void testAnsweredText_TooLong() {
        TextQuestionsAndAnswersDto dto = new TextQuestionsAndAnswersDto()
                .setAnsweredText("a".repeat(2001));
        Set<ConstraintViolation<TextQuestionsAndAnswersDto>> violations = validator.validateProperty(dto, "answeredText");
        assertThat(violations).isNotEmpty();
    }

}