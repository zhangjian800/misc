package com.dolphin.webapp.sms.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.dolphin.webapp.biz.PublicMgr;
import com.dolphin.webapp.common.web.struts.GenericAction;
import com.dolphin.webapp.vo.SystemConfig;

public class SaveSystemRulelAction extends GenericAction {

	private PublicMgr pubMgr;
	public void setPubMgr(PublicMgr pubMgr) {
		this.pubMgr = pubMgr;
	}

	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SystemRuleForm sysRuleForm = (SystemRuleForm)form;
		SystemConfig sc = new SystemConfig();
		BeanUtils.copyProperties(sc, sysRuleForm);
		pubMgr.updatetSystemConfig(sc);
		request.setAttribute("SystemConfig", sc);

		return mapping.findForward("edit");
	}
}