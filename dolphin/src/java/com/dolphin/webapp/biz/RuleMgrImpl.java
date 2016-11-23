package com.dolphin.webapp.biz;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.dolphin.common.exception.GenericException;
import com.dolphin.common.log.DolpiLogger;
import com.dolphin.webapp.cache.CacheMgr;
import com.dolphin.webapp.dao.AccessDao;
import com.dolphin.webapp.dao.RuleDao;
import com.dolphin.webapp.vo.Access;
import com.dolphin.webapp.vo.App;
import com.dolphin.webapp.vo.RptSearchVO;
import com.dolphin.webapp.vo.Rule;
import com.dolphin.webapp.vo.RuleAccessDailyCharge;
import com.dolphin.webapp.vo.RuleAccessMonthlyCharge;
import com.dolphin.webapp.vo.RuleSearchVO;
import com.dolphin.webapp.vo.SystemConfig;

public class RuleMgrImpl implements RuleMgr {
	
	private DolpiLogger logger = new DolpiLogger(RuleMgrImpl.class.getName());

	
	private RuleDao ruleDao;
	private AccessDao accessDao;
	
	public void setRuleDao(RuleDao ruleDao) {
		this.ruleDao = ruleDao;
	}
	public void setAccessDao(AccessDao accessDao) {
		this.accessDao = accessDao;
	}


	CacheMgr cacheMgr;
	public void setCacheMgr(CacheMgr cacheMgr) {
		this.cacheMgr = cacheMgr;
	}
	
	AppMgr appMgr;
	public void setAppMgr(AppMgr appMgr) {
		this.appMgr = appMgr;
	}
	
	PublicMgr pubMgr;
	public void setPubMgr(PublicMgr pubMgr) {
		this.pubMgr = pubMgr;
	}


	public final static String CACHE_KEY_RULESBYVERSION = "RULESBYVERSION";
	public final static String CACHE_KEY_ALLRULES = "ALLRULES";
	
	public final static String CACHE_KEY_SYSTEMRULE_BY_VERSION = "SYSTEM_RULE_BY_VERSION_";
	
	
	@Override
	public List<Rule> listAllRule() throws GenericException {
		Object obj = cacheMgr.getCacheEntry(CACHE_KEY_ALLRULES);
		if(obj != null){
			return (List<Rule>) obj;
		}
		List<Rule> result = (List<Rule>)ruleDao.listAll();
		if(result != null) {
			cacheMgr.addCacheEntry(CACHE_KEY_ALLRULES, result);
		}
		return result;
	}
	
	public void reloadAllRules() throws GenericException {
		cacheMgr.flushCacheEntry(CACHE_KEY_ALLRULES);
		listAllRule();
	}
	
	public void reloadRules(String versioncode) throws GenericException {
		if(versioncode!=null && versioncode.length() >0 ){
			flushRulesByVersion(versioncode, false);
			reloadRuleByVersion(versioncode, false);
		} else {
			reloadAllRules();
			//Get all Versions
			cacheMgr.flushCacheEntry(PublicMgrImpl.CACHE_KEY_ALL_VERSIONS);
			List<HashMap> versionList =  pubMgr.getAllVersions();
			for (int i = 0; i < versionList.size(); i++) {
				HashMap map = versionList.get(i);
				flushRulesByVersion((String) map.get("versioncode"), false);
				reloadRuleByVersion((String) map.get("versioncode"), false);
			}
		}
	}
	
