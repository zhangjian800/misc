package com.dolphin.webapp.biz;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.dolphin.common.exception.GenericException;
import com.dolphin.webapp.cache.CacheMgr;
import com.dolphin.webapp.cache.LocalCacheManager;
import com.dolphin.webapp.dao.PublicDao;
import com.dolphin.webapp.vo.ConfirmChargeRptSearchVO;
import com.dolphin.webapp.vo.PhoneCharge;
import com.dolphin.webapp.vo.PhoneNumType;
import com.dolphin.webapp.vo.PhoneProduct;
import com.dolphin.webapp.vo.SystemConfig;

public class PublicMgrImpl implements PublicMgr{
	
	PublicDao pubDao;
	CacheMgr cacheMgr;
	LocalCacheManager localCacheMgr;
	
	public void setPubDao(PublicDao pubDao) {
		this.pubDao = pubDao;
	}
	public void setCacheMgr(CacheMgr cacheMgr) {
		this.cacheMgr = cacheMgr;
	}
	public void setLocalCacheMgr(LocalCacheManager localCacheMgr) {
		this.localCacheMgr = localCacheMgr;
	}

	public final static String CACHE_KEY_ALL_PRODUCTS = "PRODUCTS";
	public final static String CACHE_KEY_PRODUCT= "PRODUCT_";
	
	public final static String CACHE_KEY_ALL_VERSIONS = "VERSIONS";
	private final static String CACHE_KEY_ALL_PROVINCES = "PROVINCES";
	private final static String CACHE_KEY_CITIESBYPROVINCE = "CITIES_";
	public final static String CACHE_KEY_SYSTEMRULE= "SYSTEMRULE";
	private final static String CACHE_KEY_PHONENUMTYPES= "PHONENUMTYPES";	

	private final static String CACHE_KEY_PHONE_PREFIX = "PHONENUMPREFIX_";	

	
	@Override
	public List<PhoneProduct> getAllProducts() throws GenericException {
		Object obj = cacheMgr.getCacheEntry(CACHE_KEY_ALL_PRODUCTS);
		if(obj != null){
			return (List<PhoneProduct>) obj;
		}
		List<PhoneProduct> result = pubDao.getAllProducts();
		if(result != null) {
			cacheMgr.addCacheEntry(CACHE_KEY_ALL_PRODUCTS, result);
		}
		return result;
	}

	@Override
	public List<HashMap> getAllVersions() throws GenericException {
		Object obj = cacheMgr.getCacheEntry(CACHE_KEY_ALL_VERSIONS);
		if(obj != null){
			return (List<HashMap>) obj;
		}		
		List<HashMap> result = pubDao.getAllVersions();
		if(result != null) {
			cacheMgr.addCacheEntry(CACHE_KEY_ALL_VERSIONS, result);
		}
		return result;
	}

	@Override
	public List<HashMap> getAllProvinces() throws GenericException {
		Object obj = cacheMgr.getCacheEntry(CACHE_KEY_ALL_PROVINCES);
		if(obj != null){
			return (List<HashMap>) obj;
		}		
		List<HashMap> result = pubDao.getAllProvinces();
		if(result != null) {
			cacheMgr.addCacheEntry(CACHE_KEY_ALL_PROVINCES, result);
		}
		return result;
	}
	
	@Override
	public void  reloadProvinceAndCities() throws GenericException {
		cacheMgr.flushCacheEntry(CACHE_KEY_ALL_PROVINCES);
		List<HashMap> provinces = getAllProvinces();
		for (int i = 0; i < provinces.size(); i++) {
			HashMap map = provinces.get(i);
			getCitiesByProcince((String) map.get("provicecode"));
		}
	}
	
	@Override
	public List<HashMap> getCitiesByProcince(String provinceCode) throws GenericException {
		String cacheKey = CACHE_KEY_CITIESBYPROVINCE+provinceCode;
		Object obj = cacheMgr.getCacheEntry(cacheKey);
		if(obj != null){
			return (List<HashMap>) obj;
		}		
		List<HashMap> result = pubDao.getCitiesByProcince(provinceCode);
		if(result != null) {
			cacheMgr.addCacheEntry(cacheKey, result);
		}
		return result;
	}
	
//	@Override
//	public List<PhoneNumType> getAllPhoneNumTypes() throws GenericException {
////		List<PhoneNumType> list = pubDao.getAllPhoneNumTypes();
////		for(int i=0 ; i<list.size(); i++){
////			PhoneNumType phoneNum = list.get(i);
////			localCacheMgr.addCacheEntry(CACHE_KEY_PHONE_PREFIX + phoneNum.getPhonePrefix(), phoneNum);
////		}
//		return null;
//	}
//	
//	@Override
//	public PhoneNumType getPhoneNumType(String prefix) 	throws GenericException {
//		Object obj = cacheMgr.getCacheEntry(CACHE_KEY_PHONE_PREFIX + prefix);
//		if(obj != null){
//			return (PhoneNumType) obj;
//		}
//		PhoneNumType result = pubDao.getPhoneNumType(prefix);
//		if(result != null) {
//			cacheMgr.addCacheEntry(CACHE_KEY_PHONE_PREFIX + prefix, result);
//		}
//		return result;
//	}
	
