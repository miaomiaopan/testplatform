package testplatform.security;

//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author panmiaomiao
 *
 * @date 2018年5月2日
 */
public class SecurityConfiguration {
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests()
//				// 所有用户均可访问的资源
//				.antMatchers("/css/**", "/js/**", "/images/**", "/webjars/**", "**/favicon.ico", "/index").permitAll()
//				// ROLE_USER的权限才能访问的资源
//				// .antMatchers("/user/**").hasRole("USER")
//				// 任何尚未匹配的URL只需要验证用户即可访问
//				.anyRequest().authenticated().and().formLogin()
//				// 指定登录页面,授予所有用户访问登录页面
//				.loginPage("/login").permitAll().and().headers().frameOptions().sameOrigin();
//
//	}
//
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
//	}
}
