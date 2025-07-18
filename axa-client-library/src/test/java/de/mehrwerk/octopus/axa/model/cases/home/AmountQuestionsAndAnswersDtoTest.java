package de.mehrwerk.octopus.axa.model.cases.home;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class AmountQuestionsAndAnswersDtoTest {

    private Validator validator;
    private AnsweredAmountDto validAmount;

    @BeforeEach
    void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        validAmount = new AnsweredAmountDto()
                .setValue(BigDecimal.valueOf(100.0))
                .setCurrency("EUR");
    }

    @Test
    void testQuestionType_Valid() {
        AmountQuestionsAndAnswersDto dto = new AmountQuestionsAndAnswersDto()
                .setQuestionType("MONETARY_AMOUNT");
        Set<ConstraintViolation<AmountQuestionsAndAnswersDto>> violations = validator.validateProperty(dto, "questionType");
        assertThat(violations).isEmpty();
    }

    @Test
    void testQuestionType_Null() {
        AmountQuestionsAndAnswersDto dto = new AmountQuestionsAndAnswersDto()
                .setQuestionType(null);
        Set<ConstraintViolation<AmountQuestionsAndAnswersDto>> violations = validator.validateProperty(dto, "questionType");
        assertThat(violations).isNotEmpty();
    }

    @Test
    void testQuestionType_Blank() {
        AmountQuestionsAndAnswersDto dto = new AmountQuestionsAndAnswersDto()
                .setQuestionType("   ");
        Set<ConstraintViolation<AmountQuestionsAndAnswersDto>> violations = validator.validateProperty(dto, "questionType");
        assertThat(violations).isNotEmpty();
    }

    @Test
    void testAnsweredAmount_Valid() {
        AmountQuestionsAndAnswersDto dto = new AmountQuestionsAndAnswersDto()
                .setAnsweredAmount(validAmount);
        Set<ConstraintViolation<AmountQuestionsAndAnswersDto>> violations = validator.validateProperty(dto, "answeredAmount");
        assertThat(violations).isEmpty();
    }

    @Test
    void testAnsweredAmount_Null() {
        AmountQuestionsAndAnswersDto dto = new AmountQuestionsAndAnswersDto()
                .setAnsweredAmount(null);
        Set<ConstraintViolation<AmountQuestionsAndAnswersDto>> violations = validator.validateProperty(dto, "answeredAmount");
        assertThat(violations).isNotEmpty();
    }

    @Test
    void testDefaultQuestionType() {
        AmountQuestionsAndAnswersDto dto = new AmountQuestionsAndAnswersDto();
        assertThat(dto.getQuestionType()).isEqualTo("MONETARY_AMOUNT");
    }
}