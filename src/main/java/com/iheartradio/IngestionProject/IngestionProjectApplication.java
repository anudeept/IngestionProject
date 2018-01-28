package com.iheartradio.IngestionProject;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class IngestionProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(IngestionProjectApplication.class, args);
	}
}
