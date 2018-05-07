package testplatform.entity;

import javax.persistence.Entity;

/**
 * @author panmiaomiao
 *
 * @date 2018年4月28日
 */
@Entity
public class Api extends Base {
	private static final long serialVersionUID = 1L;

	public enum PROTOCOL {
		HTTP, HTTPS
	}

	private String name;
	// 协议
	private PROTOCOL protocol;
	// 域名
	private String domain;
	private String uri;
	private String params;
	private String bodyParams;

	public PROTOCOL getProtocol() {
		return protocol;
	}

	public void setProtocol(PROTOCOL protocol) {
		this.protocol = protocol;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getBodyParams() {
		return bodyParams;
	}

	public void setBodyParams(String bodyParams) {
		this.bodyParams = bodyParams;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
