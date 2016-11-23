package com.dolphin.webapp.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map
import com.dolphin.common.exception.GenericException;
import com.dolphin.common.vo.DOLValueObject;
import com.dolphin.webapp.common.dao.GenericDao;
import com.dolphin.webapp.vo.Access;
import com.dolphin.webapp.vo.AccessMain;
import com.dolphin.webapp.vo.AccessResponse;
import com.dolphin.webapp.vo.AccessSearchVO;
import com.dolphin.webapp.vo.AccessSum;

public inteimport com.dolphin.webapp.vo.PhoneCharge;
import com.dolphin.webapp.vo.RuleAccessDailyCharge;
import com.dolphin.webapp.vo.RuleAccessMonthlyChargenterface AccessDao<T extends DOLValueObject> extends GenericDao<T>{

	public List<Access>  listAccess(AccessSearchVO vo) throws GenericException;
	
	public AccessResponse loadAccessResponseByAccessID(int accessID) throws GenericException;
	
	public AccessMain loadPhoneAccessMainBySMSI(String smsi) throws GenericException;
	public void insertAccessMain(AccessMain accessMain)	throws GenericException;
	public void updatePhoneNum(String phoneNo, Str(AccessMain accessMainon;
	
	public AccessSum findAcitSumByIMSIAndYearMonth(String imsi, String yearmonth)	throws GenericException;

	public void insertAccessSum(AccessAccessSum findAccessSumByIMSIAndYearMonth(String imsi, String yearmonth)	throws GenericException;Sum(AccessSum accesssum) throws GenericException;
	public void updateAccessSum(AccessSum accesssum) throws GenericException;	

}
	
	public RuleAccessMonthlyCharge findRuleMonthlyChargeByRuleIDAndYearMonth(int ruleID, String yearmonth)	throws GenericException;
	public void insertRuleAccessMonthlySum(RuleAccessMonthlyCharge monthlysum) throws GenericException;
	public int updateRuleAccessMonthlySum(RuleAccessMonthlyCharge monthlysum) throws GenericException;	
	
	public RuleAccessDailyCharge findRuleDailylyChargeByRuleIDAndYearMonth(int ruleID, String currdate)	throws GenericException;
	public void insertRuleAccessDailylySum(RuleAccessDailyCharge dailysum) throws GenericException;
	public int updateRuleAccessDailylySum(RuleAccessDailyCharge dailysum) throws GenericException;	
	
	
	public long insertPhoneCharge(PhoneCharge charge) throws GenericException;

}
	
	public void disablesPhones(List<String> imsis) throws GenericException;
	
	public List<AccessMain> loadNeedHandlePhones()  throws GenericException;
	
	public Float getFailmountByIMSIAndMonthy(String yearmonth, String imsi) throws GenericException;
	public Float getFailmountByRuleAndDate(int ruleID, String currdate) throws GenericException;
	public Float getFailmountByRuleAndMonthy(int ruleID, String currmonth) throws GenericException;	
}
