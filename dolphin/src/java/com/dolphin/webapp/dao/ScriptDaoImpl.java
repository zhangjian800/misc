package com.dolphin.webapp.dao;

import com.dolphin.common.exception.GenericException;
import com.dolphin.webapp.common.dao.DefaultGenericDaoImpl;
import com.dolphin.webapp.vo.Script;

public class ScriptDaoImpl  extends DefaultGenericDaoImpl<Script> implements ScriptDao<Script> {

	private static final String _SQL_STATEMENT_GET_SCRIPTBYCODE = "getScriptByCode";
	
	@Override
	public Script geScriptByScriptcode(String scriptCode)
			throws GenericException {
		return (Script)this.queryForObject(_SQL_STATEMENT_GET_SCRIPTBYCODE, scriptCode);
	}

}