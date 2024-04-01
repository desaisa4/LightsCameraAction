package com.lca.utils;

import java.util.HashMap;
import java.util.Map;

public enum UserAuthErrors {
	
	SIGNUP_USERNAME_ERROR(0, "Invalid Username. Please review the following:"),
	SIGNUP_PASSWORD_ERROR(1, "Invalid Password. Please review the following:"),
	SIGNUP_EMAIL_ERROR(2, "Invalid Email. Please review the following:");
	
	private int code;
	private String message;

	private UserAuthErrors(int code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public String getErrorMessage() {
		return message;
	}
	
	public static Map<String,String> getAllErrors(){
		Map<String,String> errors = new HashMap<String,String>();
		for (UserAuthErrors error : UserAuthErrors.values()) {
			errors.put(error.toString(), error.getErrorMessage());
		}
		return errors;
	}

}
