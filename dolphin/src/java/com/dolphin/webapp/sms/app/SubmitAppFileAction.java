package com.dolphin.webapp.sms.app;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.dolphin.webapp.biz.AppMgr;
import com.dolphin.webapp.common.web.struts.GenericAction;


public class SubmitAppFileAction  extends GenericAction {

	private AppMgr appMgr = null;
	
	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		int appID = Integer.parseInt(request.getParameter("appID"));
		
		UploadForm uploadForm = (UploadForm)form;
	    FormFile file = uploadForm.getFile();
		String localFileName =  file.getFileName();
		byte [] inputSteam =  file.getFileData();
		this.appMgr.uploadAppStream(appID, localFileName, inputSteam);
		
		return mapping.findForward("list");
		
	}
	
	public void setAppMgr(AppMgr appMgr) {
		this.appMgr = appMgr;
	}	
}