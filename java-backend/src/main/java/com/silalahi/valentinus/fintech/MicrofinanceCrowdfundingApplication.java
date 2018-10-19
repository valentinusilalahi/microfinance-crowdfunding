package com.silalahi.valentinus.fintech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
@EnableConfigurationProperties({ SecurityProperties.class })
public class MicrofinanceCrowdfundingApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicrofinanceCrowdfundingApplication.class, args);
	}
}
