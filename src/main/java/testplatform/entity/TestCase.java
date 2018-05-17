package testplatform.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

/**
 * @author panmiaomiao
 *
 * @date 2018年4月27日
 */
@Entity
public class TestCase extends Base {
	private static final long serialVersionUID = 1L;

	private String name;
	
	@OneToMany(cascade=CascadeType.ALL) 
	@JoinColumn(name="testcaseId")  
	private List<Step> steps;
	
	@OneToMany(cascade=CascadeType.ALL) 
	@JoinColumn(name="testcaseId")  
	private List<CaseResult> caseResult;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Step> getSteps() {
		return steps;
	}

	public void setSteps(List<Step> steps) {
		this.steps = steps;
	}

	public List<CaseResult> getCaseResult() {
		return caseResult;
	}

	public void setCaseResult(List<CaseResult> caseResult) {
		this.caseResult = caseResult;
	}

}
