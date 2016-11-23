package com.dolphin.webapp.sms.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.dolphin.webapp.common.web.struts.GenericAction;

public class LogoutAction extends GenericAction {

	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session=request.getSession();
		session.removeAttribute("SessionState");
		return mapping.findForward("index");
	}
}