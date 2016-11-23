package com.dolphin.webapp.cache;

import java.io.IOException;

import com.dolphin.common.exception.GenericException;
import com.dolphin.common.log.DolpiLogger;

import net.spy.memcached.AddrUtil;
import net.spy.memcached.MemcachedClient;
public class MemcachedManager implements CacheMgr{
	
	DolpiLogger logger = new DolpiLogger(MemcachedManager.class.getName());

	
	MemcachedClient memcachedClient;
	public void setMemcachedClient(MemcachedClient memcachedClient) {
		this.memcachedClient = memcachedClient;
	}

	@Override
	public Object getCacheEntry(String cacheKey) throws GenericException {
		try {
			return memcachedClient.get(cacheKey);
		} catch (Exception e) {
			logger.info("Cannot get cache for " + cacheKey);
		}
		return null;
	}

	@Override
	public void addCacheEntry(String cacheKey, Object obj) throws GenericException {
		try {
			memcachedClient.set(cacheKey,  60*60*24*30,  obj);
		} catch (Exception e) {
			logger.info("Cannot add cache for " + cacheKey);
		}
	}

	@Override
	public void flushCacheEntry(String cacheKey) throws GenericException {
		try {
			memcachedClient.delete(cacheKey);
		} catch (Exception e) {
			logger.info("Cannot flush cache for " + cacheKey);
		}
	}

	@Override
	public void flushAll() throws GenericException {
		try {
			memcachedClient.flush();
		} catch (Exception e) {
			logger.info("Cannot flush All");
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
		
		if(groupID!=null){
			Object groupObj = getCacheEntry(groupID);
			String groupKeyValue = null;
			if(groupObj!=null){
				groupKeyValue = (String)groupObj + GROUP_KEY_SEPRATOR + key;
			}else{
				groupKeyValue = key;
			}
			addCacheEntry(groupID, groupKeyValue,90 * 60);
			addCacheEntry(key, value, 60 * 60);
		} else {
			addCacheEntry(key, value, 60 * 60);
		}
	}
	
	/**
	 * @param groupID
	 * @param key
	 * @param value
	 * @throws GenericException
	 */
	public void flushGlobalCacheByGroup(String groupID)
			throws GenericException {
		Object groupObj = getCacheEntry(groupID);
		if(groupObj!=null){
			String groupKeyValue = (String)groupObj;
			if(groupKeyValue!=null){
				String [] keyList = groupKeyValue.split(GROUP_KEY_SEPRATOR);
				if(keyList==null) return;
				for(int i=0;i<keyList.length;i++){
					this.flushCacheEntry(keyList[i]);
				}
				flushCacheEntry(groupID);
			}
		}
	}	
	public static void main(String [] args){
		try {
			MemcachedClient c=new MemcachedClient(
					AddrUtil.getAddresses("10.224.135.15:11211"));

				// Store a value (async) for one hour
				c.set("someKey", 3600, "test");
				// Retrieve a value (synchronously).
				String myObject= (String)c.get("someKey");
				System.out.println("==test="+myObject);
				
				c.set("someKey", 3600, "test2");
				 myObject= (String)c.get("someKey");
				System.out.println("==test="+myObject);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void addCacheEntry(String cacheKey, Object obj, int seconds)
			throws GenericException {
		try {
			memcachedClient.set(cacheKey,  seconds,  obj);
		} catch (Exception e) {
			logger.info("Cannot add cache for " + cacheKey);
		}
		
	}
	
}	
