package com.dolphin.webapp.sms.rpt;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendarimport java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.dolphin.common.log.DolpiLogger;
import com.dolphin.webapp.biz.AccessMgr;
import com.dolphin.webapp.biz.AppMgr;
import com.dolphin.webapp.biz.PublicMgr;
import com.dolphin.webapp.biz.RuleMgr;
import com.dolphin.webapp.common.web.struts.GenericAction;
import com.dolphin.webapp.sms.access.GenerateAccessAction;
import com.dolphin.webapp.sms.access.SearchAccessForm;
import com.dolphin.webapp.vo.Access;
import com.dolphin.webapp.vo.AccessSearchVO;
import com.dolphin.webapp.vo.PhoneProduct;
import com.dolphin.webapp.vo.RptSearchVO;
import com.dolphin.webapp.vo.Rule;
import com.dolphin.webapp.vo.RuleAccessDailyCharge;

public class ListDailyChargeDtlAction extends GenericAction {

	DolpiLogger logger = new DolpiLogger(ListDailyChargeDtlAction.class.getName());

	private AccessMgr accessMgr;
	private PublicMgr pubMgr;
	private RuleMgr ruleMgr;
	private AppMgr appMgr;
	public void setAccessMgr(AccessMgr accessMgr) {
		this.accessMgr = accessMgr;
	}
	public void setPubMgr(PublicMgr pubMgr) {
		this.pubMgr = pubMgr;
	}
	
	public void setRuleMgr(RuleMgr ruleMgr) {
		this.ruleMgr = ruleMgr;
	}
	public void setAppMgr(AppMgr appMgr) {
		this.appMgr = appMgr;
	}
	@Override
	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ReportForm searchForm = (ReportForm)form;
		
		AccessSearchVO vo = new AccessSearchVO();
		
		if(StringUtils.isNotBlank(searchForm.getCurrdate())){
			vo.setCurrdate(searchForm.getCurrdate());
		}
		if(searchForm.getRuleID()>0){
			vo.setRuleID(searchForm.g else {
			String currdate = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
			vo.setCurrdate(currdatehForm.getRuleID());
		}
		if(searchForm.getMaxlimit()>0){
			vo.setMaxlimit(searchForm.getMaxlimit());
		} else {
			vo.setMaxlimit(10);
		}
		
		
		if(vo.getMaxlimit()>0 && vo.getRuleID() >0) {
			List<Access> listResult = accessMgr.listAccetAttribute("AccessList", listResult);
		} else{
			request.setAttribute("AccessList", new ArrayList<Access>());

		}
		List<Rule> chargeRuleList = ruleMgr.listChargeRules();
		request.setAttribute("chargeRuleList", chargeRuleList);
		
		request.setAttribute("AccessSearchVO", vo);

		return mapping.findForward("result");	
	}

	
	
}
