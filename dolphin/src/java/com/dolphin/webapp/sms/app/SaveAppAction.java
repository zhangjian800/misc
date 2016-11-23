package com.dolphin.webapp.sms.app;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.dolphin.webapp.biz.AppMgr;
import com.dolphin.webapp.common.web.struts.GenericAction;
import com.dolphin.webapp.vo.App;

public class SaveAppAction extends GenericAction {

	private AppMgr appMgr = null;
	
	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AppForm appForm = (AppForm)form;
		App app = new App();
		BeanUtils.copyProperties(app, appForm);
		System.out.println(">>>>>>>>>>>Encoding"+System.getProperty("file.encoding"));
		if(app.getAppID() >0 ) {
			appMgr.updateApp(app);
		} else {
			appMgr.createApp(app);
		}
		request.setAttribute("App", app);
		
		return mapping.findForward("list");	
	}
	
	public void setAppMgr(AppMgr appMgr) {
		this.appMgr = appMgr;
	}	
}