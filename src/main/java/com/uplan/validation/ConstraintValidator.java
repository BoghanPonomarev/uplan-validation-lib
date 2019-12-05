package com.uplan.validation;

import org.springframework.validation.Errors;

/**
 * Class links spring {@link org.springframework.validation.Validator} and generic
 * application validator for more correct dependency injection.
 *
 * @param <T> - type that validates by object instance of this class.
 */
public abstract class ConstraintValidator<T> implements org.springframework.validation.Validator {

    /**
     * Objective representation of <T> type.Necessary for avoiding of
     * ClassCastException in {@link ConstraintValidator#validate(Object, Errors)} method.
     */
    private Class targetClass;

    public ConstraintValidator(Class targetClass) {
        this.targetClass = targetClass;
    }

    /**
     * Always return true cause spring throws exception when type mismatch, even if we don't need it.
     * Main verification occurs in {@link ConstraintValidator#validate(Object, Errors)} method.
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    /**
     * Validates object of {@link ConstraintValidator#targetClass} class with preliminary
     * verification and casting.Delegate main validation to {@link ConstraintValidator#validateConstraint(Object, Errors)}.
     *
     * @param target - object instance that will be validated.
     * @param errors - spring errors object that saves validation mistake values.
     */
    @Override
    public void validate(Object target, Errors errors) {
        if (target.getClass().equals(targetClass)) {
            T castedTarget = (T) target;
            validateConstraint(castedTarget, errors);
        }
    }

    /**
     * Template method that should contain main validation logic for target class.
     */
    public abstract void validateConstraint(T target, Errors errors);
}
