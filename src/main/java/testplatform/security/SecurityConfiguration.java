package testplatform.security;

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
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 http.csrf().disable().formLogin().loginPage("/login").permitAll().defaultSuccessUrl("/index")
         .and()
         .authorizeRequests() 
         .anyRequest() 
         .authenticated()
         .and()
         .logout()
         .permitAll();
	}

}
