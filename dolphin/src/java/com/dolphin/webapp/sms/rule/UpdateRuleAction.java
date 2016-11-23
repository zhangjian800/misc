package com.dolphin.webapp.sms.rule;

import java.util.Arrays;
import java.util.HashMap;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.dolphin.webapp.biz.AppMgr;
import com.dolphin.webapp.biz.PublicMgr;
import com.dolphin.webapp.biz.RuleMgr;
import com.dolphin.webapp.biz.ScriptMgr;
import com.dolphin.webapp.common.web.struts.GenericAction;
import com.dolphin.webapp.sms.SMSGenericAction;
import com.dolphin.webapp.vo.App;
import com.dolphin.webapp.vo.PhoneProduct;
import com.dolphin.webapp.vo.Rule;
import com.dolphin.webapp.vo.Script;

public class UpdateRuleAction extends SMSGenericAction {

	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int ruleID = Integer.parseInt(request.getParameter("ruleID"));
		Rule rule = ruleMgr.getRuleByRuleID(ruleID);
		
		String type = request.getParameter("type");
		
		/*
		List<App> appList = appMgr.listAllApp();
		List<HashMap> versions = pubMgr.getAllVersions();
//		if("rule4city".equalsIgnoreCase(type)){
			List<HashMap> provices = pubMgr.getAllProvinces();
			List<PhoneProduct> products = pubMgr.getAllProducts();
			request.setAttribute("ProvinceList", provices);
			request.setAttribute("ProdudctList", products);
//		}
//		if("rule4product".equalsIgnoreCase(type)){
//			List<PhoneProduct> products = pubMgr.getAllProducts();
//			request.setAttribute("ProdudctList", products);
//		}*/
		
		List<App> appList = appMgr.listAllApp();
		List<HashMap> versions = pubMgr.getAllVersions();
		List<HashMap> provices = pubMgr.getAllProvinces();
		List<PhoneProduct> products = pubMgr.getAllProducts();
		
		request.setAttribute("VersionList", versions);
		request.setAttribute("ProvinceList", provices);
		request.setAttribute("ProdudctList", products);
		request.setAttribute("AppList", appList);
		
		request.setAttribute("ruletype", type);
		request.setAttribute("Rule", rule);
		
		
		String [] cities = StringUtils.split(rule.getCity(), "_");
		if(cities!=null){
			request.setAttribute("CitySet", cities);
		}
		
		return mapping.findForward(type);
	}
}