package com.silalahi.valentinus.fintech.controller;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.silalahi.valentinus.fintech.error.ValidationException;
import com.silalahi.valentinus.fintech.model.controller.WebResponse;
import com.silalahi.valentinus.fintech.util.ValidationUtils;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ExceptionController {

	@Setter
	private MessageSource messageSource;

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ValidationException.class)
	public WebResponse<Object> validationException(ValidationException exception) {
		log.warn(exception.getMessage(), exception);
		return WebResponse.badRequest(ValidationUtils.errors(exception.getConstraintViolations()));
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public WebResponse<Object> methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        log.warn(exception.getMessage(), exception);
        return WebResponse.badRequest(ValidationUtils.errors(exception.getBindingResult(), messageSource));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public WebResponse<Object> bindException(BindException exception) {
        log.warn(exception.getMessage(), exception);
        return WebResponse.badRequest(ValidationUtils.errors(exception.getBindingResult(), messageSource));
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public WebResponse<Object> throwable(Throwable exception) {
        log.error(exception.getMessage(), exception);
        return WebResponse.status(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
