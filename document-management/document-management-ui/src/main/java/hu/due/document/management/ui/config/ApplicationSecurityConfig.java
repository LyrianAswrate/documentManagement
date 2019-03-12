package hu.due.document.management.ui.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// @formatter:off
		http//
				.csrf().disable()//
				.headers().frameOptions().sameOrigin()//
				.and()//
				// Use Vaadin's CSRF protection

				.exceptionHandling() //
				.defaultAuthenticationEntryPointFor(new LoginUrlAuthenticationEntryPoint("/login"),
						new AntPathRequestMatcher("/**")) //
				.and()//
				//
				.authorizeRequests().antMatchers( //
						"/VAADIN/**", //
						"/PUSH/**", //
						"/vaadinServlet/UIDL/**", //
						"/vaadinServlet/HEARTBEAT/**", //
						"/vaadinServlet/APP/**", // s
						"/login", //
						"/login/**", //
						"/main", //
						"/main/**", //
						"/error/**", //
						"/accessDenied/**", //
						"/fileDownloadServlet", //
						"/fileDownloadServlet/**" //
				).permitAll()// - Minden más URL védett!
				.anyRequest().fullyAuthenticated()//
				.and() //
				//
				.logout() //
				.logoutUrl("/logout").logoutSuccessUrl("/login") //
				.permitAll();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/*"); // Static resources are ignored
	}
}