	@Override
	public List<Rule> listAllRule4List() throws GenericException {
		List<Rule> result = listAllRule();
		List<Rule> result2 = new ArrayList<Rule>();
		for(int i=0;i<result.size();i++){
			Rule rl = result.get(i);
			App app = appMgr.getAppByAppID(rl.getAppID());
			if(app!=null){
				rl.setAppcode(app.getAppcode());
				rl.setAppfilename(app.getAppfilename());
				String ruleType = rl.getRuletype();
				if("rule4fstpriority".equals(ruleType)){
					rl.setRuletypedesc("SYSTEM_RULE");
				} else{
					if(app.getApptype().equalsIgnoreCase("1")){
						rl.setRuletypedesc("ADS_RULE");
					} else if(app.getApptype().equalsIgnoreCase("2")){
						rl.setRuletypedesc("CHARGE_RULE");
					} else {
						rl.setRuletypedesc("NORMAL_RULE");
					}
				}
			} else {
				if("rule4fstpriority".equals(rl.getRuletype())){
					rl.setRuletypedesc("SYSTEM_RULE");
				} else{
					rl.setRuletypedesc("NORMAL_RULE");
				}
			}
			result2.add(rl);
		}
		return result2;
	}	
	@Override
	public Rule getRuleByRuleID(long ruleID) throws GenericException {
		List<Rule> result = listAllRule();
		if(result==null) return null;
		for(int i=0;i<result.size();i++){
			Rule rl = result.get(i);
			if(rl.getRuleID() == ruleID){
				return rl;
			}
		}
		return null;
	}
	@Override
	public long createRule(Rule rule) throws GenericException {
		long ruleID = ruleDao.create(rule);
		
		flushRulesByVersion(rule.getVersion(), true);
		reloadRuleByVersion(rule.getVersion(), true);

		return  ruleID;

	}
	
	@Override
	public void createMultipleRules(List<Rule> rulelist) throws GenericException {
		String ruleVerion = null;
		for(int i=0;i<rulelist.size();i++){
			Rule rl = rulelist.get(i);
			ruleVerion = rl.getVersion();
			ruleDao.create(rl);
		}
		
		if(ruleVerion!=null){
			flushRulesByVersion4Multiple(ruleVerion, true);
			reloadRuleByVersion(ruleVerion, true);
		}

	}
	
	private void flushRulesByVersion4Multiple(String version, boolean flushAllRules) throws GenericException{
		if(flushAllRules) {
			cacheMgr.flushCacheEntry(CACHE_KEY_ALLRULES);
		}
		cacheMgr.flushCacheEntry(CACHE_KEY_RULESBYVERSION+version);
		cacheMgr.flushCacheEntry(CACHE_KEY_SYSTEMRULE_BY_VERSION+version);
		
	}
	
	private void flushRulesByVersion(String version, boolean flushAllRules) throws GenericException{
		if(flushAllRules) {
			cacheMgr.flushCacheEntry(CACHE_KEY_ALLRULES);
		}
		cacheMgr.flushCacheEntry(CACHE_KEY_RULESBYVERSION+version);
		cacheMgr.flushCacheEntry(CACHE_KEY_SYSTEMRULE_BY_VERSION+version);
		
		String groupID = version + _VERSION_GROUP_KEY_PREFIX;
		cacheMgr.flushGlobalCacheByGroup(groupID);
	}
	
	//Current system has three layes rules
	//1, all rules
	//2, all rules per version
	//3, system rules
	private void reloadRuleByVersion(String version, boolean flushAllRules) throws GenericException{
		if(flushAllRules){
			listAllRule();
		}
		getRuleListVersion(version);
		getSystemRuleByVersion(version);
	}
	
	
	@Override
	public void updateRule(Rule rule) throws GenericException {
		ruleDao.update(rule);
		
		flushRulesByVersion(rule.getVersion(), true);
		reloadRuleByVersion(rule.getVersion(), true);
		
		//update rule
		updateRuleChargeLimit(rule);
		
		this.getSystemRuleByVersion(rule.getVersion());
	}
	
	public void updateRuleChargeLimit(Rule rule) throws GenericException {

		String currdate = new SimpleDateFormat("yyyyMMdd").format(java.util.Calendar.getInstance().getTime());
		String curretnYearMonth = new SimpleDateFormat("yyyyMM").format(java.util.Calendar.getInstance().getTime());

		RuleAccessDailyCharge daily = accessDao.findRuleDailylyChargeByRuleIDAndYearMonth(rule.getRuleID(), currdate);
		if(daily!=null && daily.getDataversion()>0){
			daily.setDataversion(daily.getDataversion()+1);
			daily.setStatus("Reset");
			this.accessDao.updateRuleAccessDailylySum(daily);			
		}
		RuleAccessMonthlyCharge monthly = accessDao.findRuleMonthlyChargeByRuleIDAndYearMonth(rule.getRuleID(), curretnYearMonth);
		if(monthly!=null && monthly.getDataversion()>0){
			monthly.setDataversion(monthly.getDataversion()+1);
			monthly.setStatus("Reset");
			this.accessDao.updateRuleAccessMonthlySum(monthly);			
		}
	}
	
