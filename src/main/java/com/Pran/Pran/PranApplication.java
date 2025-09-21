package com.Pran.Pran;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.CrossOrigin;


@SpringBootApplication

@EnableScheduling
public class PranApplication {

	public static void main(String[] args) {
		SpringApplication.run(PranApplication.class, args);
	}

}
