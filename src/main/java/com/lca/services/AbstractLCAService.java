package com.lca.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Component
public abstract class AbstractLCAService implements IServices {
	
	@Autowired
	private Environment env;

	private final String apiBaseUrl = "https://api.themoviedb.org/3/";
	private WebClient webClient = WebClient.builder().baseUrl(apiBaseUrl).build();
	@Value("${apikey}")
	private static String API_KEY;
	
	protected Mono<String> makeApiCall(String uriPath) {
		// Build and execute the API call. Store as Mono.
		Mono<String> response = webClient.get()
				.uri(uriBuilder -> uriBuilder.path(uriPath)
				.queryParam("api_key", API_KEY)
				.build()).retrieve().bodyToMono(String.class);
		
		return response;
	}
	
	
	public abstract Mono<String> requestData();
}