	//#######################Not use##########################
	@Override
	public Rule getRuleByRuleCode(String rulecode, String version) throws GenericException {
		List<Rule> result = getRuleListVersion(version);
		if(result==null) return null;
		for(int i=0;i<result.size();i++){
			Rule rl = result.get(i);
			if(rl.getRulecode().equalsIgnoreCase(rulecode)){
				return rl;
			}
		}
		return null;
	}
	
	
	@Override
	public Rule getRuleByRuleCode(String rulecode) throws GenericException {
		List<Rule> result = listAllRule();
		if(result==null) return null;
		for(int i=0;i<result.size();i++){
			Rule rl = result.get(i);
			if(rl.getRulecode().equalsIgnoreCase(rulecode)){
				return rl;
			}
		}
		return null;
	}
	
	public List<Rule> getRuleBySearchVO(RuleSearchVO searchVO) throws GenericException {
		return ruleDao.listRuleBySearchVO(searchVO);
	}
	
	@Override
	public boolean checkRuleExisted(String ruleCode, RuleSearchVO searchVO)
			throws GenericException {
		Rule rule =  getRuleByRuleCode(ruleCode);
		if(rule!=null && rule.getRuleID() >0){
			return true;
		} else {
			List<Rule> ls =  getRuleBySearchVO(searchVO);
			if(ls!=null && ls.size()>0){
				return true;
			} 
			return false;
		}
	}
	//##################################################

	@Override
	public List<Rule> getRuleListVersion(String version) throws GenericException {
		Object obj = cacheMgr.getCacheEntry(CACHE_KEY_RULESBYVERSION+version);
		if(obj != null){
			List<Rule> l =  (List<Rule>) obj;
			if(l.size()>0) return l;
		}
		List<Rule> result = ruleDao.getRuleListByVersion(version);
		if(result != null) {
			cacheMgr.addCacheEntry(CACHE_KEY_RULESBYVERSION+version, result);
		}
		return result;
	}	
	
	@Override
	public Rule searchRule(String city, String productcode, String version, long gapDays)	throws GenericException {
		List<Rule> ruleList = getRuleListVersion(version);
		if(ruleList==null || ruleList.size()==0) {
			return null;
		}
		//Search rule by city+productcode+version
		for(int i=0;i<ruleList.size();i++){
			Rule rule = ruleList.get(i);
			if(StringUtils.equalsIgnoreCase(rule.getStatus(), "Enable") && 
			   StringUtils.equalsIgnoreCase(rule.getCity(), city)	&&
			   StringUtils.equalsIgnoreCase(rule.getProductcode(), productcode)){
				logger.info("Located rule for city="+city+", product code="+productcode+" and version"+ version + " {gapDays="+gapDays+", rule.gapdays"+rule.getGapdays() + ", rule.ID="+rule.getRuleID());
				if(gapDays >= rule.getGapdays()){
					return rule;
				}
			}
		}
		
		//Search rule by productcode
		for(int i=0;i<ruleList.size();i++){
			Rule rule = ruleList.get(i);
			if(StringUtils.equalsIgnoreCase(rule.getStatus(), "Enable") && 
			   StringUtils.equalsIgnoreCase(rule.getProductcode(), productcode)){
				logger.info("product code="+productcode+" and version"+ version + " {gapDays="+gapDays+", rule.gapdays"+rule.getGapdays() + ", rule.ID="+rule.getRuleID());				
				if(gapDays >= rule.getGapdays()){
					return rule;
				}
			}			
		}
		
		//Only has one record
		for(int i=0;i<ruleList.size();i++){
			Rule rule = ruleList.get(i);
			if(rule.getStatus().equalsIgnoreCase("Enable") && 
					gapDays >= rule.getGapdays()){
				logger.info("version"+ version + " {gapDays="+gapDays+", rule.gapdays"+rule.getGapdays() + ", rule.ID="+rule.getRuleID());		
				return rule;
			}
		}	

		return null;
	}

