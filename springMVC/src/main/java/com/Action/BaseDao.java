package com.Action;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Dao基础类，所有Dao都继承这个类，包含基本的增删改查方法，继承自它的类可以不写增删改查方法
 * 
 * @author shangxuejin
 * @date Nov 11, 2011 11:58:44 AM
 * @param <T> 模型类，如AdminUser
 */
public abstract class BaseDao<T> {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	protected SqlSessionTemplate sqlSessionTemplate;

	/**
	 * Mapper的命名空间
	 */
	private String namespace;

	/**
	 * 批量插入时，每次插入多少条
	 */
	private int batchSize = 1024;
	
	public static final int IN_QUERY_BATCH_SIZE=2000;//in 查询的Batch 查询次数，Sqlserver最多2100个。

	/**
	 * 向数据库中插入模型<br>
	 * 继承BaseDao后，可以不用写insert方法，而直接使用这个方法。<br>
	 * 要使用这个方法，要求Mapper文件必须放在"com.luolai.ec.services.mapper目录下，<br>
	 * 而且namespace必须为com.luolai.ec.services.mapper.*Mapper，<br>
	 * 其中 * 表示去掉后缀的Dao类名，必须有一个id为insert的语句
	 */
	public int insert(T t) {

		return this.sqlSessionTemplate.insert(this.getMapperNamespace() + "insert", t);
	}

	/**
	 * 插入模型<br>
	 * 继承BaseDao后，可以不用写update方法，而直接使用这个方法。<br>
	 * 要使用这个方法，要求Mapper文件必须放在"com.luolai.ec.services.mapper目录下，<br>
	 * 而且namespace必须为com.luolai.ec.services.mapper.*Mapper，<br>
	 * 其中 * 表示去掉后缀的Dao类名，必须有一个id为${insertStmt}的语句
	 */
	public int insertT(String insertStmt, Map<String, Object> condition) {

		return this.sqlSessionTemplate.insert(this.getMapperNamespace() + insertStmt, condition);
	}

	/**
	 * 批量插入<br>
	 * 使用insert into test(name) values ('a'),('b')...('z');语句，每次插入batchSize(默认1024)条
	 * 要使用这个方法，要求Mapper文件必须放在"com.luolai.ec.services.mapper目录下，<br>
	 * 而且namespace必须为com.luolai.ec.services.mapper.*Mapper(也可以重写getMapperNamespace)，<br>
	 * 其中 * 表示去掉后缀的Dao类名。必须有一个id为batchInsert的插入语句
	 * 
	 * @param 要插入的对象列表
	 * @return 返回影响的行数
	 * @throws InterruptedException
	 */
	public int batchInsertT(List<T> list) {
		if (list == null || list.size() < 1)
			return 0;

		int effect = 0;
		for (int i = 0; i < list.size(); i += batchSize) {

			effect += this.sqlSessionTemplate.insert(this.getMapperNamespace() + "batchInsert",
					list.subList(i, Math.min(i + batchSize, list.size())));
			/*
			if (i % (batchSize * 1024) == 0) {
				try {
					Thread.sleep(5000);
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			*/
		}

		return effect;
	}

	/**
	 * 更新模型<br>
	 * 继承BaseDao后，可以不用写update方法，而直接使用这个方法。<br>
	 * 要使用这个方法，要求Mapper文件必须放在"com.luolai.ec.services.mapper目录下，<br>
	 * 而且namespace必须为com.luolai.ec.services.mapper.*Mapper，<br>
	 * 其中 * 表示去掉后缀的Dao类名，必须有一个id为update的语句
	 */
	public int update(T t) {

		return this.sqlSessionTemplate.update(this.getMapperNamespace() + "update", t);
	}

	/**
	 * 更新模型<br>
	 * 继承BaseDao后，可以不用写update方法，而直接使用这个方法。<br>
	 * 要使用这个方法，要求Mapper文件必须放在"com.luolai.ec.services.mapper目录下，<br>
	 * 而且namespace必须为com.luolai.ec.services.mapper.*Mapper，<br>
	 * 其中 * 表示去掉后缀的Dao类名，必须有一个id为${updateStmt}的语句
	 */
	protected int updateT(String updateStmt, Object value) {

		return this.sqlSessionTemplate.update(this.getMapperNamespace() + updateStmt, value);
	}

