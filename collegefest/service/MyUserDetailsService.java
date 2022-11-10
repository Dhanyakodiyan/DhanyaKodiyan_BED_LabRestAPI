package com.greatlearning.collegefest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.greatlearning.collegefest.security.MyUserDetails;
import com.greatlearning.collegefest.entity.User;
import com.greatlearning.collegefest.Repo.UserRepo;

@Service
public class MyUserDetailsService implements UserDetailsService{
	

	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUsername(username);
		if(user==null) {
			throw new UsernameNotFoundException("User not found");
		}
		return new MyUserDetails(user);
	

	}

}
