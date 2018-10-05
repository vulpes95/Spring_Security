package eustrosoft.org.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	//
	@GetMapping("/showMyLoginPage")
	public String showMyLoginPage() {
		//return "plain_login";
		return "nice_login";
	}
	
	@GetMapping("/access-denied")
	public String showAccessdenied() {
		//return "plain_login";
		return "access_denied";
	}
	
	
}
