package com.silalahi.valentinus.fintech.controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.server.ServerWebExchange;

import com.silalahi.valentinus.fintech.core.paging.PagingWebFilter;
import com.silalahi.valentinus.fintech.model.paging.PagingRequest;

public interface PagingController {

	@ModelAttribute(binding = false)
	default PagingRequest getPagingRequest(ServerWebExchange exchange) {
		return exchange.getAttribute(PagingWebFilter.PAGING_REQUEST_ATTRIBUTE);
	}

}
