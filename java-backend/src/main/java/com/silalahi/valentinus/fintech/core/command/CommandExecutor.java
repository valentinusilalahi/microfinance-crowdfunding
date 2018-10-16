package com.silalahi.valentinus.fintech.core.command;

import reactor.core.publisher.Mono;

public interface CommandExecutor {

	<REQ, RES> Mono<RES> execute(Class<? extends Command<REQ, RES>> commandClass, REQ Request);

}
