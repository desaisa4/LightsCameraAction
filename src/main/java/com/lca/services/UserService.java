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
	
	
	
	public boolean createUser(UserAuthDto userAuthDto) {
		try {
			userRepository.save(new LCAUser(userAuthDto.getNewusername(), userAuthDto.getNewpassword(), userAuthDto.getNewemail()));
			return true;
		}catch(Exception e) {
			System.out.println("Exception occured while attempting to persist User to the DB. " + e);
			return false;
		}
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
