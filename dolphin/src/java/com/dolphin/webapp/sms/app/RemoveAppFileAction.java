package com.dolphin.webapp.sms.app;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.dolphin.webapp.biz.AppMgr;
import com.dolphin.webapp.common.web.struts.GenericAction;
import com.dolphin.webapp.vo.App;

public class RemoveAppFileAction extends GenericAction {

	private AppMgr appMgr = null;
	
	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int appID = Integer.parseInt(request.getParameter("appID"));
		
		App app = appMgr.getAppByAppID(appID);
		app.setStatus("Delete");
		appMgr.updateApp(app);


		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter pw = response.getWriter();
		pw.write("OKOKOK");
		pw.flush();
		
		return null;
	}
	
	public void setAppMgr(AppMgr appMgr) {
		this.appMgr = appMgr;
	}	
}