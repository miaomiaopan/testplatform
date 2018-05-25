/**
 * 
 */
package testplatform.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import testplatform.entity.Role;
import testplatform.repository.UserRepository;

/**
 * @author panmiaomiao
 *
 * @date 2018年5月24日
 */
@Component
public class MyUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		testplatform.entity.User temp = userRepository.getByUsername(username);
		if (temp == null) {
			throw new UsernameNotFoundException("用户不存在");
		}

		List<Role> roles = temp.getRoles();

		return new MyUserDetails(temp, roles);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
