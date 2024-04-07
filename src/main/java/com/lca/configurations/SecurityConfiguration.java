package com.lca.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.lca.services.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration{

	@Autowired
	CustomUserDetailsService userDetailsService;
	
//	@Autowired
//	public SecurityConfiguration(CustomUserDetailsService userDetailsService){
//		this.userDetailsService = userDetailsService;
//	}
	

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		 http
		 	.csrf(csrf -> csrf.disable())
		 	.authorizeHttpRequests(authorize -> { authorize
			.requestMatchers("/nowPlaying").hasAnyRole("ADMIN","USER")
			.requestMatchers("/myprofile").hasAnyRole("ADMIN","USER")
			.requestMatchers("/signup").permitAll()
			//TODO: Only for testing. Remove after.
			.requestMatchers("/h2-console/**").permitAll()
			.anyRequest().authenticated();

		}).formLogin(formLogin -> formLogin.loginPage("/")
				//This is where spring does the postMapping for the request for username/password authentication.
				.loginProcessingUrl("/login")
				.defaultSuccessUrl("/nowPlaying")
				.permitAll());
		
		//This will allow frames but only for our domain/port
		http.headers(headers -> headers
				.frameOptions( options -> options
						.sameOrigin()));
		 
		 return http.build();
		
	}
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configurationProvider) throws Exception{
    	return configurationProvider.getAuthenticationManager();
    }
    
    @Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(userDetailsService);
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		return daoAuthenticationProvider;
	}
	
}
