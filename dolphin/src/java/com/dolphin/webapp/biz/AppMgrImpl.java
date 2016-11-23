package com.dolphin.webapp.biz;

import java.io.InputStream;
import java.util.List;

import com.dolphin.common.exception.GenericException;
import com.dolphin.webapp.cache.CacheMgr;
import com.dolphin.webapp.dao.AppDao;
import com.dolphin.webapp.vo.App;
import com.dolphin.webapp.vo.AppFile;

public class AppMgrImpl implements AppMgr {
	private AppDao appDao;
	
	
	public void setAppDao(AppDao appDao) {
		this.appDao = appDao;
	}
	CacheMgr cacheMgr;
	public void setCacheMgr(CacheMgr cacheMgr) {
		this.cacheMgr = cacheMgr;
	}	

	public final static String CACHE_KEY_ALLAPPS = "ALLAPPS";
	public final static String CACHE_KEY_APPSTREAM = "APPSTREAM_";
	
	@Override
	public List<App> listAllApp() throws GenericException {
		Object obj = cacheMgr.getCacheEntry(CACHE_KEY_ALLAPPS);
		if(obj != null){
			return (List<App>)obj;
		}
		Object result =  appDao.listAll();
		if(result != null) {
			cacheMgr.addCacheEntry(CACHE_KEY_ALLAPPS, result);
			return (List<App>)result;
		}
		return null;
	}
	
	@Override
	public void reloadAllApps() throws GenericException {
		cacheMgr.flushCacheEntry(CACHE_KEY_ALLAPPS);
		listAllApp();
	}
	
	
	@Override
	public App getAppByAppID(int appID) throws GenericException {
		List<App> result = listAllApp();
		for(int i=0;i<result.size();i++){
			App rl = result.get(i);
			if(rl.getAppID() == appID){
				return rl;
			}
		}
		return null;
	}


	@Override
	public int createApp(App app) throws GenericException {
		int appID =  appDao.create(app);
		
		cacheMgr.flushCacheEntry(CACHE_KEY_ALLAPPS);
		
		//Reload
		reloadAllApps();
		
		return appID;
		
	}


	@Override
	public void updateApp(App app) throws GenericException {
		appDao.update(app);
		reloadAllApps();
	}
	
	
	
	
	@Override
	public App getAppByAppcode(String appcode) throws GenericException {
		Object obj = appDao.getAppByAppcode(appcode);
		if(obj!=null){
			return (App)obj;
		}
		return null;
	}

	@Override
	public boolean checkAppExisted(String appcode) throws GenericException {
		App app = getAppByAppcode(appcode);
		return app!=null && app.getAppID() >0;
	}
	

	@Override
	public void removeAppStream(int appID) throws GenericException{
		appDao.removeAppFile(appID);
		//Reload
		reloadAllApps();
		//Reload
		reloadAppStream(appID);
	}
	@Override
	public void uploadAppStream(int appID, String fileName, byte[] inputSteam)
			throws GenericException {
		AppFile file = new AppFile();
		
		appDao.removeAppFile(appID);

		file.setAppID(appID);
		file.setAppfilename(fileName);
		file.setAppstream(inputSteam);
			
		appDao.uploadAppFile(file);
		
		//Reload
		reloadAllApps();
		reloadAppStream(appID);
	}

	@Override
	public void reloadAppStream(int appID) throws GenericException {
		cacheMgr.flushCacheEntry(CACHE_KEY_APPSTREAM +appID);
		getAppStream(appID);
	}
	
	@Override
	public byte[] getAppStream(int appID) throws GenericException {
		Object obj = cacheMgr.getCacheEntry(CACHE_KEY_APPSTREAM +appID);
		if(obj != null){
			return (byte[])obj;
		}
		byte[] result =  appDao.getAppFile(appID).getAppstream();
		if(result != null) {
			cacheMgr.addCacheEntry(CACHE_KEY_APPSTREAM +appID, result);
		}		
		return result;
	}


}	
