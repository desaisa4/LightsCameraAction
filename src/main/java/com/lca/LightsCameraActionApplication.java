package com.lca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.lca.controllers.MoviesController;
import com.lca.services.MovieService;

@SpringBootApplication
public class LightsCameraActionApplication {

	public static void main(String[] args) {
		SpringApplication.run(LightsCameraActionApplication.class, args);
		System.out.println("Starting Application...");
	}
}
