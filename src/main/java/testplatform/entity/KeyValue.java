/**
 * 
 */
package testplatform.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author panmiaomiao
 *
 * @date 2018年5月17日
 */
@MappedSuperclass
public class KeyValue implements Serializable {
	private static final long serialVersionUID = 1L;
	@Column(name = "\"key\"", nullable = false) 
	private String key;
	@Column(name = "\"value\"", nullable = false) 
	private String value;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	public KeyValue() {
	}

	public KeyValue(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
