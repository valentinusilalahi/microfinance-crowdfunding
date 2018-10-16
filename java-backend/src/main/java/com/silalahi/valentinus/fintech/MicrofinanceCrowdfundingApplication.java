package com.silalahi.valentinus.fintech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.silalahi.valentinus.fintech.properties.PagingProperties;

@EnableJpaAuditing
@SpringBootApplication
@EnableConfigurationProperties({ PagingProperties.class })
public class MicrofinanceCrowdfundingApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicrofinanceCrowdfundingApplication.class, args);
	}
}
