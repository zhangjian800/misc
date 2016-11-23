package com.dolphin.webapp.sms.app;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.dolphin.webapp.biz.AppMgr;
import com.dolphin.webapp.biz.PublicMgr;
import com.dolphin.webapp.common.web.struts.GenericAction;
import com.dolphin.webapp.vo.App;

public class UpdateAppAction extends GenericAction {

	private AppMgr appMgr = null;
	private PublicMgr pubMgr = null;

	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int appID = Integer.parseInt(request.getParameter("appID"));
		App app = appMgr.getAppByAppID(appID);
		request.setAttribute("App", app);
		request.setAttribute("VersionList", pubMgr.getAllVersions());
		return mapping.findForward("edit");
	}
	
	public void setAppMgr(AppMgr appMgr) {
		this.appMgr = appMgr;
	}	
	public void setPubMgr(PublicMgr pubMgr) {
		this.pubMgr = pubMgr;
	}
}