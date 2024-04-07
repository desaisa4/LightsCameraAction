package com.lca.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lca.DTO.UserAuthDto;
import com.lca.entities.LCAUser;
import com.lca.entities.UserRepository;

@Service
public class UserService {
	

	private final UserRepository userRepository;
	
	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public Iterable<LCAUser> getAllUsers(){
		return userRepository.findAll();
	}
	
	public Optional<LCAUser> getUserById(Long id) {
		return userRepository.findById(id);
	}
	
	public Optional<LCAUser> getUserByUsername(String userName){
		return userRepository.findByUserName(userName);
	}
	
	public Optional<LCAUser> getUserByEmail(String email){
		return userRepository.findByEmail(email);
	}

}
