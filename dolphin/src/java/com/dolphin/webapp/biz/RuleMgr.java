package com.dolphin.webapp.biz;

import java.util.List;

import com.dolphin.common.exception.GenericException;
import com.dolphin.webapp.vo.RptSearchVO;
import com.dolphin.webapp.vo.Rule;
import com.dolphin.webapp.vo.RuleAccessDailyCharge;
import com.dolphin.webapp.vo.RuleSearchVO;

public interface RuleMgr {
	
	
	public void reloadRules(String versioncode) throws GenericException;
	
	List<Rule> listAllRule() throws GenericException;
	
	List<Rule> listAllRule4List() throws GenericException;

	public Rule getRuleByRuleID(long ruleID) throws GenericException;
	public long createRule(Rule rule) throws GenericException;
	public void createMultipleRules(List<Rule> rulelist) throws GenericException;
	public void updateRule(Rule rule) throws GenericException;

	public Rule getRuleByRuleCode(String rulecode) throws GenericException;
	public Rule getRuleByRuleCode(String rulecode, String version) throws GenericException;
	
	public boolean checkRuleExisted(String ruleCode, RuleSearchVO searchVO) throws GenericException;
	
	public List<Rule> getRuleBySearchVO(RuleSearchVO searchVO) throws GenericException;
	
	public List<Rule> getRuleListVersion(String version) throws GenericException;
	
	
	
	/**
	 * @param city
	 * @param productcode
	 * @param version
	 * @param gapDays
	 * @return
	 * @throws GenericException
	 * @Deprecated
	 */
	public Rule searchRule(String city, String productcode, String version , long gapDays)	throws GenericException;
	
	/**
	 * @param city
	 * @param productcode
	 * @param version
	 * @param gapDays
	 * @return
	 * @throws GenericException
	 * @Deprecated
	 */
	public Rule searchRule(String province, String city, String productcode, String version, String serviceprovider, long gapDays,boolean isOnlySearchAds)	throws GenericException;	
	
	public Rule getSystemRuleByVersion(String version) throws GenericException;
	
	public List<Rule> listChargeRules() throws GenericException;
	
	public List<RuleAccessDailyCharge>  listRuleAccessDailyCharge(RptSearchVO vo) throws GenericException;
	
	
}


