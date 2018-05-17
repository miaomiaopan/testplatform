package testplatform.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import testplatform.entity.Api;
import testplatform.entity.CaseResult;
import testplatform.entity.Step;
import testplatform.entity.TestCase;
import testplatform.repository.ApiRepository;
import testplatform.repository.TestCaseRepository;
import testplatform.util.TestCaseExcutor;

/**
 * @author panmiaomiao
 *
 * @date 2018年4月27日
 */
@Controller
@RequestMapping(value = "case")
public class TestCaseController {
	@Autowired
	private TestCaseRepository testCaseRepository;
	@Autowired
	private ApiRepository apiRepository;

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(Model model) {
		List<TestCase> testCaseArr = testCaseRepository.findAll();
		model.addAttribute("testCaseArr", testCaseArr);
		return "caseManage";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody Long save(@RequestBody TestCase testCase) throws Exception {
		Long id = testCase.getId();
		Date date = new Date();
		if (id == null) {
			testCase.setCreateTime(date);
			testCase = testCaseRepository.save(testCase);
		} else {
			TestCase temp = testCaseRepository.findById(id).get();
			testCase.setCreateTime(temp.getCreateTime());
			testCase.setUpdateTime(date);
			testCase = testCaseRepository.saveAndFlush(testCase);
		}

		return testCase.getId();
	}

	@RequestMapping(value = "/test", method = RequestMethod.POST)
	public @ResponseBody Long test() throws Exception {
		TestCase testCase = new TestCase();
		Api api1 = apiRepository.getOne(3l);
		Api api2 = apiRepository.getOne(4l);
		List<Step> stepArr = new ArrayList<Step>();
		Step step1 = new Step();
		step1.setApi(api1);
		step1.setParams("wd=step1");
		step1.setCreateTime(new Date());
		stepArr.add(step1);
		Step step2 = new Step();
		step2.setApi(api2);
		step2.setParams("wd=step2");
		step2.setCreateTime(new Date());
		stepArr.add(step2);
		testCase.setCreateTime(new Date());
		testCase.setSteps(stepArr);
		testCase.setName("shLoginAndGetUserInfo");
		testCase = testCaseRepository.save(testCase);

		return testCase.getId();
	}
	
	@RequestMapping(value = "/excute/{id}", method = RequestMethod.GET)
	public @ResponseBody String excute(@PathVariable Long id) throws Exception{
		if (id == null) {
			throw new Exception("id不能为空");
		}
		TestCase testCase = testCaseRepository.getOne(id);
		testCase = TestCaseExcutor.excutor(testCase);
		testCaseRepository.save(testCase);
//		List<CaseResult> caseResultArr = testCase.getCaseResult();
		return "ok";
	}

}
