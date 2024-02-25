package com.lca.services;

import java.beans.JavaBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import reactor.core.publisher.Mono;

@Component
public class MovieServiceClient {

	@Autowired
	private Environment env;
	
	private final String apiBaseUrl = "https://api.themoviedb.org/3/";
	private final WebClient webClient;
	private static String API_KEY;

	
	public MovieServiceClient() {
		this.webClient = WebClient.builder().baseUrl(apiBaseUrl).build();
		//TODO: This does not work currently.
		//API_KEY = env.getProperty("apikey");
		API_KEY = "YOUR_API_KEY_GOES_HERE";
	}
	
	public void getNowPlaying() {
		
		// Build and execute the API call. Store as Mono.
		Mono<String> response = webClient.get()
				.uri(uriBuilder -> uriBuilder.path("/movie/now_playing")
				.queryParam("api_key", API_KEY)
				.build()).retrieve().bodyToMono(String.class);
		
		//Subscribe when available. Asynchronous.
		response.subscribe(
			data -> {
				try {
					// Make retreived data pretty for printing.
					ObjectMapper mapper = new ObjectMapper();
					mapper.enable(SerializationFeature.INDENT_OUTPUT);
					Object json = mapper.readValue(data, Object.class);
					String prettyJson = mapper.writeValueAsString(json);
					System.out.println(prettyJson);
				} catch (Exception e) {
					e.printStackTrace();
				} 
				
			},
			error -> error.printStackTrace()
		);
		
	}
}
