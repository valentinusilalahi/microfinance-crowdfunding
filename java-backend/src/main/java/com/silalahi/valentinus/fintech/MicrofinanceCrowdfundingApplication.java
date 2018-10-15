package com.silalahi.valentinus.fintech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MicrofinanceCrowdfundingApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicrofinanceCrowdfundingApplication.class, args);
	}
}
