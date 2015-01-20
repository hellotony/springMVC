package com.bangbangwo.Permission;

import org.springframework.stereotype.Component;

@Component
public class Sys_role_user {
	
	private int role_id;
	private int user_id;
	
	public int getRole_id() {
		return role_id;
	}
	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
}
