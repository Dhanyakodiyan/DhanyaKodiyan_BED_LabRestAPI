package com.greatlearning.collegefest.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.greatlearning.collegefest.Repo.RoleRepo;
import com.greatlearning.collegefest.Repo.UserRepo;
import com.greatlearning.collegefest.service.MyUserDetailsService;


@Configuration
@EnableWebSecurity

public class WebSecurityConfig extends WebSecurityConfiguration{
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Bean
	public PasswordEncoder encoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserDetailsService userDetailsService()
	{
		return new MyUserDetailsService();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
		dao.setUserDetailsService(userDetailsService());
		dao.setPasswordEncoder(encoder());
		return dao;
	}

	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
		.antMatchers(HttpMethod.GET,"/api/students/fecthAllStudents","/api/students/fecthStudentById","/api/students/showformForAdd")
		.hasAnyAuthority("USER","ADMIN")
		.antMatchers(HttpMethod.POST,"/api/students/saveStudent")
		.hasAnyAuthority("USER","ADMIN").and()
		.authorizeRequests()
		.antMatchers("/api/students/showformForUpdate","/api/students/deleteStudentById")
		.hasAuthority("ADMIN").anyRequest().authenticated()
		.and().formLogin().loginProcessingUrl("/login").permitAll()
		.and().logout().logoutSuccessUrl("/login").permitAll().and()
		.exceptionHandling().accessDeniedPage("/api/students/403");
	}
	
	public RoleRepo getRoleRepo() {
		return roleRepo;
	}

	public void setRoleRepo(RoleRepo roleRepo) {
		this.roleRepo = roleRepo;
	}

	public UserRepo getUserRepo() {
		return userRepo;
	}

	public void setUserRepo(UserRepo userRepo) {
		this.userRepo = userRepo;
	}

	@Autowired
	private RoleRepo roleRepo;
	
	@Autowired
	private UserRepo userRepo;

}
