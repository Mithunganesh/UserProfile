package com.rade.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.rade.api.exception.SecureAPIAccessDeniedHandler;
import com.rade.api.filters.APISecurityJWTFilter;

@EnableWebSecurity
@Configuration
public class APISecurityConfigurer extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService apiUserDetailsService;

	@Autowired
	APISecurityJWTFilter jwtRequestFilter;

	@Autowired
	SecureAPIAccessDeniedHandler accessDeniedHandler;

	@Autowired
	APISecurityAuthEntryPoint authEntryPoint;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(apiUserDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/authenticate").permitAll().anyRequest().authenticated()
				.antMatchers("/user/add").hasRole("ADMIN").antMatchers("/user/get/*").hasAnyRole("ADMIN", "USER").and()
				.formLogin().and().exceptionHandling().and().exceptionHandling()
				.accessDeniedHandler(accessDeniedHandler).authenticationEntryPoint(authEntryPoint).and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();

	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

}
