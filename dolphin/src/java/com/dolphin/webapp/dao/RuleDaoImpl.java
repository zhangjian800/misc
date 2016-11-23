package com.dolphin.webapp.dao;

import java.util.List;

import com.dolphin.common.exception.GenericException;
import com.dolphin.webapp.common.dao.DefaultGenericDaoImpl;
import com.dolphin.webapp.vo.Access;
import com.dolphin.webapp.vo.RptSearchVO;
import com.dolphin.webapp.vo.Rule;
import com.dolphin.webapp.vo.RuleAccessDailyCharge;
import com.dolphin.webapp.vo.RuleSearchVO;

public class RuleDaoImpl extends DefaultGenericDaoImpl<Rule> implements RuleDao<Rule> {
	
	private final static String _SQL_STATEMENT_LISTBYACCESS = "listRulesByAccess";
	
	private final static String _SQL_STATEMENT_LISTBYSEARCHCRITIRIA = "listRulesBySearchCritiria";
	private final static String _SQL_STATEMENT_LISTBYRULECODE = "listRulesByRuleCode";

	private final static String _SQL_STATEMENT_GETRULE4CITY = "getRuleByCityAndVersion";
	private final static String _SQL_STATEMENT_GETRULE4PRODUCTCODE = "getRuleByProductAndVersion";
	private final static String _SQL_STATEMENT_GETRULE4VERSION = "getRuleByVersion";
	private final static String _SQL_STATEMENT_GETSYSTEMRULES = "getSystemRuleByVersion";

	private final static String _SQL_STATEMENT_LIST_RULE_DAILY_CHARGE = "listRuleDailyCharge";

	
	public List<Rule> listRulesByAccess(Access access) throws GenericException{
		Object obj = queryForList(_SQL_STATEMENT_LISTBYACCESS, access);
		
		StringBuffer  searchSql = new StringBuffer();
		searchSql.append("select * from t_rule");
		searchSql.append(" where producttype = '" +access.getProductType()+ "'");
		searchSql.append("  and version = '" +access.getVersion()+ "'");
		searchSql.append("  and pid = '" +access.getPid()+ "'");
		searchSql.append("  and lbs = '" +access.getLbs()+ "'");
		searchSql.append("  and platformType = '" +access.getPlatformType()+ "'");
		searchSql.append("  order by operatorDate desc");
		logger.error("Find rule sql is:"+searchSql.toString());
		if(obj !=null){
			return (List<Rule>)obj;
		}
		return null;
	}
	
	@Override
	public Rule getRuleByRuleCode(String ruleCode) throws GenericException {
		return (Rule)this.queryForObject(_SQL_STATEMENT_LISTBYRULECODE, ruleCode);
	}
	
	@Override
	public List<Rule> listRuleBySearchVO(RuleSearchVO searchVO) throws GenericException{
		Object obj = queryForList(_SQL_STATEMENT_LISTBYSEARCHCRITIRIA, searchVO);
		
		StringBuffer  searchSql = new StringBuffer();
		searchSql.append("select * from t_rule");
		searchSql.append(" where productcode = '" +searchVO.getProductcode()+ "'");
		searchSql.append("  and province = '" +searchVO.getProvince()+ "'");
		searchSql.append("  and city = '" +searchVO.getCity()+ "'");
		searchSql.append("  and version = '" +searchVO.getVersion()+ "'");
		searchSql.append("  and pid = '" +searchVO.getPid()+ "'");
		searchSql.append("  order by createtime desc");
		logger.error("Find rule sql is:"+searchSql.toString());
		if(obj !=null){
			List<Rule> ruleList =  (List<Rule>)obj;
			return ruleList;
		}
		
		return null;
	}	
	
	@Override
	public Rule getRule4City(String city,String productcode, String version) throws GenericException{
		RuleSearchVO vo = new RuleSearchVO();
		vo.setCity(city);
		vo.setProductcode(productcode);
		vo.setVersion(version);
		Object obj = queryForObject(_SQL_STATEMENT_GETRULE4CITY, vo);
		if(obj != null){
			return (Rule)obj;
		}
		return null;
	}
	
	@Override
	public Rule getRule4ProductCode(String productcode, String version) throws GenericException{
		RuleSearchVO vo = new RuleSearchVO();
		vo.setProductcode(productcode);
		vo.setVersion(version);
		Object obj = queryForObject(_SQL_STATEMENT_GETRULE4PRODUCTCODE, vo);
		if(obj != null){
			return (Rule)obj;
		}
		return null;
	}
	
	@Override
	public Rule getRule4Version(String version) throws GenericException{
		RuleSearchVO vo = new RuleSearchVO();
		vo.setVersion(version);
		Object obj = queryForObject(_SQL_STATEMENT_GETRULE4VERSION, vo);
		if(obj != null){
			return (Rule)obj;
		}
		return null;
	}	
	
	@Override
	public List<Rule> getRuleListByVersion(String version) throws GenericException{
		RuleSearchVO vo = new RuleSearchVO();
		vo.setVersion(version);
		Object obj = this.queryForList(_SQL_STATEMENT_GETRULE4VERSION, vo);
		if(obj != null){
			return (List<Rule>)obj;
		}
		return null;
	}		
	
	@Override
	public Rule getSystemRuleByVersion(String version) throws GenericException{
		Object obj = this.queryForObject(_SQL_STATEMENT_GETSYSTEMRULES, version);
		if(obj != null){
			return (Rule)obj;
		}
		return null;
	}

	@Override
	public List<RuleAccessDailyCharge> listRuleAccessDailyCharge(RptSearchVO vo)
			throws GenericException {
		Object obj = this.queryForList(_SQL_STATEMENT_LIST_RULE_DAILY_CHARGE, vo);
		if(obj != null){
			return (List<RuleAccessDailyCharge>)obj;
		}
		return null;
	}	
	
}