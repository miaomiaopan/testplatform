package testplatform.security;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author panmiaomiao
 *
 * @date 2018年5月7日
 */
@Entity
public class User {
	@Id
	private String username;
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
