package com.dolphin.webapp.biz;

import java.util.List;

import com.dolphin.common.exception.GenericException;
import com.dolphin.webapp.dao.ScriptDao;
import com.dolphin.webapp.vo.App;
import com.dolphin.webapp.vo.Script;

public class ScriptMgrImpl implements ScriptMgr{
	
	private ScriptDao scriptDao;
	public void setScriptDao(ScriptDao scriptDao) {
		this.scriptDao = scriptDao;
	}

	@Override
	public List<Script> listAllScript() throws GenericException{
		Object objList =  scriptDao.listAll();
		if(objList!=null){
			return (List<Script>)objList;
		}
		return null;
	}

	@Override
	public int createScript(Script script) throws GenericException{
		return scriptDao.create(script);
	}

	@Override
	public void updateScript(Script script) throws GenericException{
		scriptDao.update(script);
		return;
	}


	@Override
	public Script geScriptByScriptID(long scriptID) throws GenericException {
		return (Script)scriptDao.load(scriptID);

	}

	@Override
	public Script geScriptByScriptcode(String scriptCode)
			throws GenericException {
		Object obj = scriptDao.geScriptByScriptcode(scriptCode);
		if(obj!=null){
			return (Script)obj;
		}
		return null;
	}

	@Override
	public boolean checkScriptExisted(String scriptCode)
			throws GenericException {
		Script script = geScriptByScriptcode(scriptCode);
		return script!=null && script.getScriptID() >0;
	}
	
}
