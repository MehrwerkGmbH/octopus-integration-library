package de.mehrwerk.octopus.axa.model.cases.home;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class ContactDtoTest {

    private Validator validator;
    private ContactDto dto;

    @BeforeEach
    void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        PhoneDto validPhone = new PhoneDto()
                .setNumber("1234567890")
                .setInternationalPrefix("49");
        dto = new ContactDto()
                .setFirstName("John")
                .setLastName("Doe")
                .setPreferredLanguage("en")
                .setEmail("john.doe@example.com")
                .setPhone(validPhone)
                .setAppointmentProposals(new ArrayList<>());
    }

    @Test
    void whenAllFieldsValid_thenNoViolations() {
        Set<ConstraintViolation<ContactDto>> violations = validator.validate(dto);
        assertThat(violations).isEmpty();
    }

    @Test
    void whenFirstNameTooLong_thenViolation() {
        dto.setFirstName("a".repeat(101));
        Set<ConstraintViolation<ContactDto>> violations = validator.validate(dto);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getPropertyPath().toString()).isEqualTo("firstName");
    }

    @Test
    void whenLastNameTooLong_thenViolation() {
        dto.setLastName("a".repeat(101));
        Set<ConstraintViolation<ContactDto>> violations = validator.validate(dto);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getPropertyPath().toString()).isEqualTo("lastName");
    }

    @ParameterizedTest
    @ValueSource(strings = {"en", "de", "fr"})
    void whenPreferredLanguageValid_thenNoViolations(String language) {
        dto.setPreferredLanguage(language);
        Set<ConstraintViolation<ContactDto>> violations = validator.validate(dto);
        assertThat(violations).isEmpty();
    }

    @ParameterizedTest
    @ValueSource(strings = {"ENG", "e", "eng", "EN", "123", ""})
    void whenPreferredLanguageInvalid_thenViolation(String language) {
        dto.setPreferredLanguage(language);
        Set<ConstraintViolation<ContactDto>> violations = validator.validate(dto);
        assertThat(violations.size()).isEqualTo(language.length() == 2 ? 1 : 2);
        assertThat(violations.iterator().next().getPropertyPath().toString()).isEqualTo("preferredLanguage");
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "valid@example.com",
            "user.name+tag@example.com",
            "user.name@subdomain.example.com"
    })
    void whenEmailValid_thenNoViolations(String email) {
        dto.setEmail(email);
        Set<ConstraintViolation<ContactDto>> violations = validator.validate(dto);
        assertThat(violations).isEmpty();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "invalid.email",
            "@example.com",
            "user@",
            "user@.com",
            "user@.com."
    })
    void whenEmailInvalid_thenViolation(String email) {
        dto.setEmail(email);
        Set<ConstraintViolation<ContactDto>> violations = validator.validate(dto);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getPropertyPath().toString()).isEqualTo("email");
    }

    @Test
    void whenPhoneNull_thenViolation() {
        dto.setPhone(null);
        Set<ConstraintViolation<ContactDto>> violations = validator.validate(dto);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getPropertyPath().toString()).isEqualTo("phone");
    }

    @Test
    void whenPhoneInvalid_thenViolation() {
        PhoneDto invalidPhone = new PhoneDto(); // missing required fields
        dto.setPhone(invalidPhone);
        Set<ConstraintViolation<ContactDto>> violations = validator.validate(dto);
        assertThat(violations).isNotEmpty();
        assertThat(violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().startsWith("phone")))
                .isTrue();
    }

    @Test
    void whenAppointmentProposalsExceedMaxSize_thenViolation() {
        ArrayList<AppointmentProposalsDto> proposals = new ArrayList<>();
        for (int i = 0; i < 21; i++) {
            proposals.add(new AppointmentProposalsDto()
                    .setStartAt(OffsetDateTime.parse("2024-01-01T10:00:00+01:00"))
                    .setEndAt(OffsetDateTime.parse("2024-01-01T11:00:00+01:00")));
        }
        dto.setAppointmentProposals(proposals);

        Set<ConstraintViolation<ContactDto>> violations = validator.validate(dto);
        System.out.println(violations.toArray().length);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getPropertyPath().toString())
                .isEqualTo("appointmentProposals");
    }

    @Test
    void whenAppointmentProposalsNull_thenNoViolation() {
        dto.setAppointmentProposals(null);
        Set<ConstraintViolation<ContactDto>> violations = validator.validate(dto);
        assertThat(violations).isEmpty();
    }
}