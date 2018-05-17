package testplatform.entity;

import java.sql.SQLException;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.sql.rowset.serial.SerialException;

import testplatform.util.STATUS;

@Entity
public class StepResult extends Base {
	private static final long serialVersionUID = 1L;
	@Lob
	private String response;
	private STATUS status;
	private String reason;

	public StepResult() {
	};

	public StepResult(String response) throws Exception {
		this.response = response;
	}

	public String getResponse() throws Exception {
		return response;
	}

	public void setResponse(String response) throws SerialException, SQLException {
		this.response = response;
	}

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

}
