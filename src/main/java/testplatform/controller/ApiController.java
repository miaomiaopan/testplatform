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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import testplatform.entity.Api;
import testplatform.entity.Api.METHOD;
import testplatform.entity.Api.PROTOCOL;
import testplatform.repository.ApiRepository;
import testplatform.util.HttpUtil;

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

//	@RequestMapping(value = "/test", method = RequestMethod.GET)
//	public @ResponseBody Long test() {
//		Api api = new Api();
//		api.setName("测试");
//		api.setProtocol(PROTOCOL.HTTP);
//		api.setDomain("https://api-qa3.yonghuivip.com");
//		api.setUri("/api/member/signIn");
//		api.setBodyParams("{\"phoneNum\":\"string\",\"securityCode\":\"string\"}");
//		api = apiRepository.save(api);
//		return api.getId();
//	}

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

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody Long save(@RequestBody Api api) throws Exception {
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

		return api.getId();
	}

	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public @ResponseBody Api get(@PathVariable Long id, Model model) throws Exception {
		if (id == null) {
			throw new Exception("id不能为空");
		}
		Api api = apiRepository.findById(id).get();
		return api;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public @ResponseBody void delete(@PathVariable Long id, Model model) throws Exception {
		if (id == null) {
			throw new Exception("id不能为空");
		}
		apiRepository.deleteById(id);
	}

	@RequestMapping(value = "/test/{id}", method = RequestMethod.GET)
	public @ResponseBody String test(@PathVariable Long id, Model model) throws Exception {
		if(id == null){
			throw new Exception("id不能为空");
		}
		Api api = apiRepository.findById(id).get();
		String url = null;
		PROTOCOL protocol = api.getProtocol();
		if(protocol.equals(PROTOCOL.HTTP)){
			url = "http";
		}else if(protocol.equals(PROTOCOL.HTTPS)){
			url = "https";
		}
		url += "://"+api.getDomain()+api.getUri()+"?"+api.getParams();
		METHOD method = api.getMethod();
		String resppnse = null;
		if(method.equals(METHOD.GET)){
			resppnse = HttpUtil.get(url);
		}else if(method.equals(METHOD.POST)){
			resppnse = HttpUtil.post(url, api.getBodyParams());
		}
		return resppnse;
	}
}
