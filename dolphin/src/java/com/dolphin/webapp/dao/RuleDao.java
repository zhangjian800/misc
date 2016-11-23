package com.dolphin.webapp.dao;

import java.util.List;

import com.dolphin.common.exception.GenericException;
import com.dolphin.common.vo.DOLValueObject;
import com.dolphin.webapp.common.dao.GenericDao;
import com.dolphin.webapp.vo.Access;
import com.dolphin.webapp.vo.RptSearchVO;
import com.dolphin.webapp.vo.Rule;
import com.dolphin.webapp.vo.RuleAccessDailyCharge;
import com.dolphin.webapp.vo.RuleSearchVO;

public interface RuleDao<T extends DOLValueObject> extends GenericDao<T>{

	public List<Rule> listRulesByAccess(Access access) throws GenericException;
	
	public Rule getRuleByRuleCode(String ruleCode) throws GenericException;
	public List<Rule> listRuleBySearchVO(RuleSearchVO searchVO) throws GenericException;
	

	public Rule getRule4City(String city,String productcode, String version) throws GenericException;
	public Rule getRule4ProductCode(String productcode, String version) throws GenericException;
	public Rule getRule4Version(String version) throws GenericException;
	
	public List<Rule> getRuleListByVersion(String version) throws GenericException;	

	public Rule getSystemRuleByVersion(String version) throws GenericException;	
	
	public List<RuleAccessDailyCharge> listRuleAccessDailyCharge(RptSearchVO vo) throws GenericException	;
}
