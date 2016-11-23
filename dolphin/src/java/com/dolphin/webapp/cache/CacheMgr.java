package com.dolphin.webapp.cache;

import com.dolphin.common.exception.GenericException;

public interface CacheMgr {
	
	public Object getCacheEntry(String cacheKey) throws GenericException;
	public void addCacheEntry(String cacheKey, Object obj) throws GenericException;
	public void addCacheEntry(String cacheKey, Object obj, int seconds) throws GenericException;
	public void flushCacheEntry(String cacheKey) throws GenericException;
	public void flushAll() throws GenericException;
	
	
	
	/**
	 * @param groupID
	 * @param key
	 * @param value
	 * @throws GenericException
	 */
	public void addInGlobalCacheByGroup(String groupID, String key, Object value) throws GenericException;
	
	/**
	 * @param groupID
	 * @param key
	 * @param value
	 * @throws GenericException
	 */
	public void flushGlobalCacheByGroup(String groupID) throws GenericException;

}
