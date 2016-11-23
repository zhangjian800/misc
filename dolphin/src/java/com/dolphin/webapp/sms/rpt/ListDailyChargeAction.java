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

public class ListDailyChargeAction extends GenericAction {

	DolpiLogger logger = new DolpiLogger(ListDailyChargeAction.class.getName());

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
		RptSearchVO vo = new RptSearchVO();
		
		if(StringUtils.isNotBlank(searchForm.getCurrdate())){
			vo.setCurrdate(searchForm.getCurrdate());
		}
		if(searchForm.getRuleID()>0){
			vo.setRuleID(searchForm. else {
				String currdate = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
				vo.setCurrdate(currdatechForm.getRuleID());
		}
		/*
		if(vo.getBeginAccessTime()!=null && vo.getBeginAccessTime().length()>0){
			vo.setBeginAccessTime(vo.getBeginAccessTime().trim()+" 00:00:00");
		}
		if(vo.getEndAccessTime()!=null && vo.getEndAccessTime().length()>0){
			vo.setEndAccessTime(vo.getEndAccessTime().trim()+" 59:59:59");
		}
		*/
		if("".equals(vo.getImsi())){
			vo.setImsi(null);
		}
		if("".equals(vo.getProvince())){
			vo.setProvince(null);
		}	
		if("".equals(vo.getCity())){
			vo.setCity(null);
		}	
		if("".equals(vo.getVersion())){
			vo.setVersion(null);
		}		
		if("".equals(vo.getProductcode())){
			vo.setProductcode(null);
		}			
		if("".equals(vo.getBeginAccessTime())){
			vo.setBeginAccessTime(null);
		}
		if("".equals(vo.getEndAccessTime())){
			vo.setEndAccessTime(null);
		}
		if("".equals(vo.getYearmonth())){
			vo.setYearmonth(null);
		}		
		if("".equals(vo.getRespstatus())){
			vo.setRespstatus(null);
		}
		
		List<Rule> chargeRuleList = ruleMgr.listChargeRules();
		
		request.setAttribute("chargeRuleList", chargeRuleList);
		request.setAttribute("RptSearchVO", vo);
		
		List<RuleAccessDailyCharge> result = ruleMgr.listRuleAccessDailyCharge(vo);
		request.setAttribute("ResultList", result);

		return mapping.findForward("result");	
	}

	
	
}
