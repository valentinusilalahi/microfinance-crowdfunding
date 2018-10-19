package com.silalahi.valentinus.fintech.core.command;

import reactor.core.publisher.Mono;

public interface BlockingCommand<REQ, RES> extends Command<REQ, RES> {

	@Override
	default Mono<RES> execute(REQ request) {
		return mono(() -> doExecute(request));
	}

	RES doExecute(REQ request);

}
