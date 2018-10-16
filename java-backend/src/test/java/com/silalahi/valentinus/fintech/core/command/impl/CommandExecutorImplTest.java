package com.silalahi.valentinus.fintech.core.command.impl;

import static org.junit.Assert.assertEquals;

import javax.validation.constraints.NotBlank;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import com.silalahi.valentinus.fintech.core.command.Command;
import com.silalahi.valentinus.fintech.core.command.CommandExecutor;
import com.silalahi.valentinus.fintech.error.ValidationException;

import lombok.Builder;
import lombok.Data;
import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CommandExecutorImplTest.Application.class)
public class CommandExecutorImplTest {

	@Autowired
	private CommandExecutor commandExecutor;

	@Test(expected = ValidationException.class)
	public void testValidationError() {
		HelloRequest request = HelloRequest.builder().build();
		commandExecutor.execute(HelloCommand.class, request).block();
	}

	@Test
	public void testCommand() {
		HelloRequest request = HelloRequest.builder().name("Silalahi").build();
		HelloResponse response = commandExecutor.execute(HelloCommand.class, request).block();

		assertEquals("Hello Silalahi", response.getResponse());
	}

	@Data
	@Builder
	public static class HelloRequest {

		@NotBlank
		private String name;

	}

	@Data
	@Builder
	public static class HelloResponse {

		private String response;

	}

	public static interface HelloCommand extends Command<HelloRequest, HelloResponse> {

	}

	@SpringBootApplication(exclude = { 
			DataSourceAutoConfiguration.class, 
			JpaRepositoriesAutoConfiguration.class,
			FlywayAutoConfiguration.class })
	public static class Application {

		@Component
		public static class HelloCommandImpl implements HelloCommand {

			@Override
			public Mono<HelloResponse> execute(HelloRequest request) {
				return mono(() -> HelloResponse.builder().response("Hello " + request.getName()).build());
			}

		}

	}

}
