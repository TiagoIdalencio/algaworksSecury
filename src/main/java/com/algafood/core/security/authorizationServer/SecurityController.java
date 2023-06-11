package com.algafood.core.security.authorizationServer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SecurityController {

	@GetMapping("/entrar")
	public String login() {
		return "pages/entrar";
	}

	@PostMapping("/perm")
	public String perm() {
		return "";
	}
	
}
