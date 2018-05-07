package testplatform.entity;

import javax.persistence.Entity;

/**
 * @author panmiaomiao
 *
 * @date 2018年4月27日
 */
@Entity
public class TestCase extends Base {
	private static final long serialVersionUID = 1L;

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
