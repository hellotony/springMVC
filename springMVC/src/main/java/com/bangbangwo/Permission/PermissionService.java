package com.bangbangwo.Permission;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.bangbangwo.util.MD5;

@Component
public class PermissionService {

	MD5 md5 = new MD5();
	@Resource
	private PermissionDao permissionDao;
	public boolean loginCheck(String username,String pwd){
		String password = md5.GetMD5Code(pwd);
		
		
		int i = permissionDao.checkLogin(StringUtils.trim(username),StringUtils.trim(password));
		if(i==0){
			return false;
		}else{
			return true;
		}
		
	}

}
