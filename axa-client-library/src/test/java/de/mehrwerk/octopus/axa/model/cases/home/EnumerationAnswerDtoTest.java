package de.mehrwerk.octopus.axa.model.cases.home;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class EnumerationAnswerDtoTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void testAnswerId_Valid() {
        EnumerationAnswersDto dto = new EnumerationAnswersDto()
                .setAnswerId("answer1");
        Set<ConstraintViolation<EnumerationAnswersDto>> violations = validator.validateProperty(dto, "answerId");
        assertThat(violations).isEmpty();
    }

    @Test
    void testAnswerId_Null() {
        EnumerationAnswersDto dto = new EnumerationAnswersDto()
                .setAnswerId(null);
        Set<ConstraintViolation<EnumerationAnswersDto>> violations = validator.validateProperty(dto, "answerId");
        assertThat(violations).isNotEmpty();
    }

    @Test
    void testAnswerId_Blank() {
        EnumerationAnswersDto dto = new EnumerationAnswersDto()
                .setAnswerId("   ");
        Set<ConstraintViolation<EnumerationAnswersDto>> violations = validator.validateProperty(dto, "answerId");
        assertThat(violations).isNotEmpty();
    }

    @Test
    void testAnswerId_TooLong() {
        EnumerationAnswersDto dto = new EnumerationAnswersDto()
                .setAnswerId("a".repeat(151));
        Set<ConstraintViolation<EnumerationAnswersDto>> violations = validator.validateProperty(dto, "answerId");
        assertThat(violations).isNotEmpty();
    }

    @Test
    void testAnswerText_Valid() {
        EnumerationAnswersDto dto = new EnumerationAnswersDto()
                .setAnswerText("Valid Answer");
        Set<ConstraintViolation<EnumerationAnswersDto>> violations = validator.validateProperty(dto, "answerText");
        assertThat(violations).isEmpty();
    }

    @Test
    void testAnswerText_Null() {
        EnumerationAnswersDto dto = new EnumerationAnswersDto()
                .setAnswerText(null);
        Set<ConstraintViolation<EnumerationAnswersDto>> violations = validator.validateProperty(dto, "answerText");
        assertThat(violations).isNotEmpty();
    }

    @Test
    void testAnswerText_Blank() {
        EnumerationAnswersDto dto = new EnumerationAnswersDto()
                .setAnswerText("   ");
        Set<ConstraintViolation<EnumerationAnswersDto>> violations = validator.validateProperty(dto, "answerText");
        assertThat(violations).isNotEmpty();
    }

    @Test
    void testAnswerText_TooLong() {
        EnumerationAnswersDto dto = new EnumerationAnswersDto()
                .setAnswerText("a".repeat(101));
        Set<ConstraintViolation<EnumerationAnswersDto>> violations = validator.validateProperty(dto, "answerText");
        assertThat(violations).isNotEmpty();
    }

    @Test
    void testIsSelected_DefaultValue() {
        EnumerationAnswersDto dto = new EnumerationAnswersDto();
        assertThat(dto.getIsSelected()).isFalse();
    }

    @Test
    void testEnumerationAnswersDto_Valid() {
        EnumerationAnswersDto dto = new EnumerationAnswersDto()
                .setAnswerId("answer1")
                .setAnswerText("Valid Answer")
                .setIsSelected(true);
        Set<ConstraintViolation<EnumerationAnswersDto>> violations = validator.validate(dto);
        assertThat(violations).isEmpty();
    }

    @Test
    void testEnumerationAnswersDto_Invalid_AllFields() {
        EnumerationAnswersDto dto = new EnumerationAnswersDto()
                .setAnswerId("")
                .setAnswerText("")
                .setIsSelected(null);
        Set<ConstraintViolation<EnumerationAnswersDto>> violations = validator.validate(dto);
        assertThat(violations).anyMatch(v -> v.getPropertyPath().toString().equals("answerId"));
        assertThat(violations).anyMatch(v -> v.getPropertyPath().toString().equals("answerText"));
    }
}