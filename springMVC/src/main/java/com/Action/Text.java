package com.Action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.Service.TestService;

@Controller
public class Text {
	
	@Autowired
	private TestService service;
	
	@RequestMapping("/test")
	public String Test(Model model){
		model.addAttribute("name", "freemaker is successful");
		service.add();
		
		return "/system/permission/login";
	}

}
