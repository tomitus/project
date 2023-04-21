package backend.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import backend.project.domain.UserDetailServiceImpl;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {

	@Autowired
	private UserDetailServiceImpl userDetailsService;

	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests()
	    .requestMatchers("/", "/home", "/css/**", "/registration", "/saveuser").permitAll() // Allow unauthenticated access to the home endpoint
	    .requestMatchers("/h2-console/**").permitAll()
	    .requestMatchers("/admin/**").hasRole("ADMIN") // Require "ADMIN" role for endpoints matching "/admin/**"
	    .anyRequest().authenticated()
	    .and()
	    .formLogin()
	        .loginPage("/login")
	        .permitAll() // Allow unauthenticated access to the login page
	        .defaultSuccessUrl("/gamelist", true)
	    .and()
	    .logout()
	        .permitAll()
	    .and()
	    .httpBasic();


		return http.build();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());

	}

}
