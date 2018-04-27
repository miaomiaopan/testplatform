package testplatform.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author panmiaomiao
 *
 * @date 2018年4月27日
 */
@RestController
public class TestController {
	
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	String hello() {
		return "hello world";
	}
}
