package com.silalahi.valentinus.fintech.core.paging;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import com.silalahi.valentinus.fintech.model.paging.PagingDirection;
import com.silalahi.valentinus.fintech.model.paging.PagingRequest;
import com.silalahi.valentinus.fintech.model.paging.PagingSort;
import com.silalahi.valentinus.fintech.properties.PagingProperties;
import com.silalahi.valentinus.fintech.util.ReactiveUtils;

import reactor.core.publisher.Mono;

//detect paging information and set to server web exchange attribute if exists

@Component
public class PagingWebFilter implements WebFilter {

	public static final String PAGING_REQUEST_ATTRIBUTE = "PAGING_REQUEST_ATTRIBUTE";

	private PagingProperties properties;

	@Autowired
	public PagingWebFilter(PagingProperties properties) {
		this.properties = properties;
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
		return createMonoPagingRequest(exchange).doOnNext(value -> setToAttributeIfExists(exchange, value))
				.flatMap(pagingRequest -> chain.filter(exchange));
	}

	private Mono<Optional<PagingRequest>> createMonoPagingRequest(ServerWebExchange exchange) {
		return ReactiveUtils
				.mono(() -> Optional.of(exchange).filter(this::isRequestPagingExists).map(this::getPagingRequest));
	}

	private boolean isRequestPagingExists(ServerWebExchange exchange) {
		return getQueryParams(exchange).containsKey(getPagingKeys().getPage())
				|| getQueryParams(exchange).containsKey(getPagingKeys().getSize())
				|| getQueryParams(exchange).containsKey(getPagingKeys().getSorts());
	}

	private PagingRequest getPagingRequest(ServerWebExchange exchange) {
		return PagingRequest.builder()
				.page(getInteger(getQueryParams(exchange).getFirst(getPagingKeys().getPage()),
						getPageValues().getPage()))
				.size(getInteger(getQueryParams(exchange).getFirst(getPagingKeys().getSize()),
						getPageValues().getSize()))
				.sorts(getSorts(getQueryParams(exchange).getFirst(getPagingKeys().getSorts()))).build();
	}

	private void setToAttributeIfExists(ServerWebExchange exchange, Optional<PagingRequest> value) {
		value.ifPresent(pagingRequest -> exchange.getAttributes().put(PAGING_REQUEST_ATTRIBUTE, pagingRequest));
	}

	private Collection<PagingSort> getSorts(String value) {
		try {
			return Stream.of(value.split(",")).map(sortBy -> {
				String[] strings = sortBy.split(":");
				return PagingSort.builder().field(strings[0])
						.direction(PagingDirection.valueOf(strings[1].toUpperCase())).build();
			}).collect(Collectors.toList());
		} catch (Throwable throwable) {
			return Collections.emptyList();
		}
	}

	private Integer getInteger(String from, Integer defaultValue) {
		try {
			return Integer.valueOf(from);
		} catch (Throwable throwable) {
			return defaultValue;
		}
	}

	private PagingProperties.PagingValueProperties getPageValues() {
		return properties.getValues();
	}

	private PagingProperties.PagingKeyProperties getPagingKeys() {
		return properties.getKeys();
	}

	private MultiValueMap<String, String> getQueryParams(ServerWebExchange exchange) {
		return exchange.getRequest().getQueryParams();
	}
}
