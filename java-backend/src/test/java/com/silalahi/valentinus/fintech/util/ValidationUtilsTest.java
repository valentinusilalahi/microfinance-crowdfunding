package com.silalahi.valentinus.fintech.util;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.constraints.NotBlank;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.MapBindingResult;
import org.springframework.validation.Validator;

import com.silalahi.valentinus.fintech.error.ValidationException;

import lombok.Data;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ValidationUtilsTest.Application.class)
public class ValidationUtilsTest {

	@Autowired
	private Validator validator;

	@Autowired
	private javax.validation.Validator beanValidator;

	@Autowired
	private MessageSource messageSource;

	@Test
	public void testError() {
		Model model = new Model();
		MapBindingResult bindingResult = new MapBindingResult(new HashMap<>(), "model");
		validator.validate(model, bindingResult);

		Map<String, List<String>> errors = ValidationUtils.errors(bindingResult, messageSource);
		assertTrue(errors.containsKey("firstName"));
		assertTrue(errors.containsKey("lastName"));
	}
	
	@Test
    public void testBeanValidation() {
        Model model = new Model();
        Set<ConstraintViolation<Model>> constraintViolations = beanValidator.validate(model);
        ValidationException exception = new ValidationException(constraintViolations);

        Map<String, List<String>> errors = ValidationUtils.errors(exception.getConstraintViolations());
        assertTrue(errors.containsKey("firstName"));
        assertTrue(errors.containsKey("lastName"));
    }

	@Data
    class Model {

        @NotBlank
        private String firstName;

        @NotBlank
        private String lastName;

    }

    @SpringBootApplication(
        exclude = {
            FlywayAutoConfiguration.class,
            JpaRepositoriesAutoConfiguration.class,
            DataSourceAutoConfiguration.class
        }
    )
    public static class Application {

    }

}
