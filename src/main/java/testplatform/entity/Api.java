package testplatform.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

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

	public enum METHOD {
		POST, GET
	}

	private String name;
	// 协议
	private PROTOCOL protocol;
	// 域名
	private String domain;
	private String path;
	private String params;
	// TODO 扩展body参数
	private String bodyParams;
	private METHOD method;
	private String validateStr;
	@OneToMany(cascade=CascadeType.ALL) 
	@JoinColumn(name="apiId")  
	private List<Header> headers;
	private Integer sleep;

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

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
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

	public METHOD getMethod() {
		return method;
	}

	public void setMethod(METHOD method) {
		this.method = method;
	}

	public String getValidateStr() {
		return validateStr;
	}

	public void setValidateStr(String validateStr) {
		this.validateStr = validateStr;
	}

	public List<Header> getHeaders() {
		return headers;
	}

	public void setHeaders(List<Header> headers) {
		this.headers = headers;
	}

	public Integer getSleep() {
		return sleep;
	}

	public void setSleep(Integer sleep) {
		this.sleep = sleep;
	}

}