	/**
	 * 更新模型<br>
	 * 继承BaseDao后，可以不用写update方法，而直接使用这个方法。<br>
	 * 要使用这个方法，要求Mapper文件必须放在"com.luolai.ec.services.mapper目录下，<br>
	 * 而且namespace必须为com.luolai.ec.services.mapper.*Mapper，<br>
	 * 其中 * 表示去掉后缀的Dao类名，必须有一个id为${updateStmt}的语句
	 */
	protected int updateT(String updateStmt, Map<String, Object> condition) {

		return this.sqlSessionTemplate.update(this.getMapperNamespace() + updateStmt, condition);
	}

	/**
	 * 批量更新<br>
	 * 继承BaseDao后，可以不用写batchUpdate方法，而直接使用这个方法。<br>
	 * 要使用这个方法，要求Mapper文件必须放在"com.luolai.ec.services.mapper目录下，<br>
	 * 而且namespace必须为com.luolai.ec.services.mapper.*Mapper，<br>
	 * 其中 * 表示去掉后缀的Dao类名，必须有一个id为batchUpdate的语句
	 */
	public int batchUpdateT(Map<String, Object> condition) {

		return this.sqlSessionTemplate.update(this.getMapperNamespace() + "batchUpdate", condition);
	}

	/**
	 * 根据id查询模型<br>
	 * 继承BaseDao后，可以不用写getById方法，而直接使用这个方法。<br>
	 * 要使用这个方法，要求Mapper文件必须放在"com.luolai.ec.services.mapper目录下，<br>
	 * 而且namespace必须为com.luolai.ec.services.mapper.*Mapper，<br>
	 * 其中 * 表示去掉后缀的Dao类名，必须有一个id为getById的语句
	 */
	@SuppressWarnings("unchecked")
	public T getById(Integer id) {

		return (T) this.sqlSessionTemplate.selectOne(this.getMapperNamespace() + "getById", id);
	}

	/**
	 * 根据单个条件查询模型<br>
	 * 继承BaseDao后，可以不用写getById方法，而直接使用这个方法。<br>
	 * 要使用这个方法，要求Mapper文件必须放在"com.luolai.ec.services.mapper目录下，<br>
	 * 而且namespace必须为com.luolai.ec.services.mapper.*Mapper，<br>
	 * 其中 * 表示去掉后缀的Dao类名，必须有一个id为<code>statementId</code>的语句
	 */
	@SuppressWarnings("unchecked")
	protected T getT(String statementId, Object value) {

		return (T) this.sqlSessionTemplate.selectOne(this.getMapperNamespace() + statementId, value);
	}

	/**
	 * 根据多个条件查询模型<br>
	 * 继承BaseDao后，可以不用写getById方法，而直接使用这个方法。<br>
	 * 要使用这个方法，要求Mapper文件必须放在"com.luolai.ec.services.mapper目录下，<br>
	 * 而且namespace必须为com.luolai.ec.services.mapper.*Mapper，<br>
	 * 其中 * 表示去掉后缀的Dao类名，必须有一个id为<code>statementId</code>的语句
	 */
	@SuppressWarnings("unchecked")
	protected T getT(String statementId, Map<String, Object> condition) {

		return (T) this.sqlSessionTemplate.selectOne(this.getMapperNamespace() + statementId, condition);
	}

	/**
	 * 根据id删除模型<br>
	 * 继承BaseDao后，可以不用写delete方法，而直接使用这个方法。<br>
	 * 要使用这个方法，要求Mapper文件必须放在"com.luolai.ec.services.mapper目录下，<br>
	 * 而且namespace必须为com.luolai.ec.services.mapper.*Mapper，<br>
	 * 其中 * 表示去掉后缀的Dao类名，必须有一个id为delete的语句
	 */
	public int delete(Integer id) {

		return this.sqlSessionTemplate.delete(this.getMapperNamespace() + "delete", id);
	}

	protected int deleteT(String deleteStmt, Object value) {

		return this.sqlSessionTemplate.delete(this.getMapperNamespace() + deleteStmt, value);
	}

	protected int deleteT(String deleteStmt, Map<String, Object> condition) {

		return this.sqlSessionTemplate.delete(this.getMapperNamespace() + deleteStmt, condition);
	}

	/**
	 * 查询模型列表<br>
	 * 继承BaseDao后，可以不用写getList方法，而直接使用这个方法。<br>
	 * 要使用这个方法，要求Mapper文件必须放在"com.luolai.ec.services.mapper目录下，<br>
	 * 而且namespace必须为com.luolai.ec.services.mapper.*Mapper，<br>
	 * 其中 * 表示去掉后缀的Dao类名，必须有一个id为getAll的语句
	 */
	@SuppressWarnings("unchecked")
	public List<T> getTAll() {

		List<T> list = (List<T>) this.sqlSessionTemplate.selectList(this.getMapperNamespace() + "getAll");

		return list;
	}

