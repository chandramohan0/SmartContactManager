package com.smart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class MyConfig {

	@Bean
	public UserDetailsService getUserDetailService() {
		return new UserDetailServiceImpl();
	}

//	@Bean
//	public UserDetailsService getUserDetailService() {
//		
//		UserDetails normalUser = User.withUsername("abc@gmail.com").password(passwordEncoder().encode("0987")).roles("USER").build();
//		
//		UserDetails adminUser = User.withUsername("admin@gmail.com").password(passwordEncoder().encode("admin")).roles("ADMIN").build();
//		
//		return new InMemoryUserDetailsManager(normalUser,adminUser);
//		
//		
//	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(getUserDetailService());
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		return daoAuthenticationProvider;
	}




//	@SuppressWarnings("deprecation")
//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		http
//		.authorizeHttpRequests(
//				authorize -> authorize.requestMatchers("/user/**").hasRole("USER").requestMatchers("/admin/**").hasRole("ADMIN").requestMatchers("/**").permitAll().anyRequest().authenticated())
//				.formLogin(withDefaults()).httpBasic(withDefaults());
//		return http.build();
//
//	}
	
	 @SuppressWarnings("deprecation")
	@Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	        http
	            .authorizeRequests(authorize -> authorize
	                .requestMatchers("/user/**").hasRole("USER")
	                .requestMatchers("/admin/**").hasRole("ADMIN")
	                .requestMatchers("/**").permitAll()
	                .anyRequest().authenticated())
	            .formLogin(formLogin -> {
					try {
						formLogin
						    .loginPage("/signin").loginProcessingUrl("/dologin").defaultSuccessUrl("/user/index")
//						    .failureUrl("/login-fail")
//						    .permitAll()
						    .and().csrf().disable();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				})
//	            .logout(formLogout -> {
//					try {
//						formLogout.logoutSuccessUrl("/logout").permitAll().and().csrf().disable();
//					} catch (Exception e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				})
	            .httpBasic(withDefaults());
	        
	        return http.build();
	    }
}
