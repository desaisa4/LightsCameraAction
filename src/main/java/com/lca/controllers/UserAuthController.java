package com.lca.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.validation.Valid;
import com.lca.DTO.UserAuthDto;
import com.lca.entities.LCAUser;
import com.lca.utils.UserAuthErrors;

/**
 * This class will handle the postMapping for the new user registration. The post Mapping for login authentication is handled by spring security.
 */
@Controller
public class UserAuthController {
	
	Map<String,String> userAuthErrors = UserAuthErrors.getAllErrors();
	
	@Autowired
	UserController userController;
	
	@GetMapping("/")
	public String userAuth(Model model) {
		model.addAttribute("userAuthDto", new UserAuthDto());
		return "userAuth";
	}
	
	@PostMapping("/signup")
	public String signup(@Valid @ModelAttribute ("userAuthDto") UserAuthDto userAuthDto, BindingResult bindingResult, Model model) {
		//Add the error messages that can come up during signup.
		model.addAllAttributes(userAuthErrors);
		
		//Only bother to check if the user is already present if all the requirements for username/password/email are met first.
		if (!bindingResult.hasErrors()) {
			Map <String, String> accountValidityMessages = newUserValidityCheck(userAuthDto);
			model.addAttribute("accountValidityMessages", accountValidityMessages);
		}
		
		
		return ("userAuth");
	}
	
	private Map<String,String> newUserValidityCheck(UserAuthDto userAuthDto) {
		
		//true = user already exists. false = user does not exist.
		// we want to check if the username is taken and if the email is taken.
		
		String UsernameValidity = "Username looks good!";
		String EmailValidity = "Email looks good!";
		//Don't have to do any check for password. Since that will already be checked before.
		String PasswordValidity = "Password looks good!";
		
		Map <String,String> accountValidityMessages = new HashMap<String,String>();
		boolean isUsernameTaken = userController.isUsernameTaken(userAuthDto.getNewusername());
		boolean isEmailTaken = userController.isEmailTaken(userAuthDto.getNewemail());
		
		if (isUsernameTaken) {
			UsernameValidity = "Username is taken. Try another one!";
		}
		
		if (isEmailTaken) {
			EmailValidity = "Email is taken. Try another one!";
		}
		
		if (!(isUsernameTaken && isEmailTaken)) {
			//This means that the Username and Email are not already in use. We can now create our new user.
			userController.addNewUser(userAuthDto);
		}
		
		accountValidityMessages.put("usernameMessage", UsernameValidity);
		accountValidityMessages.put("passwordMessage", PasswordValidity);
		accountValidityMessages.put("emailMessage", EmailValidity);
		
		
		
		return accountValidityMessages;
		
		
		
	}
	
	
	
	
}