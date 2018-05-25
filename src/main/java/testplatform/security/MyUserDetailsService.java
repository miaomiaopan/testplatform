/**
 * 
 */
package testplatform.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import testplatform.repository.UserRepository;

/**
 * @author panmiaomiao
 *
 * @date 2018年5月24日
 */
@Component
public class MyUserDetailsService implements UserDetailsService {
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		testplatform.security.User temp = userRepository.getByUsername(username);
		if(temp == null){
			return null;
		}
		String password = passwordEncoder.encode(temp.getPassword());
		User user = new User(username, password, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
		return user;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
