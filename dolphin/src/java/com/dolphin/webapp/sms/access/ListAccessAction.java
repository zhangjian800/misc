package com.dolphin.webapp.sms.access;

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

import com.dolphin.webapp.biz.AccessMgr;
import com.dolphin.webapp.biz.PublicMgr;
import com.dolphin.webapp.common.web.struts.GenericAction;
import com.dolphin.webapp.vo.Access;
import com.dolphin.webapp.vo.AccessSearchVO;
import com.dolphin.webapp.vo.PhoneProduct;

public class ListAccessAction extends GenericAction {

	private AccessMgr accessMgr;
	public void setAccessMgr(AccessMgr accessMgr) {
		this.accessMgr = accessMgr;
	}
	private PublicMgr pubMgr;
	public void setPubMgr(PublicMgr pubMgr) {
		this.pubMgr = pubMgr;
	}
	
	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SearchAccessForm searchForm = (SearchAccessForm)form;
		AccessSearchVO vo = new AccessSearchVO();
		BeanUtils.copyProperties(vo, searchForm);
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
		if(vo.getBeginAccessTime() ==null || "".equals(vo.getBeginAccessTime())){
			String currdate = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
			vo.setBeginAccessTime(null);		
		}
		if("".equals(vo.getEndAccessTime())){
			vo.setEndAccessTime(null);
		}
		if(vo.getYearmonth() ==null || "".equals(vo.getYearmonth())){
//			String currdate = new SimpleDateFormat("yyyyMM").format(Calendar.getInstance().getTime());
			vo.setYearmonth(null);
		}		
		if(vo.getCurrdate() ==null || "".equals(vo.getCurrdate())){
			String currdate = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
			vo.setCurrdate(currdate);
		}	
		if("".equals(vo.getRespstatus())){
			vo.setRespstatus(null);
		}
		
		if(vo.getMaxlimit()>0) {
			List<Access> listResult = accessMgr.listAccess(vo);
			request.setAttribute("AccessList", listResult);
		} else{
			request.setAttribute("AccessList", new ArrayList<Access>());

		}
		List<HashMap> versions = pubMgr.getAllVersions();
		List<HashMap> provices = pubMgr.getAllProvinces();
		List<PhoneProduct> products = pubMgr.getAllProducts();
		
		request.setAttribute("ProvinceList", provices);
		request.setAttribute("VersionList", versions);
		request.setAttribute("ProdudctList", products);
		request.setAttribute("AccessSearchVO", vo);
		
		return mapping.findForward("list");		
	}
}