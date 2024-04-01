package com.lca.configurations;

import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.lca.entities.LCAUser;
import com.lca.entities.Role;
import com.lca.entities.RoleRepository;
import com.lca.entities.UserRepository;

@Component
public class StartupDataInitializer implements CommandLineRunner{
	
	RoleRepository roleRepository;
	UserRepository userRepository;
	
	@Value("${adminpassword}")
	private String adminPassword;
	
	@Value("${adminemail}")
	private String adminEmail;
	
	@Autowired
	public StartupDataInitializer(RoleRepository roleRepository, UserRepository userRepository) {
		this.roleRepository = roleRepository;
		this.userRepository = userRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		isRolePresent("USER");
		isRolePresent("ADMIN");
		createAdminOnStartup();
		
	}
	
	public void isRolePresent(String role ) {
		// if the role doesn't exist, create it.
		Role user = roleRepository.findByName(role);
		if (user==null) {
			Role newRole = new Role();
			newRole.setName(role);
			roleRepository.save(newRole);
		}
	}
	
	public void createAdminOnStartup() {
		//create admin account on startup.
		if (userRepository.findByUserName("admin").isEmpty()) {
			LCAUser admin = new LCAUser();
			admin.setUserName("admin");
			admin.setPassword(adminPassword);
			admin.setEmail(adminEmail);
			Role adminRole = roleRepository.findByName("ADMIN");
			admin.setRoles(Collections.singleton(adminRole));
			userRepository.save(admin);
		}
	}

}
