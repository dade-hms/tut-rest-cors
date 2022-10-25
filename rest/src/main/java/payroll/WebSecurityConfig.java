package payroll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private MyBasicAuthenticationEntryPoint authenticationEntryPoint;

	protected void configure(HttpSecurity http) throws Exception {
		//http.cors();
		http.authorizeRequests()
				.anyRequest()
				.authenticated()
				.and()
				.httpBasic()
				.authenticationEntryPoint(authenticationEntryPoint);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
				.inMemoryAuthentication()
				.withUser("user1")
				.password("{noop}user1Pass")
				.authorities("ROLE_USER");
	}


}
