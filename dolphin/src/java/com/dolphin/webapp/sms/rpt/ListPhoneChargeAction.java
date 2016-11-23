package com.dolphin.webapp.sms.rpt;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.dolphin.common.log.DolpiLogger;
import com.dolphin.webapp.biz.AccessMgr;
import com.dolphin.webapp.biz.AppMgr;
import com.dolphin.webapp.biz.PublicMgr;
import com.dolphin.webapp.biz.RuleMgr;
import com.dolphin.webapp.common.web.struts.GenericAction;
import com.dolphin.webapp.vo.Access;
import com.dolphin.webapp.vo.AccessSearchVO;
import com.dolphin.webapp.vo.ConfirmChargeRptSearchVO;
import com.dolphin.webapp.vo.PhoneCharge;
import com.dolphin.webapp.vo.PhoneProduct;

public class ListPhoneChargeAction extends GenericAction {

	DolpiLogger logger = new DolpiLogger(ListPhoneChargeAction.class.getName());

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
		
		ConfirmChargeRptSearchVO vo = new ConfirmChargeRptSearchVO();
		BeanUtils.copyProperties(vo, searchForm);
		
		if(vo.getCurrdate()!=null){
			String currdate = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
			vo.setCurrdate(currdate);
		}
		set2Null(vo);
		if(vo.getMaxlimit()==0){
			vo.setMaxlimit(10);
		}

		if(vo.getMaxlimit()>0) {
			List<PhoneCharge> listResult = this.pubMgr.listPhoneCharge(vo);
			request.setAttribute("PhoneChargeList", listResult);
		} else{
			request.setAttribute("PhoneChargeList", new ArrayList<Access>());

		}
		List<HashMap> versions = pubMgr.getAllVersions();
		List<HashMap> provices = pubMgr.getAllProvinces();
		
		request.setAttribute("ProvinceList", provices);
		request.setAttribute("VersionList", versions);		
		request.setAttribute("ConfirmChargeRptSearchVO", vo);
		
		return mapping.findForward("result");	
	}
	
	private void set2Null(ConfirmChargeRptSearchVO vo){
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
	}
}
