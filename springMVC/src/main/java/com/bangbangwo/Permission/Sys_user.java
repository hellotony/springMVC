package com.bangbangwo.Permission;

import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Component;

@Component
public class Sys_user {
	private int id;
	private String username;
	private String pwd;
	private Date pwd_terminal_time;
	private String fullname;
	private String phone;
	private String mail;
	private int login_sum;
	private int is_valid;
	private String memo;
	private String insert_user;
	private Date insert_date;
	private String updateUser;
	private Date updateDate;
	private int is_delete;
	
	
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
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public Date getPwd_terminal_time() {
		return pwd_terminal_time;
	}
	public void setPwd_terminal_time(Date pwd_terminal_time) {
		this.pwd_terminal_time = pwd_terminal_time;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public int getLogin_sum() {
		return login_sum;
	}
	public void setLogin_sum(int login_sum) {
		this.login_sum = login_sum;
	}
	public int getIs_valid() {
		return is_valid;
	}
	public void setIs_valid(int is_valid) {
		this.is_valid = is_valid;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getInsert_user() {
		return insert_user;
	}
	public void setInsert_user(String insert_user) {
		this.insert_user = insert_user;
	}
	public Date getInsert_date() {
		return insert_date;
	}
	public void setInsert_date(Date insert_date) {
		this.insert_date = insert_date;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public int getIs_delete() {
		return is_delete;
	}
	public void setIs_delete(int is_delete) {
		this.is_delete = is_delete;
	}

}
