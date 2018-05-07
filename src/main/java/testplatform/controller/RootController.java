package testplatform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author panmiaomiao
 *
 * @date 2018年4月27日
 */
@Controller
public class RootController {
	
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String hello() {
		return "index";
	}
}
