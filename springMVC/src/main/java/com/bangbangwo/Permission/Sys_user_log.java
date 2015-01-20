package com.bangbangwo.Permission;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class Sys_user_log {

	private int id;
	private String username;
	private String user_ip;
	private Date login_time;
	private int login_result;
	private String memo;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUser_ip() {
		return user_ip;
	}
	public void setUser_ip(String user_ip) {
		this.user_ip = user_ip;
	}
	public Date getLogin_time() {
		return login_time;
	}
	public void setLogin_time(Date login_time) {
		this.login_time = login_time;
	}
	public int getLogin_result() {
		return login_result;
	}
	public void setLogin_result(int login_result) {
		this.login_result = login_result;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
}
