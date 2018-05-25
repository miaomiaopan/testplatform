/**
 * 
 */
package testplatform.entity;

import javax.persistence.Entity;

/**
 * @author panmiaomiao
 *
 * @date 2018年5月25日
 */
@Entity
public class Role extends Base {
	private static final long serialVersionUID = 1L;
	private String role;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
