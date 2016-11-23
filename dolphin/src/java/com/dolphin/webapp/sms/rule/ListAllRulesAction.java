package com.dolphin.webapp.sms.rule;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.dolphin.common.exception.GenericException;
import com.dolphin.webapp.biz.AppMgr;
import com.dolphin.webapp.biz.RuleMgr;
import com.dolphin.webapp.biz.ScriptMgr;
import com.dolphin.webapp.common.web.struts.GenericAction;
import com.dolphin.webapp.sms.SMSGenericAction;
import com.dolphin.webapp.vo.App;
import com.dolphin.webapp.vo.ConfirmChargeRptSearchVO;
import com.dolphin.webapp.vo.PhoneProduct;
import com.dolphin.webapp.vo.Rule;
import com.dolphin.webapp.vo.RuleSearchVO;

public class ListAllRulesAction extends SMSGenericAction {
	
	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RuleForm searchForm = (RuleForm)form;
		RuleSearchVO vo = new RuleSearchVO();
		BeanUtils.copyProperties(vo, searchForm);
		set2Null(vo);
		List<Rule> listResult = ruleMgr.getRuleBySearchVO(vo);
		List<HashMap> versions = pubMgr.getAllVersions();
		List<HashMap> provices = pubMgr.getAllProvinces();		
		
		List<Rule> listResult2 = new ArrayList<Rule>();
		for(int i=0; i<listResult.size(); i++){
			Rule rl = listResult.get(i);
			if(rl.getAppID()>0){
				App app = appMgr.getAppByAppID(rl.getAppID());
				if(app!=null){
					rl.setAppcode(app.getAppcode());
					rl.setAppfilename(app.getAppfilename());
				} 
			}
			String province = rl.getProvince();
			if(rl.getProvince()!=null && !"DEFAULT".equals(rl.getProvince())){
				rl.setProvince(getProvinceDesc(provices, province));
			}
			if(rl.getCity()!=null && !"DEFAULT".equals(rl.getCity())){
				rl.setCity(getCityDesc(provices, province, rl.getCity()));
			}
			listResult2.add(rl);
		}
		
		request.setAttribute("ProvinceList", provices);
		request.setAttribute("VersionList", versions);
		
		request.setAttribute("RuleList", listResult2);
		request.setAttribute("RuleSearchVO", vo);
		
		return mapping.findForward("list");		
	}
	
	
	private String getProvinceDesc(List<HashMap> provinceList, String province) throws GenericException{
		for(int i=0; i < provinceList.size(); i++) {
			HashMap map = (HashMap)provinceList.get(i);
			if(province.equalsIgnoreCase((String)map.get("provicecode"))){
				return (String)map.get("provicedesc");
			}
		}
		return province;
	}
	
	private String getCityDesc(List<HashMap> provinces, String province, String city) throws GenericException{
		List<HashMap> cities = this.pubMgr.getCitiesByProcince(province);
		for(int i=0; i < cities.size(); i++) {
			HashMap map = (HashMap)cities.get(i);
			if(city.equalsIgnoreCase((String)map.get("citycode"))){
				return (String)map.get("citydesc");
			}
		}		
		return city;
	}
	private void set2Null(RuleSearchVO vo){
		if("".equals(vo.getProvince())){
			vo.setProvince(null);
		}	
		if("".equals(vo.getCity())){
			vo.setCity(null);
		}	
		if("".equals(vo.getVersion())){
			vo.setVersion(null);
		}		
		if("".equals(vo.getApptype())){
			vo.setApptype(null);
		}
		if("".equals(vo.getStatus())){
			vo.setStatus(null);
		}
		vo.setProductcode(null);
		vo.setPid(null);
		vo.setCity(null);
	}
}
