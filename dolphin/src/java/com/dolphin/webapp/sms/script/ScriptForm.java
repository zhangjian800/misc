package com.dolphin.webapp.sms.script;

import com.dolphin.webapp.common.web.struts.GenericForm;

public class ScriptForm extends GenericForm {

	private int scriptID;
	private String scriptCode;
	private String scriptDesc;
	private String scriptcontent;
	public int getScriptID() {
		return scriptID;
	}
	public void setScriptID(int scriptID) {
		this.scriptID = scriptID;
	}
	public String getScriptCode() {
		return scriptCode;
	}
	public void setScriptCode(String scriptCode) {
		this.scriptCode = scriptCode;
	}
	public String getScriptDesc() {
		return scriptDesc;
	}
	public void setScriptDesc(String scriptDesc) {
		this.scriptDesc = scriptDesc;
	}
	public String getScriptcontent() {
		return scriptcontent;
	}
	public void setScriptcontent(String scriptcontent) {
		this.scriptcontent = scriptcontent;
	}
	
}
