package com.dolphin.webapp.common.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import com.dolphin.common.exception.GenericException;
import com.dolphin.common.vo.DOLValueObject;

public class DefaultGenericDaoImpl<T extends DOLValueObject> extends AbstractIbatisDao implements GenericDao<T> {
		
	private static final String _SQL_STATEMENT_LISTALL_PREFIX = "listAll";
	private static final String _SQL_STATEMENT_CREATE_PREFIX = "insert";
	private static final String _SQL_STATEMENT_UPDATE_PREFIX = "update";
	private static final String _SQL_STATEMENT_FIND_PREFIX = "find";
	private static final String _SQL_STATEMENT_FIND_SUFFIX = "ByPK";

    private Class<T> entityClass;
	private String objctName = "";

	public DefaultGenericDaoImpl(){
        this.entityClass = null;
        Class thisClass = getClass();
        Type t = thisClass.getGenericSuperclass();
        if (t instanceof ParameterizedType) {
            Type[] p = ((ParameterizedType) t).getActualTypeArguments();
            this.entityClass = (Class<T>) p[0];
        }
        objctName = entityClass.getSimpleName();
	}
	
	public Object listAll() throws GenericException{
		return queryForList(_SQL_STATEMENT_LISTALL_PREFIX+objctName);
	}
	
	public int create(DOLValueObject valueObject) throws GenericException{
		 Object obj =  this.insert(_SQL_STATEMENT_CREATE_PREFIX+objctName, valueObject);
		 return (Integer)obj;
	}
	
	public void update(DOLValueObject valueObject) throws GenericException {
		this.insert(_SQL_STATEMENT_UPDATE_PREFIX+objctName, valueObject);
	}
	
	public DOLValueObject load(long pkID) throws GenericException {
		return (DOLValueObject)this.queryForObject(_SQL_STATEMENT_FIND_PREFIX+objctName+_SQL_STATEMENT_FIND_SUFFIX, pkID);
	}	
}
