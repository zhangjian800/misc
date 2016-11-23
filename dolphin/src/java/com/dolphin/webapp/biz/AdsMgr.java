package com.dolphin.webapp.biz;

import com.dolphin.common.exception.GenericException;
import com.dolphin.webapp.vo.AdsDetailContent;

public interface AdsMgr {
	
	public AdsDetailContent getNextAdsData() throws GenericException;
	
	public void autoLoadAds() throws GenericException;
	
	public void addTestData() throws GenericException;
	
	public void firstLoadAds()  throws GenericException;
	
	public void commitAds() throws GenericException ;
	
	public boolean isExistsAdsData() throws GenericException;
	
	public String getInstanceID();
	
}
