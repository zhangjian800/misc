package com.dolphin.webapp.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map
import com.dolphin.common.exception.GenericException;
import com.dolphin.webapp.common.dao.DefaultGenericDaoImpl;
import com.dolphin.webapp.vo.Access;
import com.dolphin.webapp.vo.AccessMain;
import com.dolphin.webapp.vo.AccessResponse;
import com.dolphin.webapp.vo.AccessSearchVO;
import com.dolphin.webapp.vo.AccessSum;

public claimport com.dolphin.webapp.vo.PhoneCharge;
import com.dolphin.webapp.vo.RuleAccessDailyCharge;
import com.dolphin.webapp.vo.RuleAccessMonthlyChargeclass AccessDaoImpl extends DefaultGenericDaoImpl<Access> implements AccessDao<Access> {
	
	private static final String _SQL_STATEMENT_FINDACCESSBYSEARCHVO = "findAccessBySearchVO";

	
	private static final String _SQL_STATEMENT_FIND_ACCESSRESPONSE = "findAccessResponseByPK";
	
	private static final String _SQL_STATEMENT_FIND_PHONE_ACCESS_MAIN_BYSMSI = "findPhoneAccessMainByIMSI";
	private static final String _SQL_STATEMENT_INSERT_PHONE_ACCESS_MAIN = "insertPhoneAccessMain";
	private static final String _SQL_STATEMENT_UPDATE_PHONENUM = "updatePhoneNum";

	
	private static final	private static final String _SQL_STATEMENT_FIND_NEED_HANDLE_PHONES = "findNeedHandlePhoneAccessMain";
final String _SQL_STATEMENT_FIND_PHONE_ACCESS_MAIN_BYSMSI = "findPhivate static final String _SQL_STATEMENT_INSERT_PHONE_ACCESS_SUM = "insertAccessSum";
	private static final String _SQL_STATEMENT_UPDATE_PHONE_ACCESS_SUM = "updateAccessSum";

	private static final String _SQL_STATEMENT_GET_SUCCESSHITS_SUM = "getSuccessHitSumByIMSIAndYearMonth";
	
	public AccessResponse loadAccesrivate static final String _SQL_STATEMENT_FIND_RULE_MONTHLY_ACCESS_SUM = "findRuleAccessMonthlySumByRuleID";
	private static final String _SQL_STATEMENT_INSERT_RULE_MONTHLY_ACCESS_SUM = "insertRuleAccessMonthlySum";
	private static final String _SQL_STATEMENT_UPDATE_RULE_MONTHLY_ACCESS_SUM = "updateRuleAccessMonthlySum";
	
	private static final String _SQL_STATEMENT_FIND_RULE_DAILYLY_ACCESS_SUM = "findRuleAccessDailySumByRuleID";
	private static final String _SQL_STATEMENT_INSERT_RULE_DAILYLY_ACCESS_SUM = "insertRuleAccessDailySum";
	private static final String _SQL_STATEMENT_UPDATE_RULE_DAILYLY_ACCESS_SUM  = "updateRuleAccessDailySum";
	
	
	private static final String  _SQL_STATE_SQL_STATEMENT_GET_FAILMOUNT_BYIMSIANDMONTHLY = "getFailmountByIMSIAndMonthy";
	private static final String _SQL_STATEMENT_GET_FAILMOUNT_BYRULEANDDATE = "getFailmountByRuleAndDate";
	private static final String _SQL_STATEMENT_GET_FAILMOUNT_BYRULEANDMONTHLY  = "getFailmountByRuleAndMonthy";
	
	private static final String _SQL_STATEMENT_DISABLE_PHONE = "disablePhonesprivate static final String  _SQL_STATEMENT_INSERT_PHONE_CHARGE  = "insertPhoneChargeloadAccessResponseByAccessID(int accessID) throws GenericException {
		return (AccessResponse)this.queryForObject(_SQL_STATEMENT_FIND_ACCESSRESPONSE, accessID);
	}
	
	@Override
	public AccessMain loadPhoneAccessMainBySMSI(String smsi)
			throws GenericException {
		Object obj = this.queryForObject(_SQL_STATEMENT_FIND_PHONE_ACCESS_MAIN_BYSMSI, smsi);
		if(obj==null) return null;
		return (AccessMain)obj;
	}
	
	@Override
	public int getSuccessHitSumByIMSIAndYearMonth(String imsi, String yearmonth)	throws GenericException{
		AccessSum accesssum = new AccessSum();
		accesssum.setImsi(imsi);
		accesssum.setYearmonth(yearmonth);
		Object obj = this.queryForObject(_SQL_STATEMENT_GET_SUCCESSHITS_SUM, accesssum);
		
		if(obj==null) return 0;
		return ((Integer)obj).intValue();

	}
	
	@Override
	public void insertAccessMain(AccessMain accessMain)	throws GenericException {
		 this.insert(_SQL_STATEMENT_INSERT_PHONE_ACCESS_MAIN, accessMain);
	}
	
	@Override
	public void updatePhoneNum(String phoneNo, String imsi) throws GenericException {
		AccessMain(AccessMain accessMain) throws GenericException {de	
	public AccessSum findAccessSumByIMSIAndYearMonth(String imsi, String yearmonth)	throws GenericException {
		AccessSum accesssum = new AccessSum();
		accesssum.setImsi(imsi);
		accesssum.setYearmonth(yearmonth);
		Object obj = this.queryForObject(_SQL_STATEMENT_FIND_PHONE_ACCESS_SUM, accesssum);
		if(obj==null) return null;
		return (AccessSum)obj;
	}
	
	@Override
	public void insertAccessSum(AccessSum accesssum) throws GenericException  {
		 this.insert(_SQL_STATEMENT_INSERT_PHONE_ACCESS_SUM, accesssum);
	}
	
	@Override
	public void updateAccessSum(AccessSum accesssum) throws GenericException {
		 this.insert(_SQL_STATEMENT_UPDATE_PHONE_ACCESS_SUM, accesssum);
	}

	@Override
	public List<Access> listAccess(AccessSearchVO vo) throws GenericException {
		Object obj = queryForList(_SQL_STATEMENT_FINDACCESSBYSEARCHVO, vo);
		if(obj==null) return null;
		return (List<Access>)obj; 
	}
	
}

	@Override
	public RuleAccessMonthlyCharge findRuleMonthlyChargeByRuleIDAndYearMonth(
			int ruleID, String yearmonth) throws GenericException {
		RuleAccessMonthlyCharge accesssum = new RuleAccessMonthlyCharge();
		accesssum.setRuleID(ruleID);
		accesssum.setYearmonth(yearmonth);
		
		Object obj = this.queryForObject(_SQL_STATEMENT_FIND_RULE_MONTHLY_ACCESS_SUM, accesssum);
		if(obj==null) return null;
		return (RuleAccessMonthlyCharge)obj;
	}

	@Override
	public void insertRuleAccessMonthlySum(RuleAccessMonthlyCharge monthlysum)
			throws GenericException {
		 this.insert(_SQL_STATEMENT_INSERT_RULE_MONTHLY_ACCESS_SUM, monthlysum);
		
	}

	@Override
	public int updateRuleAccessMonthlySum(RuleAccessMonthlyCharge monthlysum)
			throws GenericException {
		 return this.UPDATE(_SQL_STATEMENT_UPDATE_RULE_MONTHLY_ACCESS_SUM, monthlysum);
	}

	@Override
	public RuleAccessDailyCharge findRuleDailylyChargeByRuleIDAndYearMonth(int ruleID,
			String currdate) throws GenericException {
		RuleAccessDailyCharge accesssum = new RuleAccessDailyCharge();
		accesssum.setRuleID(ruleID);
		accesssum.setCurrdate(currdate);
		Object obj = this.queryForObject(_SQL_STATEMENT_FIND_RULE_DAILYLY_ACCESS_SUM, accesssum);
		if(obj==null) return null;
		return (RuleAccessDailyCharge)obj;
	}

	@Override
	public void insertRuleAccessDailylySum(RuleAccessDailyCharge dailysum)
			throws GenericException {
		 this.insert(_SQL_STATEMENT_INSERT_RULE_DAILYLY_ACCESS_SUM, dailysum);
	}

	@Override
	public int updateRuleAccessDailylySum(RuleAccessDailyCharge dailysum)
			throws GenericException {
		 return this.UPDATE(_SQL_STATEMENT_UPDATE_RULE_DAILYLY_ACCESS_SUM, dailysum);
	}

	@Override
	public long insertPhoneCharge(PhoneCharge charge) throws GenericException {
		 Object obj =  this.insert(_SQL_STATEMENT_INSERT_PHONE_CHARGE, charge);
		 return ((Long)obj).longValue();
	}
	
}
	@Override
	public void disablesPhones(List<String> imsis) throws GenericException {
		if(imsis==null || imsis.size()==0){
			return;
		}
		String strImsis = "";
		for(int i=0;i<imsis.size();i++){
			strImsis = "'"+imsis.get(i) + "',";
		}
		int idx = strImsis.indexOf(",");
		if(idx >0){
			strImsis = strImsis.substring(0, idx-1);
		}
		if(strImsis!=null && strImsis.length()>0){
			this.UPDATE(_SQL_STATEMENT_DISABLE_PHONE, strImsis);
		}
		return;
	}

	@Override
	public List<AccessMain> loadNeedHandlePhones() throws GenericException {
		Object obj = queryForList(_SQL_STATEMENT_FIND_NEED_HANDLE_PHONES, null);
		if(obj==null) return null;
		return (List<AccessMain>)obj; 
	}
	
	public Float getFailmountByIMSIAndMonthy(String yearmonth, String imsi) throws GenericException {
		Map map = new HashMap();
		map.put("yearmonth", String.valueOf(yearmonth));
		map.put("imsi", imsi);
		Object obj = this.queryForObject(_SQL_STATEMENT_GET_FAILMOUNT_BYIMSIANDMONTHLY, map);
		if(obj!=null){
			return (Float)obj;
		}
		return new Float(0);	
	}	
	
	public Float getFailmountByRuleAndDate(int ruleID, String currdate) throws GenericException {
		Map map = new HashMap();
		map.put("ruleID", String.valueOf(ruleID));
		map.put("currdate", currdate);
		Object obj = this.queryForObject(_SQL_STATEMENT_GET_FAILMOUNT_BYRULEANDDATE, map);
		if(obj!=null){
			return (Float)obj;
		}
		return new Float(0);
	}		
	
	public Float getFailmountByRuleAndMonthy(int ruleID, String currmonth) throws GenericException {
		Map map = new HashMap();
		map.put("ruleID", String.valueOf(ruleID));
		map.put("yearmonth", currmonth);
		Object obj = this.queryForObject(_SQL_STATEMENT_GET_FAILMOUNT_BYRULEANDMONTHLY, map);
		if(obj!=null){
			return (Float)obj;
		}
		return new Float(0);	
	}	
}
