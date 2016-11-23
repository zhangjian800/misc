package com.dolphin.webapp.biz;

import java.util.HashMap;
import java.util.List;

import com.dolphin.common.exception.GenericException;
import com.dolphin.webapp.vo.ConfirmChargeRptSearchVO;
import com.dolphin.webapp.vo.PhoneCharge;
import com.dolphin.webapp.vo.PhoneNumType;
import com.dolphin.webapp.vo.PhoneProduct;
import com.dolphin.webapp.vo.SystemConfig;

public interface PublicMgr {
	
	public List<PhoneProduct> getAllProducts() throws GenericException;
	
	public List<HashMap> getAllVersions() throws GenericException;
	public List<HashMap> getAllProvinces() throws GenericException;
	public List<HashMap> getCitiesByProcince(String provinceCode) throws GenericException;
	
	public List<PhoneNumType> getAllPhoneNumTypes() 	throws GenericException;
	public PhoneNumType getPhoneNumType(String prefix)	throws GenericException;
	public void insertPhoneProduct(PhoneProduct product)	throws GenericException;
	
	public PhoneProduct getPhoneProductAndInsert(String productCode, String version) throws GenericException;
	
	public SystemConfig getSystemConfig() throws GenericException;	
	public void updatetSystemConfig(SystemConfig sc) throws GenericException;	
	
	public boolean isRestTime();
	
	public List<PhoneCharge> listPhoneCharge(ConfirmChargeRptSearchVO vo) throws GenericException;
	
	
	public void  reloadProvinceAndCities() throws GenericException ;
	public void reloadPhoneNumTypes() 	throws GenericException ;
}
