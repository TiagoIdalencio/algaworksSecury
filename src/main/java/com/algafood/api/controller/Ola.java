package com.algafood.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Ola {
	
	@GetMapping("/hello")
	@ResponseBody
	public String hello() {
		return "Olá.";
	}

}
