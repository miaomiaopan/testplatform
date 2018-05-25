package testplatform.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author panmiaomiao
 *
 * @date 2018年5月2日
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().formLogin().loginPage("/login").defaultSuccessUrl("/index").permitAll().and()
				//静态资源都可以访问
		        .authorizeRequests().antMatchers("/css/**","/js/**").permitAll()
				.anyRequest().authenticated().and().logout().permitAll().and().logout()
				.logoutSuccessUrl("/login").permitAll()
				.and().rememberMe().userDetailsService(myUserDetailsService);
	}

}
