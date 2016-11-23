package com.dolphin.webapp.sms.script;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.dolphin.webapp.biz.ScriptMgr;
import com.dolphin.webapp.common.web.struts.GenericAction;
import com.dolphin.webapp.vo.Script;

public class ListAllScriptsAction extends GenericAction {

	private ScriptMgr scriptMgr;
	public void setScriptMgr(ScriptMgr scriptMgr) {
		this.scriptMgr = scriptMgr;
	}
	
	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<Script> listResult = scriptMgr.listAllScript();
		request.setAttribute("ScriptList", listResult);
		return mapping.findForward("list");	
	}
}