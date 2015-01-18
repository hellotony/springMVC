package com.Action;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Component;

@Component
public class Dao {
	
	@Resource
	protected SqlSessionTemplate sqlSessionTemplate;

	public void add() {
		// TODO Auto-generated method stub
		this.sqlSessionTemplate.insert("model.insert");
	}

	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
}
