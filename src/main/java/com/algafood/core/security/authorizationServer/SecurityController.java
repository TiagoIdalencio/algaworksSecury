package com.algafood.core.security.authorizationServer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

	@GetMapping("/entrar")
	public String login() {
		return "pages/entrar";
	}
	
}
