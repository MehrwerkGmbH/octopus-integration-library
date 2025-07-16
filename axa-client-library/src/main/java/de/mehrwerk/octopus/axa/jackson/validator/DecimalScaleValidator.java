package de.mehrwerk.octopus.axa.jackson.validator;

import de.mehrwerk.octopus.axa.jackson.annotation.DecimalScale;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * Validator for checking if a BigDecimal value has an allowed scale.
 * The allowed scales are defined in the {@link DecimalScale} annotation.
 */
public class DecimalScaleValidator implements ConstraintValidator<DecimalScale, BigDecimal> {
    private int[] allowedScales;

    @Override
    public void initialize(DecimalScale constraintAnnotation) {
        allowedScales = constraintAnnotation.allowedScales();
    }

    @Override
    public boolean isValid(BigDecimal value, ConstraintValidatorContext context) {
        if (value == null) return true;
        return Arrays.stream(allowedScales).anyMatch(scale -> value.scale() == scale);
    }
}
