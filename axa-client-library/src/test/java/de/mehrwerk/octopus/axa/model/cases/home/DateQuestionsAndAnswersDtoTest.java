package de.mehrwerk.octopus.axa.model.cases.home;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class DateQuestionsAndAnswersDtoTest {

    private Validator validator;
    private LocalDate validDate;

    @BeforeEach
    void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        validDate = LocalDate.now();
    }

    @Test
    void testQuestionType_Valid() {
        DateQuestionsAndAnswersDto dto = new DateQuestionsAndAnswersDto()
                .setQuestionType("DATE");
        Set<ConstraintViolation<DateQuestionsAndAnswersDto>> violations = validator.validateProperty(dto, "questionType");
        assertThat(violations).isEmpty();
    }

    @Test
    void testQuestionType_Null() {
        DateQuestionsAndAnswersDto dto = new DateQuestionsAndAnswersDto()
                .setQuestionType(null);
        Set<ConstraintViolation<DateQuestionsAndAnswersDto>> violations = validator.validateProperty(dto, "questionType");
        assertThat(violations).isNotEmpty();
    }

    @Test
    void testQuestionType_Blank() {
        DateQuestionsAndAnswersDto dto = new DateQuestionsAndAnswersDto()
                .setQuestionType("   ");
        Set<ConstraintViolation<DateQuestionsAndAnswersDto>> violations = validator.validateProperty(dto, "questionType");
        assertThat(violations).isNotEmpty();
    }

    @Test
    void testAnsweredDate_Valid() {
        DateQuestionsAndAnswersDto dto = new DateQuestionsAndAnswersDto()
                .setAnsweredDate(validDate);
        Set<ConstraintViolation<DateQuestionsAndAnswersDto>> violations = validator.validateProperty(dto, "answeredDate");
        assertThat(violations).isEmpty();
    }

    @Test
    void testAnsweredDate_Null() {
        DateQuestionsAndAnswersDto dto = new DateQuestionsAndAnswersDto()
                .setAnsweredDate(null);
        Set<ConstraintViolation<DateQuestionsAndAnswersDto>> violations = validator.validateProperty(dto, "answeredDate");
        assertThat(violations).isNotEmpty();
    }

    @Test
    void testDefaultQuestionType() {
        DateQuestionsAndAnswersDto dto = new DateQuestionsAndAnswersDto();
        assertThat(dto.getQuestionType()).isEqualTo("DATE");
    }
}