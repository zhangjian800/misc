package com.dolphin.webapp.biz;

import java.util.List;

import com.dolphin.common.exception.GenericException;
import com.dolphin.webapp.vo.App;

public interface AppMgr {
	
	public List<App> listAllApp() throws GenericException;
	public App getAppByAppID(int appID) throws GenericException;
	public App getAppByAppcode(String appcode) throws GenericException;
	public int createApp(App app) throws GenericException;
	public void updateApp(App app) throws GenericException;
	public void uploadAppStream(int appID, String fileName, byte[] inputSteam) throws GenericException;
	public void removeAppStream(int appID) throws GenericException;
	public byte[] getAppStream(int appID) throws GenericException;
	
	public boolean checkAppExisted(String appcode) throws GenericException;
	
	
	public void reloadAppStream(int appID) throws GenericException ;
	public void reloadAllApps() throws GenericException;
	
	
}
