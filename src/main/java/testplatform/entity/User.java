package testplatform.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

/**
 * @author panmiaomiao
 *
 * @date 2018年5月7日
 */
@Entity
public class User extends Base{
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	@ManyToMany(fetch=FetchType.EAGER)
	private List<Role> roles;

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

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
}
