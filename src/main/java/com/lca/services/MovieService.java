package com.lca.services;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.lca.utils.JsonParser;
import reactor.core.publisher.Mono;

@Component
public class MovieService extends AbstractLCAService{
	
	
	
	public MovieService(@Value("${apikey}") String apiKey) {
		super(apiKey);
	}
	
	private Mono<String> getNowPlaying() {
		Mono<String> response = makeApiCall("/movie/now_playing");
		return response; 
	}
	
	public List<String> parseData(String json) throws IOException {
		JsonNode jsonNode = JsonParser.parse(json).get("results");
		List <String> titles = new ArrayList<String>();
		
		for (JsonNode iter : jsonNode) {
			titles.add((iter.get("title")).toString().replaceAll("\"",""));
		}
		return titles;
	}
	
	@Override
	public Mono<String> requestData(){
		return getNowPlaying();
	}
	
}
