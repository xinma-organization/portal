package com.xinma.portal.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TagQueryController {

	@RequestMapping(value = "{code}", method = RequestMethod.GET)
	public String tagQuery(@PathVariable String code, Model model) {
		System.out.println(code);
		model.addAttribute("message", "Tag Query!");
		return "query";
	}
}
