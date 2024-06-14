package com.smart.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.smart.dao.UserRepository;
import com.smart.entities.User;

public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@SuppressWarnings("unused")
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.getUserByUserName(username);

		//set Encoded password if stored password is not already encoded
//		user.setPassword(passwordEncoder.encode(user.getPassword()));

		if (user == null) {
			throw new UsernameNotFoundException("could not found user!!!");

		}

		CustomUserDetail customUserDetail = new CustomUserDetail(user);
		return customUserDetail;
	}

}
