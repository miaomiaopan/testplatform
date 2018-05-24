/**
 * 
 */
package testplatform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author  panmiaomiao
 *
 * @date  2018年5月24日
 */
@Controller
@RequestMapping("/user")
public class UserController {
	@RequestMapping("doLogin")
    public String doLogin() {       
        return "Hello Spring Security";
    }
}
