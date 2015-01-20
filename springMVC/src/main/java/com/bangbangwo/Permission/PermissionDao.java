package com.bangbangwo.Permission;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Component;

import com.Action.BaseDao;

@Component
public class PermissionDao {
	@Resource
	private SqlSessionTemplate sqlSessionTemplate;

	private Map<String, Object> map =  new  HashMap<String, Object>();
	public int checkLogin(String username,String pwd){
		map.put("username", username);
		map.put("pwd", pwd);
		int i = this.sqlSessionTemplate.selectOne("Sys_user_Mapper.loginCheck", map);
		return i;
		
		//return (int) this.sqlSessionTemplate.selectOne("Sys_user_Mapper.loginCheck", map);
	}
	
	
	
}
