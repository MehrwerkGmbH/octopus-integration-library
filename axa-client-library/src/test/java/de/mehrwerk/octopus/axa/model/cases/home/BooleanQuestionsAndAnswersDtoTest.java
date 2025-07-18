package de.mehrwerk.octopus.axa.model.cases.home;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class BooleanQuestionsAndAnswersDtoTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void testQuestionType_Valid() {
        BooleanQuestionsAndAnswersDto dto = new BooleanQuestionsAndAnswersDto()
                .setQuestionType("BOOLEAN");
        Set<ConstraintViolation<BooleanQuestionsAndAnswersDto>> violations = validator.validateProperty(dto, "questionType");
        assertThat(violations).isEmpty();
    }

    @Test
    void testQuestionType_Null() {
        BooleanQuestionsAndAnswersDto dto = new BooleanQuestionsAndAnswersDto()
                .setQuestionType(null);
        Set<ConstraintViolation<BooleanQuestionsAndAnswersDto>> violations = validator.validateProperty(dto, "questionType");
        assertThat(violations).isNotEmpty();
    }

    @Test
    void testQuestionType_Blank() {
        BooleanQuestionsAndAnswersDto dto = new BooleanQuestionsAndAnswersDto()
                .setQuestionType("   ");
        Set<ConstraintViolation<BooleanQuestionsAndAnswersDto>> violations = validator.validateProperty(dto, "questionType");
        assertThat(violations).isNotEmpty();
    }

    @Test
    void testDefaultQuestionType() {
        BooleanQuestionsAndAnswersDto dto = new BooleanQuestionsAndAnswersDto();
        assertThat(dto.getQuestionType()).isEqualTo("BOOLEAN");
    }
}