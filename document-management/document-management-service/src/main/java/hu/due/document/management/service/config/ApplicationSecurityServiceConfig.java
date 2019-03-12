package hu.due.document.management.service.config;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import hu.due.document.management.service.security.ApplicationUserDetailsAuthenticationProvider;

@Configuration
public class ApplicationSecurityServiceConfig {
	@Bean(name = "applicationUserDetailsAuthenticationProvider")
	public AuthenticationProvider authenticationProvider() throws NoSuchAlgorithmException {
		return new ApplicationUserDetailsAuthenticationProvider();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}
}
