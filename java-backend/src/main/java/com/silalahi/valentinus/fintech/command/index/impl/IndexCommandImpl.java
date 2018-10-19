package com.silalahi.valentinus.fintech.command.index.impl;

import org.springframework.stereotype.Component;

import com.silalahi.valentinus.fintech.command.index.IndexCommand;
import com.silalahi.valentinus.fintech.model.command.index.IndexCommandRequest;
import com.silalahi.valentinus.fintech.model.command.index.IndexCommandResponse;

@Component
public class IndexCommandImpl implements IndexCommand {

	@Override
	public IndexCommandResponse doExecute(IndexCommandRequest request) {
		// TODO Auto-generated method stub
		return toResponse(request.getName());
	}

	private IndexCommandResponse toResponse(String name) {
		// TODO Auto-generated method stub
		return IndexCommandResponse.builder()
				.hello(String.format("Hello %s", name))
				.build();
	}
	
	

}