	public void reloadPhoneNumTypes() 	throws GenericException {
		localCacheMgr.flushCacheEntry(CACHE_KEY_PHONENUMTYPES);
		getAllPhoneNumTypes();
		
	}
	
	@Override
	public List<PhoneNumType> getAllPhoneNumTypes() 	throws GenericException {
		Object obj = localCacheMgr.getCacheEntry(CACHE_KEY_PHONENUMTYPES);
		if(obj != null){
			return (List<PhoneNumType>) obj;
		}
		List<PhoneNumType> result = pubDao.getAllPhoneNumTypes();
		if(result != null) {
			localCacheMgr.addCacheEntry(CACHE_KEY_PHONENUMTYPES, result);
		}
		return result;
		
	}
	
	@Override
	public PhoneNumType getPhoneNumType(String prefix) 	throws GenericException {
		Object obj = cacheMgr.getCacheEntry(CACHE_KEY_PHONE_PREFIX + prefix);
		if(obj != null){
			return (PhoneNumType) obj;
		}		
//		PhoneNumType  result = pubDao.getPhoneNumType(prefix);
//		if(result != null){
//			cacheMgr.addCacheEntry(CACHE_KEY_PHONE_PREFIX + prefix, result);
//			return result;
//		}
		List<PhoneNumType>  list = getAllPhoneNumTypes();
		for(int i=0 ; i<list.size(); i++){
			PhoneNumType phoneNum = list.get(i);
			if(phoneNum.getPhonePrefix().equalsIgnoreCase(prefix)){
				cacheMgr.addCacheEntry(CACHE_KEY_PHONE_PREFIX + prefix, phoneNum);
				return phoneNum;
			}
		}
		return null;
	}
	
	@Override
	public void insertPhoneProduct(PhoneProduct product) 	throws GenericException {
		pubDao.insertPhoneProduct(product);
		
	}
	
	@Override
	public PhoneProduct getPhoneProductAndInsert(String productCode, String version) throws GenericException {
		boolean isExist = false;
		List<PhoneProduct> allProducts = getAllProducts();
		for(int i=0 ; i<allProducts.size(); i++){
			PhoneProduct product = allProducts.get(i);
			if(product.getProductcode().equalsIgnoreCase(productCode)){
				isExist = true;
				return product;
			}
		}
		///Added on 3/9 checked if cached
		Object obj = cacheMgr.getCacheEntry(CACHE_KEY_PRODUCT + productCode);
		if(obj != null && obj instanceof PhoneProduct ) {
			return (PhoneProduct)obj;
		}
		
		if(!isExist){
			PhoneProduct product = new PhoneProduct();
			product.setProductcode(productCode);
			product.setProductdesc(productCode);
			product.setVersion(version);
			product.setStatus("Enable");
			insertPhoneProduct(product);
			
			///Added on 3/9 checked if cached
			cacheMgr.addCacheEntry(CACHE_KEY_PRODUCT + productCode, product);
			
			return product;
		}
		return null;
	}
	@Override
	public SystemConfig getSystemConfig() throws GenericException {
		Object obj = cacheMgr.getCacheEntry(CACHE_KEY_SYSTEMRULE);
		if(obj != null){
			return (SystemConfig) obj;
		}
		SystemConfig result = pubDao.getSystemConfig();
		if(result != null) {
			cacheMgr.addCacheEntry(CACHE_KEY_SYSTEMRULE, result);
		}
		return result;
	}
	
	@Override
	public void updatetSystemConfig(SystemConfig sc) throws GenericException {
		pubDao.updatetSystemConfig(sc);
		cacheMgr.addCacheEntry(CACHE_KEY_SYSTEMRULE, sc);
	}
	
	//Time as 8:00 or 08:00
	private int [] getHourMin(String time) {
		int [] result = new int [2];
		int idx = time.indexOf(":");
		result[0] = Integer.parseInt(time.substring(0, idx).trim());
		result[1] = Integer.parseInt(time.substring(idx+1).trim());
		return result;
	}
	
	public boolean isRestTime() {
		
		boolean isRestTime = false;

		try {
			Date accessTime = Calendar.getInstance().getTime();
			SystemConfig sc = getSystemConfig();
			
			//if  the values is 0, by pass
			if(sc.getIsblockworkingtime() ==0){
				return false;
			}			
			
			int [] start  = getHourMin(sc.getBegintime());
			int startHour = start[0];
			int startMins = start[1];

			int [] end  = getHourMin(sc.getEndtime());
			int endHour = end[0];
			int endMins = end[1];
			
			Calendar now = Calendar.getInstance();
			now.setTime(accessTime);
			
			int currentHour = now.get(Calendar.HOUR_OF_DAY);
			int currentMins = now.get(Calendar.MINUTE);
			
			if(currentHour >= startHour && currentHour <=endHour){
				if(currentHour ==  startHour){
					if(currentMins <startMins){
						isRestTime = true;
					}
				}
				if(currentHour ==  endHour){
					if(currentMins > endMins){
						isRestTime = true;
					}
				}
			} else {
				isRestTime = true;
			}
			
			return isRestTime;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public List<PhoneCharge> listPhoneCharge(ConfirmChargeRptSearchVO vo)
			throws GenericException {
		return this.pubDao.listPhoneCharge(vo);
	}	

}
