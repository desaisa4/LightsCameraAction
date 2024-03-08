package com.lca.utils;

import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonParser {
	
	private static ObjectMapper objectMapper = getDefaultObjectMapper();
	
	private static ObjectMapper getDefaultObjectMapper() {
		objectMapper = new ObjectMapper();
		return objectMapper;
	}
	
	public static JsonNode parse(String json) throws IOException{
		JsonNode jsonNode = objectMapper.readTree(json);
		
		return jsonNode;
	}
	
	public String makeJsonPretty(String data) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			Object json = mapper.readValue(data, Object.class);
			String prettyJson = mapper.writeValueAsString(json);
			return prettyJson;
		}catch (Exception e) {
			e.printStackTrace();		
		}
		return null;
	}

}
