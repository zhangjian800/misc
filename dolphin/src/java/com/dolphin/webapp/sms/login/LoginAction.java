package com.dolphin.webapp.sms.login;

import com.dolphin.webapp.biz.LoginResult;

import com.dolphin.webapp.biz.LoginMgr;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.dolphin.webapp.common.web.struts.GenericAction;
import com.dolphin.webapp.context.SessionState;

public class LoginAction extends GenericAction {

	LoginMgr loginMgr = null;
	
	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		LoginForm loginForm = (LoginForm)form;
		String email = loginForm.getEmail();
		String password = loginForm.getPassword();
		
		HttpSession session=request.getSession();
		if(StringUtils.isBlank(email) || StringUtils.isBlank(password)){
			session.removeAttribute("SessionState");
			request.setAttribute("failureCode", LoginResult._PASSWORD_INVALID);
		    return mapping.findForward("index");
		}
		
		LoginResult result = loginMgr.login(email, password);
		if(result.getLoginStatus() == LoginResult._SUCCESS) {
			SessionState sessionState = new SessionState();
			sessionState.setEmail(email);
			sessionState.setUid(result.getUid());
			session.setAttribute("SessionState", sessionState);
		    return mapping.findForward("target");
		} else {
			session.removeAttribute("SessionState");
			int failureCode = result.getLoginStatus();
			request.setAttribute("failureCode", failureCode);
		    return mapping.findForward("index");
		}
	}

	public void setLoginMgr(LoginMgr loginMgr) {
		this.loginMgr = loginMgr;
	}
}
