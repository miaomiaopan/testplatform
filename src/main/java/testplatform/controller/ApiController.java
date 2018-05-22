package testplatform.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import testplatform.entity.Api;
import testplatform.entity.StepResult;
import testplatform.repository.ApiRepository;
import testplatform.util.ApiExcutor;

/**
 * @author panmiaomiao
 *
 * @date 2018年5月3日
 */
@Controller
@RequestMapping("/api")
public class ApiController {
	@Autowired
	private ApiRepository apiRepository;

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(Api api, Model model, Pageable pageable) {
		if (pageable.getPageNumber() == 0) {
			pageable = PageRequest.of(1, 20);
		}
		Page<Api> result = apiRepository.search(pageable, api);
		model.addAttribute("apiArr", result.getContent());
		Map<String, Integer> page = new HashMap<String, Integer>();
		page.put("currentPage", pageable.getPageNumber());
		page.put("totalPages", result.getTotalPages());
		model.addAttribute("page", page);
		return "apiManage";
	}
	
	@RequestMapping(value = "/importApi", method = RequestMethod.GET)
	public String list(Api api, Model model, Pageable pageable, Long caseId) {
		if (pageable.getPageNumber() == 0) {
			pageable = PageRequest.of(1, 20);
		}
		Page<Api> result = apiRepository.search(pageable, api);
		
		model.addAttribute("apiArr", result.getContent());
		Map<String, Integer> page = new HashMap<String, Integer>();
		page.put("currentPage", pageable.getPageNumber());
		page.put("totalPages", result.getTotalPages());
		model.addAttribute("page", page);
		model.addAttribute("caseId", caseId);
		return "importApi";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Api api) throws Exception {
		Long id = api.getId();
		Date date = new Date();
		if (id == null) {
			api.setCreateTime(date);
			api = apiRepository.save(api);
		} else {
			Api temp = apiRepository.findById(id).get();
			api.setCreateTime(temp.getCreateTime());
			api.setUpdateTime(date);
			api = apiRepository.saveAndFlush(api);
		}

		return "redirect:/api/search";
	}

	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public @ResponseBody Api get(@PathVariable Long id, Model model) throws Exception {
		Api api = apiRepository.findById(id).get();
		return api;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public @ResponseBody void delete(@PathVariable Long id, Model model) throws Exception {
		apiRepository.deleteById(id);
	}

	@RequestMapping(value = "/test/{id}", method = RequestMethod.GET)
	public @ResponseBody StepResult test(@PathVariable Long id, Model model) throws Exception {
		Api api = apiRepository.findById(id).get();
		StepResult stepResult = ApiExcutor.excutor(api);
//		if(!Status.SUCCESS.equals(stepResult.getStatus())){
//			
//		}
		return stepResult;
	}
}
