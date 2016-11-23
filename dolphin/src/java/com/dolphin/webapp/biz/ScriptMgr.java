package com.dolphin.webapp.biz;

import java.util.List;

import com.dolphin.common.exception.GenericException;
import com.dolphin.webapp.vo.Script;

public interface ScriptMgr {
	
	List<Script> listAllScript() throws GenericException;
	public Script geScriptByScriptID(long scriptID) throws GenericException;
	public Script geScriptByScriptcode(String scriptCode) throws GenericException;
	public int createScript(Script script) throws GenericException;
	public void updateScript(Script script) throws GenericException;
	
	public boolean checkScriptExisted(String scriptCode) throws GenericException;
	
}
