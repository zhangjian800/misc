package com.dolphin.webapp.biz;

import java.util.List;

import com.dolphin.common.exception.GenericException;
import com.dolphin.webapp.vo.Access;
import com.dolphin.webapp.vo.AccessResponse;
import com.dolphin.webapp.vo.AccessSearchVO;

public import com.dolphin.webapp.vo.AccessSumic import com.dolphin.webapp.vo.PhoneChargeic interface AccessMgr {
	
	public int insertAccess(Access access) throws GenericException;
	public AccessResponse newAccess(Access access) throws GenericException;
	public Access loadAccessAccessID(int accessID) throws GenericException;
	public List<Access> listAllAccess() throws GenericException;
	public List<Access> listAccess(AccessSearchVO vo) throws GenericException;
	
	public void insertAndUpdatePhone(String imsi, String phoneNum) throws Generio) throws GenericException;
	
public long insertPhoneCharge(PhoneCharge phoneCharge) throws GenericException;

	
	public void disablesPhones(List<String> imsis) throws GenericException;
	
	public void updateAllPhones() throws GenericException;
	
	public AccessSum findAccessSumByIMSIAndYearMonth(String imsi, String yearmonth)	throws GenericException;
	public void updateAccessSum(AccessSum accesssum)	throws GenericException ;

}
