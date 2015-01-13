package com.Action;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Text {
	
	
	@RequestMapping("/test")
	public String Test(Model model){
		model.addAttribute("name", "freemaker is successful");
		return "/freemarker";
	}

}
