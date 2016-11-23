package com.dolphin.webapp.dao;

import com.dolphin.common.exception.GenericException;
import com.dolphin.common.vo.DOLValueObject;
import com.dolphin.webapp.common.dao.GenericDao;
import com.dolphin.webapp.vo.App;
import com.dolphin.webapp.vo.AppFile;

public interface AppDao<T extends DOLValueObject> extends GenericDao<T>{
	
	public void uploadAppFile(AppFile appfile) throws GenericException;
	public void removeAppFile(int appID) throws GenericException;
	public AppFile getAppFile(int appID) throws GenericException;
	
	public App getAppByAppcode(String appCode) throws GenericException;
}
