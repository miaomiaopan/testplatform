package testplatform.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import testplatform.util.StepApiConverter;
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
	public String search(TestCase testCase, Model model, Pageable pageable) {
		if (pageable.getPageNumber() == 0) {
			pageable = PageRequest.of(1, 20);
		}
		Page<TestCase> result = testCaseRepository.search(pageable, testCase);
		model.addAttribute("testCaseArr", result.getContent());
		Map<String, Integer> page = new HashMap<String, Integer>();
		page.put("currentPage", pageable.getPageNumber());
		page.put("totalPages", result.getTotalPages());
		model.addAttribute("page", page);
		return "caseManage";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(TestCase testCase) throws Exception {
		Long id = testCase.getId();
		Date date = new Date();
		if (id == null) {
			testCase.setCreateTime(date);
			testCase = testCaseRepository.save(testCase);
		} else {
			TestCase temp = testCaseRepository.findById(id).get();
			temp.setName(testCase.getName());
			testCase = testCaseRepository.saveAndFlush(temp);
		}

		return "redirect:/case/search";
	}

	@RequestMapping(value = "/saveStep", method = RequestMethod.POST)
	public String saveStep(TestCase testCase) throws Exception {
		Long id = testCase.getId();
		Step tempStep = testCase.getSteps().get(0);
		testCase = testCaseRepository.findById(id).get();
		for (Step step : testCase.getSteps()) {
			if (step.getId().equals(tempStep.getId())) {
				step.setParams(tempStep.getParams());
				step.setBodyParams(tempStep.getBodyParams());
				step.setSleep(tempStep.getSleep());
				step.setValidateStr(tempStep.getValidateStr());
				step.setHeaders(tempStep.getHeaders());
				step.setUpdateTime(new Date());
			}
		}
		testCase = testCaseRepository.saveAndFlush(testCase);

		return "redirect:/case/search";
	}

	@RequestMapping(value = "/deleteStep", method = RequestMethod.POST)
	public String deleteStep(@RequestBody TestCase testCase) throws Exception {
		Long id = testCase.getId();
		Step tempStep = testCase.getSteps().get(0);
		testCase = testCaseRepository.findById(id).get();
		List<Step> steps = testCase.getSteps();
		for (Step step : steps) {
			if (step.getId().equals(tempStep.getId())) {
				steps.remove(step);
				break;
			}
		}
		testCase = testCaseRepository.saveAndFlush(testCase);

		return "redirect:/case/search";
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
	public @ResponseBody CaseResult excute(@PathVariable Long id) throws Exception {
		if (id == null) {
			throw new Exception("id不能为空");
		}
		TestCase testCase = testCaseRepository.getOne(id);
		testCase = TestCaseExcutor.excutor(testCase);
		testCaseRepository.save(testCase);
		List<CaseResult> caseResults = testCase.getCaseResult();
		return caseResults.get(caseResults.size() - 1);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public @ResponseBody void delete(@PathVariable Long id, Model model) throws Exception {
		testCaseRepository.deleteById(id);
	}

	@RequestMapping(value = "/doImportApi", method = RequestMethod.POST)
	public @ResponseBody TestCase doImportApi(@RequestBody TestCase entity) throws Exception {
		TestCase testCase = testCaseRepository.findById(entity.getId()).get();
		Api api = null;
		for (Step step : entity.getSteps()) {
			api = apiRepository.findById(step.getApi().getId()).get();
			testCase.getSteps().add(StepApiConverter.ApiConvertToStep(api));
		}

		testCaseRepository.saveAndFlush(testCase);

		return testCase;
	}
	
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public @ResponseBody TestCase get(@PathVariable Long id, Model model) throws Exception {
		TestCase testCase = testCaseRepository.findById(id).get();
		return testCase;
	}

}
