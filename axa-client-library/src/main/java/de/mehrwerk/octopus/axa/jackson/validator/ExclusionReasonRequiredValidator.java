package de.mehrwerk.octopus.axa.jackson.validator;

import de.mehrwerk.octopus.axa.jackson.annotation.ValidExclusionReason;
import de.mehrwerk.octopus.axa.model.cases.home.HomeCaseResponseDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Validator for checking if the exclusion reason is provided when the case status is EXCLUDED.
 * This validator is used in conjunction with the {@link ValidExclusionReason} annotation.
 */
public class ExclusionReasonRequiredValidator implements ConstraintValidator<ValidExclusionReason, HomeCaseResponseDto> {

    @Override
    public boolean isValid(HomeCaseResponseDto obj, ConstraintValidatorContext context) {
        if (obj == null) return true;

        if (obj.getStatus() == HomeCaseResponseDto.Status.EXCLUDED) {
            String reason = obj.getExclusionReason();
            return reason != null && !reason.trim().isEmpty();
        }

        return true;
    }
}
