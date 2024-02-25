package com.lca.LightsCameraAction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.lca.services.MovieServiceClient;

@SpringBootApplication
public class LightsCameraActionApplication {

	public static void main(String[] args) {
		SpringApplication.run(LightsCameraActionApplication.class, args);
		
		MovieServiceClient msc = new MovieServiceClient();
		msc.getNowPlaying();
		
	}
	
}
