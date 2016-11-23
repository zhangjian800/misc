package com.dolphin.webapp.sms.rule;


import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.dolphin.webapp.sms.SMSGenericAction;
import com.dolphin.webapp.vo.Rule;


public class DisableRuleAction extends SMSGenericAction {

	
	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int ruleID = Integer.parseInt(request.getParameter("ruleID"));
		Rule rule = ruleMgr.getRuleByRuleID(ruleID);
		rule.setStatus("Delete");
		ruleMgr.updateRule(rule);
		
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter pw = response.getWriter();
		pw.write("OKOKOK");
		pw.flush();
		
		return null;
	}
}