package com.silalahi.valentinus.fintech.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Path;

import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;

public class ValidationUtils {

	/**
	 * Create errors from binding result with default locale
	 *
	 * @param result binding result
	 * @param messageSource message source
	 * @return errors or empty map
	 */
	public static Map<String, List<String>> errors(BindingResult result, 
			MessageSource messageSource) {
		return errors(result, messageSource, Locale.getDefault());
	}

	/**
	 * Create errors from binding result
	 *
	 * @param result binding result
	 * @param messageSource message source
	 * @param locale locale
	 * @return errors or empty map
	 */
	public static Map<String, List<String>> errors(BindingResult result, 
			MessageSource messageSource, Locale locale) {
		if (result.hasFieldErrors()) {
			Map<String, List<String>> map = new HashMap<>();

			result.getFieldErrors().forEach(fieldError -> {
				String field = fieldError.getField();
				if (!map.containsKey(fieldError.getField())) {
					map.put(field, new ArrayList<>());
				}
				String errorMessage = messageSource.getMessage(fieldError.getCode(), 
						fieldError.getArguments(),
						fieldError.getDefaultMessage(), 
						locale);
				map.get(field).add(errorMessage);
			});

			return map;
		} else {
			return Collections.emptyMap();
		}
	}

	/**
	 * Get errors mapping from constrain violations
	 *
	 * @param constraintViolations constrain violations
	 * @return errors
	 */
	public static Map<String, List<String>> errors(Set<ConstraintViolation<?>> constraintViolations) {
		Map<String, List<String>> map = new HashMap<>();

		constraintViolations.forEach(violation -> {
			for (String attribute : getAttributes(violation)) {
				putEntry(map, attribute, violation.getMessage());
			}
		});

		return map;
	}

	private static void putEntry(Map<String, List<String>> map, String key, String value) {
		if (!map.containsKey(key)) {
			map.put(key, new ArrayList<>());
		}
		map.get(key).add(value);
	}

	private static String[] getAttributes(ConstraintViolation<?> constraintViolation) {
		String[] values = (String[]) constraintViolation
				.getConstraintDescriptor()
				.getAttributes().get("path");
		if (values == null || values.length == 0) {
			return getAttributesFromPath(constraintViolation);
		} else {
			return values;
		}
	}

	private static String[] getAttributesFromPath(ConstraintViolation<?> constraintViolation) {
		Path path = constraintViolation.getPropertyPath();

		StringBuilder builder = new StringBuilder();
		path.forEach(node -> {
			if (node.getName() != null) {
				if (builder.length() > 0) {
					builder.append(".");
				}

				builder.append(node.getName());
			}
		});

		return new String[] { builder.toString() };
	}

}
