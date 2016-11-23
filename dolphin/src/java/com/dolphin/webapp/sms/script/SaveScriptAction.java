package com.dolphin.webapp.sms.script;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.dolphin.webapp.biz.ScriptMgr;
import com.dolphin.webapp.common.web.struts.GenericAction;
import com.dolphin.webapp.vo.Script;

public class SaveScriptAction extends GenericAction {

	private ScriptMgr scriptMgr;
	public void setScriptMgr(ScriptMgr scriptMgr) {
		this.scriptMgr = scriptMgr;
	}

	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ScriptForm scriptForm = (ScriptForm)form;
		Script script = new Script();
		BeanUtils.copyProperties(script, scriptForm);
		
		if(script.getScriptID() >0 ) {
			scriptMgr.updateScript(script);
		} else {
			scriptMgr.createScript(script);
		}
		
		request.setAttribute("Script", script);
		return mapping.findForward("list");
	}
}