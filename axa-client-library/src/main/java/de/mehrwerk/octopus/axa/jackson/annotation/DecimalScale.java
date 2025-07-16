package de.mehrwerk.octopus.axa.jackson.annotation;

import de.mehrwerk.octopus.axa.jackson.validator.DecimalScaleValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * This the decimal scale validation annotation.
 * The field requires either a scale of 2 or 3.
 */
@Documented
@Constraint(validatedBy = DecimalScaleValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DecimalScale {
    String message() default "Decimal scale must be between 2 and 3";

    int[] allowedScales() default {2, 3};

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