	private static String _APP_TYPE_ADS = "1";
	private static String _APP_TYPE_CHARGE = "2";
	
	private static String _VERSION_GROUP_KEY_PREFIX = "rules";
	
	@Override
	public Rule searchRule(String province, String city, String productcode, String version, String serviceprovider, long gapDays, boolean isOnlySearchAds)	throws GenericException{
		
		String cacheKey4Charge = version + productcode + city + province +  serviceprovider + _APP_TYPE_CHARGE;
		String cacheKey4Ads = version + productcode + city + province +  serviceprovider + _APP_TYPE_ADS;
		String groupID = version + _VERSION_GROUP_KEY_PREFIX;
		
		boolean needCharge = !isOnlySearchAds;
		Object obj  = null;
		if(needCharge) {
			//1,first get charge
			obj  = cacheMgr.getCacheEntry(cacheKey4Charge);
			if(obj!=null){
				return (Rule)obj;
			}
		}
		
		//###############Separate Rules as Charge and Ads Rule start##########################
		//TODO put in cache
		List<Rule> ruleList = getRuleListVersion(version);
		if(ruleList==null || ruleList.size()==0) {
			return null;
		}
		
		List<Rule> chargecList =  new ArrayList<Rule>();
		List<Rule> adsList = new ArrayList<Rule>();
		for(int i=0;i<ruleList.size();i++){
			Rule rule = ruleList.get(i);	
			if(StringUtils.equalsIgnoreCase(rule.getStatus(), "Enable")){
				//2.2 
				if(_APP_TYPE_ADS.endsWith(rule.getApptype())){//ADS Rule
					adsList.add(rule);
				} else {
					chargecList.add(rule);
				}
			}
					
		}
		//###############Separate Rules as Charge and Ads Rule End##########################		
		
		//1, first check calc rule if city is not null or default
		Rule  rule = null;
		if(needCharge){
			if(city!=null &&  !"DEFAULT".equalsIgnoreCase(city)) {
				if(chargecList.size()>0){
					rule = searchOneRule(chargecList, province, city, productcode, version, serviceprovider, gapDays, _APP_TYPE_CHARGE);
				}
			}		
			
			if(rule !=null){
				cacheMgr.addInGlobalCacheByGroup(groupID, cacheKey4Charge, rule);
				return rule;
			}
		}

		//2,then get ads
		obj  = cacheMgr.getCacheEntry(cacheKey4Ads);
		if(obj!=null){
			return (Rule)obj;
		}
	
		//2, and then check ads rule
		if(rule==null && adsList.size()>0){
			rule = searchOneRule(adsList, province, city, productcode, version, serviceprovider, gapDays, _APP_TYPE_ADS);
		}
		
		
		if(rule !=null){
			cacheMgr.addInGlobalCacheByGroup(groupID, cacheKey4Ads, rule);
		}
		
		return rule;
	}	
	
	public static String _DEFAULT_VAL= "DEFAULT";

	public boolean isMatchCity(String ruleCity, String requestCity){
		if(ruleCity==null){
			return false;
		} 
		String [] cities = StringUtils.split(ruleCity, "_");
		java.util.HashSet cityset = new HashSet();
		if(cities!=null){
			cityset.addAll(Arrays.asList(cities));
		}
		return cityset.contains(requestCity);
	}
	
