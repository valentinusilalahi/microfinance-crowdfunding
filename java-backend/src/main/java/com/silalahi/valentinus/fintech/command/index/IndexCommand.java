package com.silalahi.valentinus.fintech.command.index;

import com.silalahi.valentinus.fintech.core.command.BlockingCommand;
import com.silalahi.valentinus.fintech.model.command.index.IndexCommandRequest;
import com.silalahi.valentinus.fintech.model.command.index.IndexCommandResponse;

public interface IndexCommand extends BlockingCommand<IndexCommandRequest, IndexCommandResponse> {

}
