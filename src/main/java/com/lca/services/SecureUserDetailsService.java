package com.lca.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.lca.entities.LCAUser;
import com.lca.entities.UserRepository;

public class SecureUserDetailsService {
	
	
	private final UserRepository userRepository;
	
	@Autowired
	public SecureUserDetailsService (UserRepository userRepository){
		this.userRepository = userRepository;
	}

//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		Optional<LCAUser> user = userRepository.findByUserName(username);
//		if (user.isEmpty()) {
//			return null;
//		}
//		
//		User.builder()
//			.username(user.get().getUserName())
//			.password(user.get().getPassword())
//			
//	}

}
