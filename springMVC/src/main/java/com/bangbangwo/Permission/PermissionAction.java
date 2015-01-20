package com.bangbangwo.Permission;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sun.net.www.content.text.plain;

import com.Action.Model;
import com.sun.org.glassfish.gmbal.ParameterNames;

@Controller
@RequestMapping("/Permission")
public class PermissionAction {
	
	@Autowired
	private  PermissionService permissionService;

	@RequestMapping("/login")
	public String login(@RequestParam String username, String pwd,Model model){
		if(permissionService.loginCheck(username,pwd)){
			return "/index";
		}else{
			return "/freemarker";
		}
		
	}

	
}
