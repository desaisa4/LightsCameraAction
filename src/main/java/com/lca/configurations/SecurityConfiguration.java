package com.lca.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration{

	@Bean
	public UserDetailsService userDetailsService(PasswordEncoder encoder) {
		
		UserDetails admin = User.withUsername("admin")
							.password(encoder.encode("admin"))
							.roles("ADMIN")
							.build();
		
		UserDetails user = User.withUsername("user")
				.password(encoder.encode("user"))
				.roles("USER")
				.build();
		
		return new InMemoryUserDetailsManager(admin,user);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
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
	
}
