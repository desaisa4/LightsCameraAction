package com.lca.controllers;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.lca.DTO.UserAuthDto;
import com.lca.entities.LCAUser;
import com.lca.services.UserService;

@Controller
public class UserController {
	

	
	private final UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	// Need the @ResponseBody here if we want thymeleaf to not think this is a template.
	@GetMapping("/myprofile")
	public String userInformation(@RequestParam(value = "id", defaultValue = "-1") Long id, Model model){
		Optional<LCAUser> user = userService.getUserById(id);
		String message;
		if (user.isPresent()) {
			message = "First Name: "+user.get().getUserName();
		}else {
			message = "Invalid ID. This user does not exist";
		}
		
		model.addAttribute("message",message);
		return "myprofile";
	}
	
	
	/**
	 * Here we want to verify the request to create the new user does not match an existing user.
	 * Make sure that the username is not already used and the email is unique.
	 */
	public boolean isUsernameTaken(String userName) {
		Optional<LCAUser> newUser = userService.getUserByUsername(userName);
		// Return false if the username or email is taken
		if (newUser.isPresent()) {
			return true;
		}
		return false;
	}
	
	public boolean isEmailTaken(String email) {
		Optional<LCAUser> newUser = userService.getUserByEmail(email);
		if (newUser.isPresent()) {
			return true;
		}
		return false;
		
	}
	
}
