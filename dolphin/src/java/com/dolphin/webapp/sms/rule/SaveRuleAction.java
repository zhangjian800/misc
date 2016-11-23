package com.dolphin.webapp.sms.rule;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.dolphin.webapp.vo.Rule;
import com.dolphin.webapp.sms.SMSGenericAction;
import com.dolphin.webapp.sms.rule.RuleForm;

public class SaveRuleAction extends SMSGenericAction {

	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RuleForm ruleForm = (RuleForm)form;
		
	
		//Add protection
		if(StringUtils.isEmpty(ruleForm.getProvince())){
			ruleForm.setProvince("DEFAULT");
		}
		if(StringUtils.isEmpty(ruleForm.getServiceprovider())){
			ruleForm.setServiceprovider("DEFAULT");
		}		
		
		Rule rule = new Rule();
		BeanUtils.copyProperties(rule, ruleForm);
		String [] mulCityes = request.getParameterValues("city");
		String city = null;
		if(mulCityes!=null && mulCityes.length>1){
			for(int i=0; i<mulCityes.length;i++){
				if(city!=null && city.length()>0){
					city = city +"_"+mulCityes[i];
				} else {
					city = mulCityes[i];
				}
			}
			rule.setCity(city);
		} 
		
		if(StringUtils.isEmpty(ruleForm.getCity())){
			ruleForm.setCity("DEFAULT");
		}
		
		rule.setApptype(appMgr.getAppByAppID(ruleForm.getAppID()).getApptype());		
		if(rule.getRuleID() >0 ) {
			ruleMgr.updateRule(rule);
		} else {
//			if(mulCityes!=null && mulCityes.length>1){
//				Rule newRule = null;
//				List<Rule> ruleList = new ArrayList<Rule>();
//				for(int i=0; i<mulCityes.length;i++){
//					newRule = new Rule();
//					BeanUtils.copyProperties(newRule, rule);
//					newRule.setCity(mulCityes[i]);
//					newRule.setRulecode(rule.getRulecode()+"_"+mulCityes[i]);
//					ruleList.add(newRule);
//				}
//				ruleMgr.createMultipleRules(ruleList);
//			} else{
				ruleMgr.createRule(rule);				
//			}
		}
		
		List<Rule> listResult = ruleMgr.listAllRule4List();
		request.setAttribute("RuleList", listResult);		
		return mapping.findForward("list");	
	}
	
}