	public static void main(String [] args) {
		RuleMgrImpl rl = new RuleMgrImpl();
		System.out.println(rl.isMatchCity("0512_025", "0512"));
		System.out.println(rl.isMatchCity("0512", "0512"));
		System.out.println(rl.isMatchCity("0519_0512_025", "0512"));
		System.out.println(rl.isMatchCity(_DEFAULT_VAL+"_0512_", "0512"));
		
		System.out.println(!rl.isMatchCity(_DEFAULT_VAL, "0512"));
		System.out.println(rl.isMatchCity(_DEFAULT_VAL, _DEFAULT_VAL));
		
	}
	
	
	private Rule searchOneRule(List<Rule> ruleList ,String province, String city, String productcode, String version, String serviceprovider, long gapDays, String type)	throws GenericException{
		if(ruleList==null || ruleList.size()==0) {
			return null;
		}
		
		for(int i=0;i<ruleList.size();i++){
			Rule rule = ruleList.get(i);
			if(StringUtils.equalsIgnoreCase(rule.getProductcode(), productcode)	&&
			   isMatchCity(rule.getCity(), city)	&&
			   StringUtils.equalsIgnoreCase(rule.getProvince(), province) && 
			   StringUtils.equalsIgnoreCase(rule.getServiceprovider(), serviceprovider)){
				logger.info("Located rule for province "+ province +"city="+city+", product code="+productcode+" and version"+ version +" and serviceprovider"+ serviceprovider
							+ " {gapDays="+gapDays+", rule.gapdays"+rule.getGapdays() + ", rule.ID="+rule.getRuleID());
				if(gapDays >= rule.getGapdays()){
					return rule;
				}
			}
		}
		
		for(int i=0;i<ruleList.size();i++){
			Rule rule = ruleList.get(i);
			if(StringUtils.equalsIgnoreCase(rule.getProductcode(), productcode)	&&
					isMatchCity(rule.getCity(), city)	&&
			   StringUtils.equalsIgnoreCase(rule.getProvince(), province) && 
			   StringUtils.equalsIgnoreCase(rule.getServiceprovider(), _DEFAULT_VAL)){
				logger.info("Located rule for province "+ province +"city="+city+", product code="+productcode+" and version"+ version 
						+" and serviceprovider"+ _DEFAULT_VAL + " {gapDays="+gapDays+", rule.gapdays"+rule.getGapdays() + ", rule.ID="+rule.getRuleID());
				if(gapDays >= rule.getGapdays()){
					return rule;
				}
			}
		}		
		
		for(int i=0;i<ruleList.size();i++){
			Rule rule = ruleList.get(i);
			if(StringUtils.equalsIgnoreCase(rule.getProductcode(), productcode)	&&
			   StringUtils.equalsIgnoreCase(rule.getCity(), _DEFAULT_VAL)	&&
			   StringUtils.equalsIgnoreCase(rule.getProvince(), province) && 
			   StringUtils.equalsIgnoreCase(rule.getServiceprovider(), serviceprovider)){
				logger.info("Located rule for province "+ province +"city="+_DEFAULT_VAL+", product code="+productcode+" and version"+ version 
						+" and serviceprovider"+ serviceprovider + " {gapDays="+gapDays+", rule.gapdays"+rule.getGapdays() + ", rule.ID="+rule.getRuleID());
				if(gapDays >= rule.getGapdays()){
					return rule;
				}
			}
		}	
		
		for(int i=0;i<ruleList.size();i++){
			Rule rule = ruleList.get(i);
			if(StringUtils.equalsIgnoreCase(rule.getProductcode(), productcode)	&&
			   StringUtils.equalsIgnoreCase(rule.getCity(), _DEFAULT_VAL)	&&
			   StringUtils.equalsIgnoreCase(rule.getProvince(), province) && 
			   StringUtils.equalsIgnoreCase(rule.getServiceprovider(), _DEFAULT_VAL)){
				logger.info("Located rule for province "+ province +"city="+_DEFAULT_VAL+", product code="+productcode+" and version"+ version 
						+" and serviceprovider"+ _DEFAULT_VAL + " {gapDays="+gapDays+", rule.gapdays"+rule.getGapdays() + ", rule.ID="+rule.getRuleID());
				if(gapDays >= rule.getGapdays()){
					return rule;
				}
			}
		}	
		
		for(int i=0;i<ruleList.size();i++){
			Rule rule = ruleList.get(i);
			if(StringUtils.equalsIgnoreCase(rule.getProductcode(), productcode)	&&
			   StringUtils.equalsIgnoreCase(rule.getCity(), _DEFAULT_VAL)	&&
			   StringUtils.equalsIgnoreCase(rule.getProvince(), _DEFAULT_VAL) && 
			   StringUtils.equalsIgnoreCase(rule.getServiceprovider(), serviceprovider)){
				logger.info("Located rule for province "+ _DEFAULT_VAL +"city="+_DEFAULT_VAL+", product code="+productcode+" and version"+ version 
						+" and serviceprovider"+ serviceprovider + " {gapDays="+gapDays+", rule.gapdays"+rule.getGapdays() + ", rule.ID="+rule.getRuleID());
				if(gapDays >= rule.getGapdays()){
					return rule;
				}
			}
		}	
		
		for(int i=0;i<ruleList.size();i++){
			Rule rule = ruleList.get(i);
			if(StringUtils.equalsIgnoreCase(rule.getProductcode(), productcode)	&&
			   StringUtils.equalsIgnoreCase(rule.getCity(), _DEFAULT_VAL)	&&
			   StringUtils.equalsIgnoreCase(rule.getProvince(), _DEFAULT_VAL) && 
			   StringUtils.equalsIgnoreCase(rule.getServiceprovider(), _DEFAULT_VAL)){
				logger.info("Located rule for province "+ _DEFAULT_VAL +"city="+_DEFAULT_VAL+", product code="+productcode+" and version"+ version 
						+" and serviceprovider"+ _DEFAULT_VAL + " {gapDays="+gapDays+", rule.gapdays"+rule.getGapdays() + ", rule.ID="+rule.getRuleID());
				if(gapDays >= rule.getGapdays()){
					return rule;
				}
			}
		}			

		//Part2
		for(int i=0;i<ruleList.size();i++){
			Rule rule = ruleList.get(i);
			if(StringUtils.equalsIgnoreCase(rule.getProductcode(), _DEFAULT_VAL)	&&
					isMatchCity(rule.getCity(), city)	&&
			   StringUtils.equalsIgnoreCase(rule.getProvince(), province) && 
			   StringUtils.equalsIgnoreCase(rule.getServiceprovider(), serviceprovider)){
				logger.info("Located rule for province "+ province +"city="+city+", product code="+_DEFAULT_VAL+" and version"+ version +" and serviceprovider"+ serviceprovider
							+ " {gapDays="+gapDays+", rule.gapdays"+rule.getGapdays() + ", rule.ID="+rule.getRuleID());
				if(gapDays >= rule.getGapdays()){
					return rule;
				}
			}
		}
		
		for(int i=0;i<ruleList.size();i++){
			Rule rule = ruleList.get(i);
			if(StringUtils.equalsIgnoreCase(rule.getProductcode(), _DEFAULT_VAL)	&&
					isMatchCity(rule.getCity(), city)	&&
			   StringUtils.equalsIgnoreCase(rule.getProvince(), province) && 
			   StringUtils.equalsIgnoreCase(rule.getServiceprovider(), _DEFAULT_VAL)){
				logger.info("Located rule for province "+ province +"city="+city+", product code="+_DEFAULT_VAL+" and version"+ version 
						+" and serviceprovider"+ _DEFAULT_VAL + " {gapDays="+gapDays+", rule.gapdays"+rule.getGapdays() + ", rule.ID="+rule.getRuleID());
				if(gapDays >= rule.getGapdays()){
					return rule;
				}
			}
		}		
		
		for(int i=0;i<ruleList.size();i++){
			Rule rule = ruleList.get(i);
			if(StringUtils.equalsIgnoreCase(rule.getProductcode(), _DEFAULT_VAL)	&&
			   StringUtils.equalsIgnoreCase(rule.getCity(), _DEFAULT_VAL)	&&
			   StringUtils.equalsIgnoreCase(rule.getProvince(), province) && 
			   StringUtils.equalsIgnoreCase(rule.getServiceprovider(), serviceprovider)){
				logger.info("Located rule for province "+ province +"city="+_DEFAULT_VAL+", product code="+_DEFAULT_VAL+" and version"+ version 
						+" and serviceprovider"+ serviceprovider + " {gapDays="+gapDays+", rule.gapdays"+rule.getGapdays() + ", rule.ID="+rule.getRuleID());
				if(gapDays >= rule.getGapdays()){
					return rule;
				}
			}
		}	
		
		for(int i=0;i<ruleList.size();i++){
			Rule rule = ruleList.get(i);
			if(StringUtils.equalsIgnoreCase(rule.getProductcode(), _DEFAULT_VAL)	&&
			   StringUtils.equalsIgnoreCase(rule.getCity(), _DEFAULT_VAL)	&&
			   StringUtils.equalsIgnoreCase(rule.getProvince(), province) && 
			   StringUtils.equalsIgnoreCase(rule.getServiceprovider(), _DEFAULT_VAL)){
				logger.info("Located rule for province "+ province +"city="+_DEFAULT_VAL+", product code="+_DEFAULT_VAL+" and version"+ version 
						+" and serviceprovider"+ _DEFAULT_VAL + " {gapDays="+gapDays+", rule.gapdays"+rule.getGapdays() + ", rule.ID="+rule.getRuleID());
				if(gapDays >= rule.getGapdays()){
					return rule;
				}
			}
		}	
		
		for(int i=0;i<ruleList.size();i++){
			Rule rule = ruleList.get(i);
			if(StringUtils.equalsIgnoreCase(rule.getProductcode(), _DEFAULT_VAL)	&&
			   StringUtils.equalsIgnoreCase(rule.getCity(), _DEFAULT_VAL)	&&
			   StringUtils.equalsIgnoreCase(rule.getProvince(), _DEFAULT_VAL) && 
			   StringUtils.equalsIgnoreCase(rule.getServiceprovider(), serviceprovider)){
				logger.info("Located rule for province "+ _DEFAULT_VAL +"city="+_DEFAULT_VAL+", product code="+productcode+" and version"+ version 
						+" and serviceprovider"+ serviceprovider + " {gapDays="+gapDays+", rule.gapdays"+rule.getGapdays() + ", rule.ID="+rule.getRuleID());
				if(gapDays >= rule.getGapdays()){
					return rule;
				}
			}
		}	
		
		for(int i=0;i<ruleList.size();i++){
			Rule rule = ruleList.get(i);
			if(StringUtils.equalsIgnoreCase(rule.getProductcode(), _DEFAULT_VAL)	&&
			   StringUtils.equalsIgnoreCase(rule.getCity(), _DEFAULT_VAL)	&&
			   StringUtils.equalsIgnoreCase(rule.getProvince(), _DEFAULT_VAL) && 
			   StringUtils.equalsIgnoreCase(rule.getServiceprovider(), _DEFAULT_VAL)){
				logger.info("Located rule for province "+ _DEFAULT_VAL +"city="+_DEFAULT_VAL+", product code="+_DEFAULT_VAL+" and version"+ version 
						+" and serviceprovider"+ _DEFAULT_VAL + " {gapDays="+gapDays+", rule.gapdays"+rule.getGapdays() + ", rule.ID="+rule.getRuleID());
				if(gapDays >= rule.getGapdays()){
					return rule;
				}
			}
		}			
		
		return null;
	}	
	
	
	
