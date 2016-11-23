package com.dolphin.webapp.dao;

import com.dolphin.common.exception.GenericException;
import com.dolphin.common.vo.DOLValueObject;
import com.dolphin.webapp.common.dao.GenericDao;
import com.dolphin.webapp.vo.Script;

public interface ScriptDao<T extends DOLValueObject> extends GenericDao<T>{
	
	public Script geScriptByScriptcode(String scriptCode) throws GenericException;
}
