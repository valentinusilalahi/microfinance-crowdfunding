package com.silalahi.valentinus.fintech.properties;

import javax.validation.constraints.NotBlank;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties("microfinance.web.paging")
public class SecurityProperties {

	@NotBlank
	private String jwtSigningKey;

}
