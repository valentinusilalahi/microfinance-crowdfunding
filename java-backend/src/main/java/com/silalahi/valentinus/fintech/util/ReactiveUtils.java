package com.silalahi.valentinus.fintech.util;

import java.util.Collection;
import java.util.function.Supplier;
import java.util.stream.Stream;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ReactiveUtils {
	public static <T> Mono<T> mono(Supplier<T> supplier) {
		return Mono.fromSupplier(supplier);
	}

	public static <T> Flux<T> fluxStream(Supplier<Stream<? extends T>> supplier) {
		return Flux.fromStream(supplier);
	}

	public static <T> Flux<T> flux(Supplier<Collection<? extends T>> supplier) {
		return fluxStream(() -> supplier.get().stream());
	}
}
