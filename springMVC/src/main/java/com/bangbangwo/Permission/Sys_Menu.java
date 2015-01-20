package com.bangbangwo.Permission;

import java.util.Date;

import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.stereotype.Component;

@Component
public class Sys_Menu {

	private int id;
	private String en_name;
	private String ch_name;
	private int parent_id;
	private String menu_uri;
	private int sort_index;
	private int status;
	private int level;
	private String description;
	private String dependence;
	private String insert_user;
	private Date insert_date;
	private String update_user;
	private Date update_date;
	private int is_delete;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEn_name() {
		return en_name;
	}
	public void setEn_name(String en_name) {
		this.en_name = en_name;
	}
	public String getCh_name() {
		return ch_name;
	}
	public void setCh_name(String ch_name) {
		this.ch_name = ch_name;
	}
	public int getParent_id() {
		return parent_id;
	}
	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}
	public String getMenu_uri() {
		return menu_uri;
	}
	public void setMenu_uri(String menu_uri) {
		this.menu_uri = menu_uri;
	}
	public int getSort_index() {
		return sort_index;
	}
	public void setSort_index(int sort_index) {
		this.sort_index = sort_index;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDependence() {
		return dependence;
	}
	public void setDependence(String dependence) {
		this.dependence = dependence;
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
	public String getUpdate_user() {
		return update_user;
	}
	public void setUpdate_user(String update_user) {
		this.update_user = update_user;
	}
	public Date getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}
	public int getIs_delete() {
		return is_delete;
	}
	public void setIs_delete(int is_delete) {
		this.is_delete = is_delete;
	}
}
