package com.silalahi.valentinus.fintech.model.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import org.springframework.http.HttpStatus;

import com.silalahi.valentinus.fintech.model.paging.Paging;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WebResponse<T> {
	
	private Integer code;
	private String status;
	private T data;
	private Map<String, List<String>> errors;
	private Paging paging;
	
	public static <T> WebResponse<T> ok(T data){
		return WebResponse.<T>builder()
				.status(HttpStatus.OK.getReasonPhrase())
				.code(HttpStatus.OK.value())
				.data(data)
				.build();
	}
	
	public static <T> WebResponse<T> ok(T data, Paging paging){
		return WebResponse.<T>builder()
				.status(HttpStatus.OK.getReasonPhrase())
				.code(HttpStatus.OK.value())
				.data(data)
				.paging(paging)
				.build();
	}
	
	public static <T> WebResponse<T> badRequest(Map<String, List<String>> errors){
		return WebResponse.<T>builder()
				.status(HttpStatus.BAD_REQUEST.getReasonPhrase())
				.code(HttpStatus.BAD_REQUEST.value())
				.errors(errors)
				.build();
	}
	
	public static <T> WebResponse<T> status(HttpStatus status) {
        return WebResponse.status(null, status);
    }

    public static <T> WebResponse<T> status(@Nullable T data, HttpStatus status) {
        return WebResponse.<T>builder()
            .status(status.getReasonPhrase())
            .code(status.value())
            .data(data)
            .build();
    }

	
}
