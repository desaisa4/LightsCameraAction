package com.lca.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.lca.services.MovieService;
import reactor.core.publisher.Mono;
import com.lca.services.AbstractLCAService;

@Controller
public class MoviesController {
	
	@Autowired
	MovieService movieService;
	
	@GetMapping("/nowPlaying")
	public Mono<String> nowPlaying(Model model) {
		
		
		
		Mono<String> nPlaying = movieService.requestData();
		
		//Subscribe when available. Asynchronous.
		return nPlaying.map(
			data -> {
					List<String> titles = null;
					try {
						titles = movieService.parseData(data);
						
					}catch(IOException e) {
						System.out.println("Error: Failed to parse Json string. Unable to create JsonNode object. Check your Json String.");
						e.printStackTrace();
					}
					
					model.addAttribute("titles",titles);
					return "nowplaying";			
		});
	}

}