	@Override
	public Rule getSystemRuleByVersion(String version) throws GenericException {
		Object obj = cacheMgr.getCacheEntry(CACHE_KEY_SYSTEMRULE_BY_VERSION+version);
		if(obj != null){
			return (Rule)obj;
		}
		
		Rule result = ruleDao.getSystemRuleByVersion(version);
		if(result != null) {
			cacheMgr.addCacheEntry(CACHE_KEY_SYSTEMRULE_BY_VERSION+version, result);
		}
		return result;
	}
	@Override
	public List<Rule> listChargeRules() throws GenericException {
		List<Rule> ruleList = this.listAllRule();
		if(ruleList==null || ruleList.size()==0) {
			return null;
		}
		List<Rule> chargecList =  new ArrayList<Rule>();
//		List<Rule> adsList = new ArrayList<Rule>();
		for(int i=0;i<ruleList.size();i++){
			Rule rule = ruleList.get(i);
				//2.2 
				if(this._APP_TYPE_CHARGE.equalsIgnoreCase(rule.getApptype())){//ADS Rule
//					adsList.add(rule);
//				} else {
					chargecList.add(rule);
				}
					
		}
		return chargecList;
	}
	@Override
	public List<RuleAccessDailyCharge> listRuleAccessDailyCharge(RptSearchVO vo)
			throws GenericException {
		return ruleDao.listRuleAccessDailyCharge(vo);
	}
	
}
