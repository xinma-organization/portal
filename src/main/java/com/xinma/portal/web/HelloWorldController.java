package com.xinma.portal.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorldController {

	@RequestMapping("/hello/world")
	public String helloWorld(Model model) {
		System.out.println("hello world.");
		model.addAttribute("message", "Hello World!");
		return "index";
	}
}
