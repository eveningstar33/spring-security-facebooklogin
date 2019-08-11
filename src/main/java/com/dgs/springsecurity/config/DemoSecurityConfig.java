package com.dgs.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;

@Configuration
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {
	
	/*
	 * We'll not use UserDetailsManager because you don't want to manage your own users. So the app uses 
	 * the users of another app. So this is why you don't need the UserDetailsManager interface. Instead of
	 * UserDetailsManager we'll use ClientRegistrationRepository. It is similar with UserDetailsManager, but
	 * instead of managing the users which is represented in Spring Security by the UserDetails contract, in 
	 * this case the ClientRegistrationRepository manages ClientRegistrations. And the ClientRegistration is
	 * the object that represents the app in GitHub, Facebook, etc. This is where we need to set the client
	 * id and the client secret. We need to create a ClientRegistration and the ClientRegistration is something
	 * managed by the ClientRegistrationRepository.
	 */
	
	@Bean
	public ClientRegistrationRepository clientRegistrationRepository() {
		
		ClientRegistration c = clientRegistration();
		return new InMemoryClientRegistrationRepository(c);
		
	}
	
	private ClientRegistration clientRegistration() {
		
		return CommonOAuth2Provider.FACEBOOK.getBuilder("facebook")
				.clientId("883192872062774")
				.clientSecret("077222904be9559500d694741142eb0b")
				.build();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
				
		http.authorizeRequests()
				.anyRequest().authenticated()
			.and()
				.oauth2Login();
	}

}
