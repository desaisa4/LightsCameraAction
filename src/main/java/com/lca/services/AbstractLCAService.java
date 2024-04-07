package com.lca.services;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Component
public abstract class AbstractLCAService implements IServices {
	
	private final String apiBaseUrl = "https://api.themoviedb.org/3/";
	private WebClient webClient = WebClient.builder().baseUrl(apiBaseUrl).build();
	
	private String apiKey;
	
	protected AbstractLCAService (String apiKey) {
		this.apiKey = apiKey;
	}

	protected Mono<String> makeApiCall(String uriPath) {
		// Build and execute the API call. Store as Mono.
		Mono<String> response = webClient.get()
				.uri(uriBuilder -> uriBuilder.path(uriPath)
				.queryParam("api_key",apiKey)
				.build()).retrieve().bodyToMono(String.class);
		
		return response;
	}
	
	public abstract Mono<String> requestData();
}
