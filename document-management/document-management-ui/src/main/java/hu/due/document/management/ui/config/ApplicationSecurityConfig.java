package hu.due.document.management.ui.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import hu.due.document.management.ui.login.LoginUI;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
		http//
				.csrf().disable()//
				.headers().frameOptions().sameOrigin()//
				.and()//
				// Use Vaadin's CSRF protection

				.exceptionHandling() //
				.defaultAuthenticationEntryPointFor(new LoginUrlAuthenticationEntryPoint(LoginUI.PATH),
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
				.logoutUrl("/logout").logoutSuccessUrl(LoginUI.PATH) //
				.permitAll();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/*"); // Static resources are ignored
	}

	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider);
	}

}