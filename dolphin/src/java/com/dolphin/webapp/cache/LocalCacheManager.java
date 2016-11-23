package com.dolphin.webapp.cache;

import com.dolphin.common.exception.GenericException;
import com.dolphin.common.log.DolpiLogger;
import com.dolphin.webapp.sms.access.GenerateAccessAction;
import com.opensymphony.oscache.base.NeedsRefreshException;
import com.opensymphony.oscache.general.GeneralCacheAdministrator;

public class LocalCacheManager implements CacheMgr{

	DolpiLogger logger = new DolpiLogger(LocalCacheManager.class.getName());
	
	GeneralCacheAdministrator oscacheAdmin;
	public void setOscacheAdmin(GeneralCacheAdministrator oscacheAdmin) {
		this.oscacheAdmin = oscacheAdmin;
	}

	@Override
	public Object getCacheEntry(String cacheKey) throws GenericException {
		try {
			return oscacheAdmin.getFromCache(cacheKey);
		} catch (NeedsRefreshException e) {
			logger.info("Cannot get cache for " + cacheKey);
		}
		return null;
	}

	@Override
	public void addCacheEntry(String cacheKey, Object obj) throws GenericException {
		try {
			oscacheAdmin.putInCache(cacheKey, obj);
		} catch (Exception e) {
			logger.info("Cannot add cache for " + cacheKey);
		}
	}

	@Override
	public void flushCacheEntry(String cacheKey) throws GenericException {
		try {
			oscacheAdmin.flushEntry(cacheKey);
		} catch (Exception e) {
			logger.info("Cannot flush cache for " + cacheKey);
		}
	}


    private static final String GROUP_KEY_SEPRATOR = "~#~";
	/**
	 * @param groupID
	 * @param key
	 * @param value
	 * @throws GenericException
	 */
	public void addInGlobalCacheByGroup(String groupID, String key, Object value)
			throws GenericException {
//		if(groupID!=null){
//			oscacheAdmin.putInCache(key, value, new String [] {groupID});
//		} else {
//			addCacheEntry(key, value);
//		}
	}
	
	/**
	 * @param groupID
	 * @param key
	 * @param value
	 * @throws GenericException
	 */
	public void flushGlobalCacheByGroup(String groupID)
			throws GenericException {
//		oscacheAdmin.flushGroup(groupID);
	}	
	
	@Override
	public void flushAll() throws GenericException {
		try {
			oscacheAdmin.flushAll();
		} catch (Exception e) {
			logger.info("Cannot flush All");
		}
	}

	@Override
	public void addCacheEntry(String cacheKey, Object obj, int seconds)
			throws GenericException {
		try {
			oscacheAdmin.putInCache(cacheKey, obj);
		} catch (Exception e) {
			logger.info("Cannot add cache for " + cacheKey);
		}
		
	}
	
}
