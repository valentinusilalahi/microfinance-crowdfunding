package com.silalahi.valentinus.fintech.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties("microfinance.web.paging")
public class PagingProperties {

	private PagingKeyProperties keys = new PagingKeyProperties();
	private PagingValueProperties values = new PagingValueProperties();

	@Data
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class PagingKeyProperties {
		private String page = "page";
		private String size = "size";
		private String sorts = "sorts";
	}

	@Data
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class PagingValueProperties {
		private Integer page = 1;
		private Integer size = 10;
	}

}