	/**
	 * 查询模型列表<br>
	 * 继承BaseDao后，可以不用写getList方法，而直接使用这个方法。<br>
	 * 要使用这个方法，要求Mapper文件必须放在"com.luolai.ec.services.mapper目录下，<br>
	 * 而且namespace必须为com.luolai.ec.services.mapper.*Mapper，<br>
	 * 其中 * 表示去掉后缀的Dao类名，必须有一个id为getList的语句
	 */
	@SuppressWarnings("unchecked")
	public List<T> getTList() {

		List<T> list = (List<T>) this.sqlSessionTemplate.selectList(this.getMapperNamespace() + "getList");

		return list;
	}

	/**
	 * 根据条件查询模型列表<br>
	 * 继承BaseDao后，可以不用写getList方法，而直接使用这个方法。<br>
	 * 要使用这个方法，要求Mapper文件必须放在"com.luolai.ec.services.mapper目录下，<br>
	 * 而且namespace必须为com.luolai.ec.services.mapper.*Mapper，<br>
	 * 其中 * 表示去掉后缀的Dao类名，必须有一个id为getList的语句
	 * 
	 * @param condition 把所有参数都封装到这个map对象里
	 */
	@SuppressWarnings("unchecked")
	public List<T> getTList(Map<String, Object> condition) {

		List<T> list = (List<T>) this.sqlSessionTemplate.selectList(this.getMapperNamespace() + "getList", condition);

		return list;
	}

	/**
	 * 根据语句和条件查询模型列表<br>
	 * 继承BaseDao后，可以不用写getList方法，而直接使用这个方法。<br>
	 * 要使用这个方法，要求Mapper文件必须放在"com.luolai.ec.services.mapper目录下，<br>
	 * 而且namespace必须为com.luolai.ec.services.mapper.*Mapper，<br>
	 * 其中 * 表示去掉后缀的Dao类名，必须有一个id为<code>listStmtId</code>的语句
	 * 
	 * @param listStmtId 查询语句的名称（id）
	 * @param condition 把所有参数都封装到这个map对象里
	 */
	@SuppressWarnings("unchecked")
	protected List<T> getTList(String listStmtId, Object value) {

		List<T> list = (List<T>) this.sqlSessionTemplate.selectList(this.getMapperNamespace() + listStmtId, value);

		return list;
	}

	/**
	 * 根据语句和条件查询模型列表<br>
	 * 继承BaseDao后，可以不用写getList方法，而直接使用这个方法。<br>
	 * 要使用这个方法，要求Mapper文件必须放在"com.luolai.ec.services.mapper目录下，<br>
	 * 而且namespace必须为com.luolai.ec.services.mapper.*Mapper，<br>
	 * 其中 * 表示去掉后缀的Dao类名，必须有一个id为<code>listStmtId</code>的语句
	 * 
	 * @param listStmtId 查询语句的名称（id）
	 * @param condition 把所有参数都封装到这个map对象里
	 */
	@SuppressWarnings("unchecked")
	protected List<T> getTList(String listStmtId, Map<String, Object> condition) {

		List<T> list = (List<T>) this.sqlSessionTemplate.selectList(this.getMapperNamespace() + listStmtId, condition);

		return list;
	}

	/**
	 * 查询模型总数<br>
	 * 继承BaseDao后，可以不用写count方法，而直接使用这个方法。<br>
	 * 要使用这个方法，要求Mapper文件必须放在"com.luolai.ec.services.mapper目录下，<br>
	 * 而且namespace必须为com.luolai.ec.services.mapper.*Mapper，<br>
	 * 其中 * 表示去掉后缀的Dao类名，必须有一个id为count的语句
	 */
	public int count() {

		int count = (Integer) this.sqlSessionTemplate.selectOne(this.getMapperNamespace() + "count");
		return count;
	}

	/**
	 * 根据条件查询模型总数<br>
	 * 继承BaseDao后，可以不用写count方法，而直接使用这个方法。<br>
	 * 要使用这个方法，要求Mapper文件必须放在"com.luolai.ec.services.mapper目录下，<br>
	 * 而且namespace必须为com.luolai.ec.services.mapper.*Mapper，<br>
	 * 其中 * 表示去掉后缀的Dao类名，必须有一个id为count的语句
	 * 
	 * @param condition 把所有参数都封装到这个map对象里
	 */
	public int count(Map<String, Object> condition) {

		int count = (Integer) this.sqlSessionTemplate.selectOne(this.getMapperNamespace() + "count", condition);
		return count;
	}

