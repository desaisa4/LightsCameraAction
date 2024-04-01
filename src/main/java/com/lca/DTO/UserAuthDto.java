package com.lca.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserAuthDto {
	
	@NotNull
	@Size(min=5, max=20, message="Username has to be between 5 and 20 characters long.")
	private String newusername;
	@NotNull
	@Size(min=7, max=20, message="Password has to be between 7 and 20 characters long.")
	private String newpassword;
	@NotNull
	@Email(regexp = ".+[@].+[\\.].+", message="Invalid Email.")
	private String newemail;
	
	public String getNewusername() {
		return newusername;
	}
	public void setNewusername(String username) {
		this.newusername = username;
	}
	public String getNewpassword() {
		return newpassword;
	}
	public void setNewpassword(String password) {
		this.newpassword = password;
	}
	public String getNewemail() {
		return newemail;
	}
	public void setNewemail(String email) {
		this.newemail = email;
	}

}
