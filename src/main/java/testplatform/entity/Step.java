package testplatform.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * @author panmiaomiao
 *
 * @date 2018年4月27日
 */
@Entity
public class Step extends Base {
	private static final long serialVersionUID = 1L;
	@ManyToOne
	private Api api;
	private String params;
	private String bodyParams;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "stepId")
	private List<StepResult> stepResultArr;
	private String validateStr;
//	@OneToMany(cascade=CascadeType.ALL) 
//	@JoinColumn(name="stepId")
//	private List<HeaderEntity> headers;

	public Api getApi() {
		return api;
	}

	public void setApi(Api api) {
		this.api = api;
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

	public List<StepResult> getStepResultArr() {
		return stepResultArr;
	}

	public void setStepResultArr(List<StepResult> stepResultArr) {
		this.stepResultArr = stepResultArr;
	}

	public String getValidateStr() {
		return validateStr;
	}

	public void setValidateStr(String validateStr) {
		this.validateStr = validateStr;
	}

//	public List<HeaderEntity> getHeaders() {
//		return headers;
//	}
//
//	public void setHeaders(List<HeaderEntity> headers) {
//		this.headers = headers;
//	}

}
