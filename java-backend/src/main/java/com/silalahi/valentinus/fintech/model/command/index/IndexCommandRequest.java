package com.silalahi.valentinus.fintech.model.command.index;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IndexCommandRequest {

	@NotBlank(message="NotBlank")
	private String name;

}
