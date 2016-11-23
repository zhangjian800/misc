package com.dolphin.webapp.common.dao;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.dolphin.common.exception.ExceptionKeys;
import com.dolphin.common.exception.GenericException;

public abstract class AbstractIbatisDao extends SqlMapClientDaoSupport {
	
	public Object queryForObject(String statementName, Object parameterObject) throws GenericException{
		SqlMapClientTemplate template = getSqlMapClientTemplate();
		try {
			return template.queryForObject(statementName, parameterObject);
		} catch (Exception e) {
			throw new GenericException(ExceptionKeys.DAO_ERROR, e);
		}
	}
	
	public Object queryForList(String statementName, Object parameterObject) throws GenericException{
		SqlMapClientTemplate template = getSqlMapClientTemplate();
    	try {
    		return template.queryForList(statementName, parameterObject);
		} catch (Exception e) {
			throw new GenericException(ExceptionKeys.DAO_ERROR, e);
		}
	}	
	
	public Object queryForList(String statementName) throws GenericException{
		SqlMapClientTemplate template = getSqlMapClientTemplate();
    	try {
    		return template.queryForList(statementName);
		} catch (Exception e) {
			throw new GenericException(ExceptionKeys.DAO_ERROR, e);
		}
	}	
	
	public Object insert(String statementName, Object parameterObject) throws GenericException{
		SqlMapClientTemplate template = getSqlMapClientTemplate();
    	try {
    		return template.insert(statementName, parameterObject);
		} catch (Exception e) {
			throw new GenericException(ExceptionKeys.DAO_ERROR, e);
		}
	}	
	
	public int UPDATE(String statementName, Object parameterObject) throws GenericException{
		SqlMapClientTemplate template = getSqlMapClientTemplate();
    	try {
    		return template.update(statementName, parameterObject);
		} catch (Exception e) {
			throw new GenericException(ExceptionKeys.DAO_ERROR, e);
		}
	}		
}
