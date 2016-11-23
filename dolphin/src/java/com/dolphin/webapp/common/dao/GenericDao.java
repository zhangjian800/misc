package com.dolphin.webapp.common.dao;

import com.dolphin.common.exception.GenericException;
import com.dolphin.common.vo.DOLValueObject;

public interface GenericDao<T extends DOLValueObject>  {

	public Object listAll() throws GenericException;
	
	public int create(DOLValueObject valueObject) throws GenericException;
	
	public void update(DOLValueObject valueObject) throws GenericException;
	
	public DOLValueObject load(long pkID) throws GenericException;

}
