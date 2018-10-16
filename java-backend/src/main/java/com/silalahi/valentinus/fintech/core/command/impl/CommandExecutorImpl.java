package com.silalahi.valentinus.fintech.core.command.impl;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.silalahi.valentinus.fintech.core.command.Command;
import com.silalahi.valentinus.fintech.core.command.CommandExecutor;
import com.silalahi.valentinus.fintech.error.ValidationException;
import com.silalahi.valentinus.fintech.util.ReactiveUtils;

import lombok.Setter;
import reactor.core.publisher.Mono;

@Component
public class CommandExecutorImpl implements CommandExecutor, ApplicationContextAware {

	@Autowired
	private Validator validator;

	@Setter
	private ApplicationContext applicationContext;

	@Override
	public <REQ, RES> Mono<RES> execute(Class<? extends Command<REQ, RES>> commandClass, REQ request) {
		return validateRequestAsMono(request)
				.flatMap(success -> executeCommand(commandClass, request));
	}

	private <REQ, RES> Mono<RES> executeCommand(Class<? extends Command<REQ, RES>> commandClass, REQ request) {
		return applicationContext.getBean(commandClass).execute(request);
	}

	private Mono<Boolean> validateRequestAsMono(Object request) {
		return ReactiveUtils.mono(() -> validateRequest(request));
	}

	private boolean validateRequest(Object request) {
		Set<ConstraintViolation<Object>> constrainViolations = validator.validate(request);
		if (!constrainViolations.isEmpty()) {
			throw new ValidationException(constrainViolations);
		} else {
			return true;
		}
	}

}
