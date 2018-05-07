package testplatform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import testplatform.entity.TestCase;
import testplatform.repository.CaseRepository;

/**
 * @author panmiaomiao
 *
 * @date 2018年4月27日
 */
@Controller
@RequestMapping(value = "case")
public class CaseController {
	@Autowired
	private CaseRepository caseRepository;

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(Model model) {
		List<TestCase> testCaseArr = caseRepository.findAll();
		model.addAttribute("testCaseArr", testCaseArr);
		return "caseManage";
	}
}
