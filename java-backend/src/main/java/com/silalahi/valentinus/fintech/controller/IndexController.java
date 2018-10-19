package com.silalahi.valentinus.fintech.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.silalahi.valentinus.fintech.command.index.IndexCommand;
import com.silalahi.valentinus.fintech.core.command.CommandExecutor;
import com.silalahi.valentinus.fintech.model.command.index.IndexCommandRequest;
import com.silalahi.valentinus.fintech.model.command.index.IndexCommandResponse;
import com.silalahi.valentinus.fintech.model.controller.WebResponse;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
public class IndexController {

	@Autowired
	private CommandExecutor commandExecutor;

	@GetMapping(
			value = "/", 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<WebResponse<IndexCommandResponse>> index(Principal principal) {
		return commandExecutor.execute(IndexCommand.class, 
				toIndexRequest(principal))
				.map(WebResponse::ok)
				.subscribeOn(Schedulers.elastic());
	}

	private IndexCommandRequest toIndexRequest(Principal principal) {
		return IndexCommandRequest.builder()
				.name(principal.getName())
				.build();
	}

}
