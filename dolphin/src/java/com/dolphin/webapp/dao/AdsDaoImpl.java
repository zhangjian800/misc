package com.dolphin.webapp.dao;

import java.util.List;

import com.dolphin.common.exception.GenericException;
import com.dolphin.common.log.DolpiLogger;
import com.dolphin.webapp.common.dao.DefaultGenericDaoImpl;
import com.dolphin.webapp.vo.Ads;
import com.dolphin.webapp.vo.AdsDetailContent;

public class AdsDaoImpl  extends DefaultGenericDaoImpl<Ads> implements AdsDao<Ads> {
	
	DolpiLogger logger = new DolpiLogger(AdsDaoImpl.class.getName());

	public void insertAdsDetail(AdsDetailContent detail) throws GenericException {
		 logger.info("New:"+detail.getAdsID() +","+ detail.getAdsprovider() + ","+ detail.getDetailID() + ","
				   + detail.getSeq() + "," + detail.getStatus() + "," 
				   + detail.getTargetphonenum() + ", {" + detail.getAdscontent() + "} ");
	}
	
	public void commitAdsdetailData(String detailID) throws GenericException {
		 logger.info("COMPLETE:"+detailID);
	}
	
	private static final String _SQL_STATEMENT_CREATE_ADS = "insertAdsMain";
	private static final String _SQL_STATEMENT_CREATE_ADS_DETAIL = "insertAdsDetail";	

	private static final String _SQL_STATEMENT_COMMIT_ADS_DETAIL = "commitAdsDetail";	
	
	private static final String _SQL_STATEMENT_FIND_UNFINISHED = "findUnfinishAdsDetail";
	
	private static final String _SQL_STATEMENT_UPDATE_ADS = "updateAdsMain";
	private static final String _SQL_STATEMENT_FIND_UNFINISHED_ADS = "findAdsMainByInstanceID";
	

	
	public int insertAds(Ads ads) throws GenericException {
		 Object obj =  this.insert(_SQL_STATEMENT_CREATE_ADS, ads);
		 return (Integer)obj;
	}
	
	/*
	public void insertAdsDetail(AdsDetailContent detail) throws GenericException {
		 insert(_SQL_STATEMENT_CREATE_ADS_DETAIL, detail);
	}
	
	public void commitAdsdetailData(String detailID) throws GenericException {
		 insert(_SQL_STATEMENT_COMMIT_ADS_DETAIL, detailID);
	}
	
	*/
	
	@Override
	public void insertAdsDetail(List<AdsDetailContent> adsDetails)
			throws GenericException {
		for(int i=0;i<adsDetails.size();i++){
			insertAdsDetail(adsDetails.get(i));
		}
	}


	@Override
	public void commitAdsdetailData(List<AdsDetailContent> commitData)
			throws GenericException {
		for(int i=0;i<commitData.size();i++){
			commitAdsdetailData(commitData.get(i).getDetailID());
		}		
	}

	@Override
	public List<AdsDetailContent> findUnfinishedAdsdetail(String instanceID)
			throws GenericException {
		Object obj = this.queryForList(_SQL_STATEMENT_FIND_UNFINISHED, instanceID);
		if(obj!=null){
			return (List<AdsDetailContent>)obj;			
		}
		return null;
	}	
}
