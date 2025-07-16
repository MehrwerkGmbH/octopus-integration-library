package de.mehrwerk.octopus.axa.jackson.annotation;

import de.mehrwerk.octopus.axa.jackson.validator.ExclusionReasonRequiredValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * This annotation validates that the exclusionReason field is required when the status is EXCLUDED.
 * It can be applied to classes that represent cases with a status and an exclusion reason.
 */
@Documented
@Constraint(validatedBy = ExclusionReasonRequiredValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidExclusionReason {
    String message() default "exclusionReason is required when status is EXCLUDED";

    int[] allowedScales() default {2, 3};

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
