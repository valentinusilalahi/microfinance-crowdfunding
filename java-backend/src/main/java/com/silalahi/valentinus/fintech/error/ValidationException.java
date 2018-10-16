package com.silalahi.valentinus.fintech.error;

import java.util.Set;

import javax.validation.ConstraintViolation;

import com.silalahi.valentinus.fintech.util.ValidationUtils;

import lombok.Getter;

public class ValidationException extends AppException {
	@Getter
	private Set<ConstraintViolation<?>> constraintViolations;

	@SuppressWarnings("unchecked")
	public ValidationException(Set constraintViolations) {
		this(ValidationUtils.errors(constraintViolations).toString(), constraintViolations);
	}

	@SuppressWarnings("unchecked")
	public ValidationException(String message, Set constraintViolations) {
		super(message);
		this.constraintViolations = constraintViolations;
	}

}
