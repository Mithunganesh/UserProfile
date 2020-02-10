package com.rade.api.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rade.userprofile.dao.User;
import com.rade.userprofile.repository.UserRepository;

@Service
public class APIUserSecurityDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("username :: " + username);
		Optional<User> userEntity = Optional.ofNullable(userRepository.findByFirstName(username));
		
		userEntity.orElseThrow(() -> new UsernameNotFoundException("user name not available"));
		
		return new APIUserDetails(userEntity.get().getFirstName(), userEntity.get().getPassword());
	}

}
