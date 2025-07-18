package de.mehrwerk.octopus.axa.model.cases.home;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class DateTimeQuestionsAndAnswersDtoTest {

    private Validator validator;
    private OffsetDateTime validDateTime;

    @BeforeEach
    void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        validDateTime = OffsetDateTime.now();
    }

    @Test
    void testQuestionType_Valid() {
        DateTimeQuestionsAndAnswersDto dto = new DateTimeQuestionsAndAnswersDto()
                .setQuestionType("DATE_TIME");
        Set<ConstraintViolation<DateTimeQuestionsAndAnswersDto>> violations = validator.validateProperty(dto, "questionType");
        assertThat(violations).isEmpty();
    }

    @Test
    void testQuestionType_Null() {
        DateTimeQuestionsAndAnswersDto dto = new DateTimeQuestionsAndAnswersDto()
                .setQuestionType(null);
        Set<ConstraintViolation<DateTimeQuestionsAndAnswersDto>> violations = validator.validateProperty(dto, "questionType");
        assertThat(violations).isNotEmpty();
    }

    @Test
    void testQuestionType_Blank() {
        DateTimeQuestionsAndAnswersDto dto = new DateTimeQuestionsAndAnswersDto()
                .setQuestionType("   ");
        Set<ConstraintViolation<DateTimeQuestionsAndAnswersDto>> violations = validator.validateProperty(dto, "questionType");
        assertThat(violations).isNotEmpty();
    }

    @Test
    void testAnsweredDateTime_Valid() {
        DateTimeQuestionsAndAnswersDto dto = new DateTimeQuestionsAndAnswersDto()
                .setAnsweredDateTime(validDateTime);
        Set<ConstraintViolation<DateTimeQuestionsAndAnswersDto>> violations = validator.validateProperty(dto, "answeredDateTime");
        assertThat(violations).isEmpty();
    }

    @Test
    void testAnsweredDateTime_Null() {
        DateTimeQuestionsAndAnswersDto dto = new DateTimeQuestionsAndAnswersDto()
                .setAnsweredDateTime(null);
        Set<ConstraintViolation<DateTimeQuestionsAndAnswersDto>> violations = validator.validateProperty(dto, "answeredDateTime");
        assertThat(violations).isNotEmpty();
    }

    @Test
    void testDefaultQuestionType() {
        DateTimeQuestionsAndAnswersDto dto = new DateTimeQuestionsAndAnswersDto();
        assertThat(dto.getQuestionType()).isEqualTo("DATE_TIME");
    }
}