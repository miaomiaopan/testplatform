package testplatform.entity;

import javax.persistence.Entity;
import javax.persistence.Lob;

import testplatform.util.STATUS;

@Entity
public class CaseResult extends Base {
	private static final long serialVersionUID = 1L;

	private STATUS status;
	// 失败原因
	private String reason;
	@Lob
	private String response;

	public STATUS getStatus() {
		return status;
	}

	public void setStatus(STATUS status) {
		this.status = status;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

}
