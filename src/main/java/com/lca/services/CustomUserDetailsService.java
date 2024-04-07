package com.lca.services;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lca.entities.LCAUser;
import com.lca.entities.Role;
import com.lca.entities.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	
	private final UserRepository userRepository;
	
	@Autowired
	public CustomUserDetailsService (UserRepository userRepository){
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<LCAUser> user = userRepository.findByUserName(username);
		if (user.isEmpty()) {
			throw new UsernameNotFoundException("Username not found");
		}
		
		return new User(
				user.get().getUserName(), 
				user.get().getPassword(),
				getGrantedAuthorities(user.get().getRoles()));
	}
	
	private Collection<SimpleGrantedAuthority> getGrantedAuthorities(Collection<Role> roles) {
		
		List <SimpleGrantedAuthority> grantedAuthorities = roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_"+role.getName())).collect(Collectors.toList());
		return grantedAuthorities;
	}

}