	/**
	 * 根据语句和条件查询模型总数<br>
	 * 继承BaseDao后，可以不用写count方法，而直接使用这个方法。<br>
	 * 要使用这个方法，要求Mapper文件必须放在"com.luolai.ec.services.mapper目录下，<br>
	 * 而且namespace必须为com.luolai.ec.services.mapper.*Mapper，<br>
	 * 其中 * 表示去掉后缀的Dao类名，必须有一个id为<code>countStmtId</code>的语句
	 * 
	 * @param countStmtId 统计语句的名称（id）
	 * @param condition 把所有参数都封装到这个map对象里
	 */
	protected int count(String countStmtId, Map<String, Object> condition) {
		int count=0;
		Object result=this.sqlSessionTemplate.selectOne(this.getMapperNamespace() + countStmtId, condition);
		if(result!=null){
		  count = (Integer)result; 
		}
		return count;
	}

	/**
	 * 根据语句和条件查询模型总数<br>
	 * 继承BaseDao后，可以不用写getListWithPage方法，而直接使用这个方法。<br>
	 * 要使用这个方法，要求Mapper文件必须放在"com.luolai.ec.services.mapper目录下，<br>
	 * 而且namespace必须为com.luolai.ec.services.mapper.*Mapper，<br>
	 * 其中 * 表示去掉后缀的Dao类名，必须有两个id分别为count和getList的语句
	 * 
	 * @param countStmtId 查询语句的名称（id）
	 * @param condition 把所有参数都封装到这个map对象里
	 */
	public Page<T> getTListWithPage(Map<String, Object> condition) {
		int total = this.count(condition);
		List<T> list = new ArrayList<T>();

		if (total > 0) {
			if (condition.get("offset") == null) {
				condition
						.put("offset", ((Integer) condition.get("pageNo") - 1) * ((Integer) condition.get("pageSize")));
			}
			list = this.getTList(condition);
		}

		Page<T> page = new Page<T>();
		page.setResult(list);
		page.setTotalItems(total);
		page.setPageSize((Integer) condition.get("pageSize"));
		page.setPageNo((Integer) condition.get("pageNo"));
		return page;
	}

	/**
	 * 根据语句和条件查询模型总数<br>
	 * 继承BaseDao后，可以不用写insert方法，而直接使用这个方法。<br>
	 * 要使用这个方法，要求Mapper文件必须放在"com.luolai.ec.services.mapper目录下，<br>
	 * 而且namespace必须为com.luolai.ec.services.mapper.*Mapper，<br>
	 * 其中 * 表示去掉后缀的Dao类名，必须有两个id分别为<code>countStmtId</code>和<code>listStmtId</code>的语句<br>
	 * 
	 * 第一个参数是统计总数的语句，第二个是查询的语句，如果写反了，会抛出异常：<br>
	 * org.mybatis.spring.MyBatisSystemException: <br>
	 * nested exception is org.apache.ibatis.exceptions.TooManyResultsException: <br>
	 * Expected one result (or null) to be returned by selectOne(), but found: 16<br>
	 * 
	 * @param countStmtId 统计语句的名称（id）
	 * @param listStmtId 查询语句的名称（id）
	 * @param condition 把所有参数都封装到这个map对象里
	 */
	protected Page<T> getTListWithPage(String countStmtId, String listStmtId, Map<String, Object> condition) {
		int total = this.count(countStmtId, condition);
		List<T> list = new ArrayList<T>();

		if (total > 0) {
			if (condition.get("offset") == null) {
				condition
						.put("offset", ((Integer) condition.get("pageNo") - 1) * ((Integer) condition.get("pageSize")));
			}
			list = this.getTList(listStmtId, condition);
		}

		Page<T> page = new Page<T>();
		page.setResult(list);
		page.setTotalItems(total);
		page.setPageSize((Integer) condition.get("pageSize"));
		page.setPageNo((Integer) condition.get("pageNo"));
		return page;
	}

	/**
	 * 取得Mapper的命名空间
	 */
	protected String getMapperNamespace() {
		if (namespace == null) {
			namespace = "com.luolai.ec.services.mapper."
					+ this.getClass().getSimpleName().replaceAll("Dao$", "Mapper.");
		}
		return namespace;
	}

