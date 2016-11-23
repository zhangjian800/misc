package com.dolphin.webapp.sms.pub;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.dolphin.webapp.biz.AppMgr;
import com.dolphin.webapp.biz.PublicMgr;
import com.dolphin.webapp.biz.RuleMgr;
import com.dolphin.webapp.biz.ScriptMgr;
import com.dolphin.webapp.common.web.struts.GenericAction;
import com.dolphin.webapp.vo.App;
import com.dolphin.webapp.vo.Rule;
import com.dolphin.webapp.vo.RuleSearchVO;
import com.dolphin.webapp.vo.Script;

public class LoadCodeTableAction extends GenericAction {

	private PublicMgr pubMgr;
	private RuleMgr ruleMgr;
	private AppMgr appMgr = null;
	private ScriptMgr scriptMgr;
	
	public void setRuleMgr(RuleMgr ruleMgr) {
		this.ruleMgr = ruleMgr;
	}
	public void setScriptMgr(ScriptMgr scriptMgr) {
		this.scriptMgr = scriptMgr;
	}	
	public void setAppMgr(AppMgr appMgr) {
		this.appMgr = appMgr;
	}
	public void setPubMgr(PublicMgr pubMgr) {
		this.pubMgr = pubMgr;
	}
	
	
	private static final String CITY_PER_PROVINCE = "CityByProvince";
	private static final String CHECK_SCRIPT_CODE = "CheckScriptCode";
	private static final String CHECK_APP_CODE = "CheckAppCode";

	private static final String CHECK_RULE_VERSION = "CheckRuleByVersion";
	private static final String CHECK_RULE_CITY = "CheckRuleByCity";
	private static final String CHECK_RULE_PRODUCT = "CheckRuleByProduct";	
	
	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String type = request.getParameter("type");
		String arg1 = request.getParameter("arg1");
		String arg2 = request.getParameter("arg2");
		String arg3 = request.getParameter("arg3");
		String arg4 = request.getParameter("arg4");
		String arg5 = request.getParameter("arg5");
		String arg6 = request.getParameter("arg6");
		
		List<HashMap> result = new ArrayList<HashMap>();
		String jsonString = "";
		if (type.equalsIgnoreCase(CITY_PER_PROVINCE)) {
			result = pubMgr.getCitiesByProcince(arg1);
//			jsonString = "{}";
			if(result!=null){
				jsonString = JsonUtil.getJSONString(result);			
			}
		} else if(type.equalsIgnoreCase(CHECK_SCRIPT_CODE)){
			boolean isExist = scriptMgr.checkScriptExisted(arg1);
			if(isExist){
				jsonString = "1";
			}else{
				jsonString = "0";
			}
		} else if(type.equalsIgnoreCase(CHECK_APP_CODE)){
			boolean isExist = appMgr.checkAppExisted(arg1);
			if(isExist){
				jsonString = "1";
			}else{
				jsonString = "0";
			}	
		} else if(type.equalsIgnoreCase(CHECK_RULE_VERSION)){
//			Rule rule = ruleMgr.getRule4Version(arg1) ;
//			boolean isExist = rule !=null && rule.getRuleID()>0;
//			if(isExist){
//				jsonString = "1";
//			}else{
				jsonString = "0";
//			}			
		} else if(type.equalsIgnoreCase(CHECK_RULE_PRODUCT)){
//			Rule rule = ruleMgr.getRule4ProductCode(arg1, arg2);
//			boolean isExist = rule !=null && rule.getRuleID()>0;
//			if(isExist){
//				jsonString = "1";
//			}else{
				jsonString = "0";
//			}			
		} else if(type.equalsIgnoreCase(CHECK_RULE_CITY)){
//			Rule rule = ruleMgr.getRule4City(arg1, arg2, arg3);
//			boolean isExist = rule !=null && rule.getRuleID()>0;
//			if(isExist){
//				jsonString = "1";
//			}else{
				jsonString = "0";
//			}			
		}
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter pw = response.getWriter();
		pw.write(jsonString);
		pw.flush();
		return null;
	}
}
