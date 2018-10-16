package com.silalahi.valentinus.fintech.core.command;

import java.util.Collection;
import java.util.function.Supplier;
import java.util.stream.Stream;

import com.silalahi.valentinus.fintech.util.ReactiveUtils;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface Command<REQ, RES> {

	Mono<RES> execute(REQ request);

	default <T> Mono<T> mono(Supplier<T> supplier) {
		return ReactiveUtils.mono(supplier);
	}

	default <T> Flux<T> fluxStream(Supplier<Stream<? extends T>> supplier) {
		return ReactiveUtils.fluxStream(supplier);
	}

	default <T> Flux<T> flux(Supplier<Collection<? extends T>> supplier) {
		return ReactiveUtils.flux(supplier);
	}

}
