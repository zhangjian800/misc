package com.dolphin.webapp.sms.access;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.dolphin.webapp.biz.AccessMgr;
import com.dolphin.webapp.biz.PublicMgr;
import com.dolphin.webapp.common.web.struts.GenericAction;
import com.dolphin.webapp.vo.Access;
import com.dolphin.webapp.vo.AccessResponse;

public class LoadAccessResponseAction extends GenericAction {

	private AccessMgr accessMgr;
	private PublicMgr pubMgr;
	
	public void setAccessMgr(AccessMgr accessMgr) {
		this.accessMgr = accessMgr;
	}
	public void setPubMgr(PublicMgr pubMgr) {
		this.pubMgr = pubMgr;
	}
	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		int accessID = Integer.parseInt(request.getParameter("accessID"));
		Access result = accessMgr.loadAccessAccessID(accessID);
		request.setAttribute("Access", result);
		return mapping.findForward("view");	
	}
}