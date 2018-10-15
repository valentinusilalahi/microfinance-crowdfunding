package com.silalahi.valentinus.fintech.error;

import java.util.Set;

import javax.validation.ConstraintViolation;

import lombok.Getter;

public class ValidationException extends AppException {
	@Getter
	private Set<ConstraintViolation<Object>> constraintViolations;

	public ValidationException(Set<ConstraintViolation<Object>> constraintViolations) {
		this.constraintViolations = constraintViolations;
	}

}
