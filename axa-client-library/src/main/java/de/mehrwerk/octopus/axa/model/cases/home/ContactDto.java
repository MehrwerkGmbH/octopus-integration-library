package de.mehrwerk.octopus.axa.model.cases.home;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * Represents a contact
 */
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContactDto {
    /**
     * <p>First name</p>
     */
    @JsonProperty("first_name")
    @Size(max = 100)
    public String firstName;
    /**
     * <p>Last name</p>
     */
    @JsonProperty("last_name")
    @Size(max = 100)
    public String lastName;
    /**
     * <p>Language in which the contact wish to be contacted. List of ISO 639-1 codes.<br/>
     * <a href="https://en.wikipedia.org/wiki/List_of_ISO_639_language_codes">ISO 639-1</a>
     * </p>
     */
    @JsonProperty("preferred_language")
    @Size(min = 2, max = 2)
    @Pattern(regexp = "^[a-z]{2}$", message = "must be a valid ISO 639-1 code (2 lowercase letters)")
    public String preferredLanguage;
    /**
     * <p>Email address of the contact.</p>
     */
    @JsonProperty("email")
    @Email
    public String email;
    /**
     * <p>Contact phone number</p>
     */
    @JsonProperty("phone")
    @NotNull
    @Valid
    public PhoneDto phone;
    /**
     * <p>List of appointments proposed by the contact. N.B. If no values are defined, the appointment is considered to be as soon as possible</p>
     */
    @JsonProperty("appointment_proposals")
    @Valid
    @Size(max = 20)
    public List<AppointmentProposalsDto> appointmentProposals;
}