	/** (getter for batchSize) */
	public int getBatchSize() {
		return batchSize;
	}

	/** (setter for batchSize) */
	public void setBatchSize(int batchSize) {
		this.batchSize = batchSize;
	}

	/* other method */
	protected int delete(String statement) {
		return this.sqlSessionTemplate.delete(statement);
	}

	protected int delete(String statement, Object parameter) {
		return this.sqlSessionTemplate.delete(statement, parameter);
	}

	protected int insert(String statement) {
		return this.sqlSessionTemplate.insert(statement);
	}

	protected int insert(String statement, Object parameter) {
		return this.sqlSessionTemplate.insert(statement, parameter);
	}

	protected int update(String statement) {
		return this.sqlSessionTemplate.update(statement);
	}

	protected int update(String statement, Object parameter) {
		return this.sqlSessionTemplate.update(statement, parameter);
	}

	@SuppressWarnings("unchecked")
	protected List<T> selectList(String statement) {
		return (List<T>) this.sqlSessionTemplate.selectList(statement);
	}

	protected List<?> selectListByType(String statement) {
		return this.sqlSessionTemplate.selectList(statement);
	}

	@SuppressWarnings("unchecked")
	protected List<T> selectList(String statement, Object parameter) {
		return (List<T>) this.sqlSessionTemplate.selectList(statement, parameter);
	}

	protected List<?> selectListByType(String statement, Object parameter) {
		return this.sqlSessionTemplate.selectList(statement, parameter);
	}

	@SuppressWarnings("unchecked")
	protected List<T> selectList(String statement, Object parameter, RowBounds rowBounds) {
		return (List<T>) this.sqlSessionTemplate.selectList(statement, parameter, rowBounds);
	}

	protected void select(String statement, ResultHandler handler) {
		this.sqlSessionTemplate.select(statement, handler);
	}

	protected void select(String statement, Object parameter, ResultHandler handler) {
		this.sqlSessionTemplate.select(statement, parameter, handler);
	}

	protected void select(String statement, Object parameter, RowBounds rowBounds, ResultHandler handler) {
		this.sqlSessionTemplate.select(statement, parameter, rowBounds, handler);
	}

	protected Object selectOne(String statement) {
		return this.sqlSessionTemplate.selectOne(statement);
	}

	protected Object selectOne(String statement, Object parameter) {
		return this.sqlSessionTemplate.selectOne(statement, parameter);
	}

	protected Map<?, ?> selectMap(String statement, String mapKey) {
		return this.sqlSessionTemplate.selectMap(statement, mapKey);
	}

	protected Map<?, ?> selectMap(String statement, Object parameter, String mapKey) {
		return this.sqlSessionTemplate.selectMap(statement, parameter, mapKey);
	}

	protected Map<?, ?> selectMap(String statement, Object parameter, String mapKey, RowBounds rowBounds) {
		return this.sqlSessionTemplate.selectMap(statement, parameter, mapKey, rowBounds);
	}

	protected void clearCache() {
		this.sqlSessionTemplate.clearCache();
	}

	protected void colse() {
		this.sqlSessionTemplate.close();
	}

	protected void commit() {
		this.sqlSessionTemplate.commit();
	}

	/**
	 * 获取分页对象
	 * <p>
	 * 使用该方法，分页语句中 获取条数参数名称为 pageSize,获取偏移量参数为 #{pageSize}*(#{pageNo}-1)
	 * <p>
	 * 
	 * @param page 包含页码 每页记录条数的分页对象
	 * @param countStatement 查询结果记录统计语句
	 * @param listStatement 分页查询语句
	 * @param parameter 查询条件
	 * @return 分页对象
	 */
	@SuppressWarnings("unchecked")
	protected Page<T> findPage(Page<T> page, String countStatement, String listStatement, Map<String, Object> parameter) {
		long totalItems = (Integer) this.sqlSessionTemplate.selectOne(countStatement, parameter);
		page.setTotalItems(totalItems);
		if (totalItems > 0) {
			parameter.put("pageSize", page.getPageSize());
			parameter.put("pageNo", page.getPageNo());
			parameter.put("offset", (page.getPageNo() - 1) * page.getPageSize());
			List<T> result = (List<T>) this.sqlSessionTemplate.selectList(listStatement, parameter);
			page.setResult(result);
		} else {
			page.setResult(new ArrayList<T>());
		}
		return page;

	}

	/**
	 * 多数据源子类覆盖该方法
	 * 
	 * @param sqlSessionTemplate
	 */
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

}
