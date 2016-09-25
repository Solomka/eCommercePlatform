package ukma.eCommerce.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityWebApplicationConfig extends WebSecurityConfigurerAdapter {
	/* *** For project with users' roles ** */
	
	/*
	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}
	 
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");

	}

	protected void configure(HttpSecurity http) throws Exception {
		
		http
		.authorizeRequests()
		.antMatchers("/manager*").access("hasAuthority('MANAGER')") 
		.antMatchers("/cashier*").access("hasAuthority('CASHIER')") 
				.anyRequest().authenticated()
				.and()
				.formLogin().loginPage("/login")
				.usernameParameter("login").passwordParameter("password").permitAll()
				.and()
				.exceptionHandling().accessDeniedPage("/")
				.and()
				.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET")).permitAll()
				.and()
				.csrf().disable();

	}
*/
	/* just to include Spring Security to the project */
	
	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("solomka").password("solomka").roles("USER");
		auth.inMemoryAuthentication().withUser("max").password("max").roles("ADMIN");
			}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
 
	  http.authorizeRequests()
	  	.antMatchers("/", "/hello").permitAll()
	  	//.antMatchers("/", "/hello").access("hasRole('ADMIN')")
	  	.and().formLogin()
		.and().exceptionHandling().accessDeniedPage("/Access_Denied");
 
	}
}
