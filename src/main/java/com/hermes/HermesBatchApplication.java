package com.hermes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HermesBatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(HermesBatchApplication.class, args);
	}

}
