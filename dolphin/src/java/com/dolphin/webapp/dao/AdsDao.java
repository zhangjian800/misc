package com.dolphin.webapp.dao;

import java.util.List;

import com.dolphin.common.exception.GenericException;
import com.dolphin.common.vo.DOLValueObject;
import com.dolphin.webapp.common.dao.GenericDao;
import com.dolphin.webapp.vo.Ads;
import com.dolphin.webapp.vo.AdsDetailContent;

public interface AdsDao<T extends DOLValueObject> extends GenericDao<T>{

	public int insertAds(Ads ads) throws GenericException;
	public void insertAdsDetail(List<AdsDetailContent> adsDetails) throws GenericException;
	
	public List<AdsDetailContent> findUnfinishedAdsdetail(String instanceID) throws GenericException;
	public void commitAdsdetailData(List<AdsDetailContent> commitData) throws GenericException;

}
