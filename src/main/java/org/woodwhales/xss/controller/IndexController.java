package org.woodwhales.xss.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

	@GetMapping
	public String index(Model model) {
		model.addAttribute("name", "<script></script>");
		model.addAttribute("description", "<script>alert('hack')</script>");
		return "index";
	}
	
	@GetMapping("/")
	public String index2(Model model) {
		return index(model);
	}
}
