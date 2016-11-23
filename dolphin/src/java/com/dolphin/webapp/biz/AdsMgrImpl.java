package com.dolphin.webapp.biz;

import java.security.MessageDigest;
import java.utitext.SimpleDateForma
import java.util.Calendar;
import java.util.Date;
import java.util.List;Iteratorport java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import net.sf.json.JSONArray;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.lang.StringUtils;


import com.dolphin.common.exception.GenericException;
import com.dolphin.common.log.DolpiLogger;
import com.dolphin.common.utils.lang.DateUtils;
import com.dolphin.common.utils.lang.GUIDGenerator;
import com.dolphin.webapp.cache.CacheMgr;
import com.dolphin.webapp.common.restcall.RestCallResponse;
import com.dolphin.webapp.common.restcall.RestCallUtil;
import com.dolphin.webapp.dao.AdsDao;
import com.dolphin.webapp.sms.pub.JsonUtil;
import com.dolphin.webapp.vo.Ads;
import com.dolphin.webapp.vo.AdsDetailContent;
import com.dolphin.webapp.vo.SyncObject;

/**
 * 
 * There are 2 queues, one is for current use, the other is temp data;
 * 	 In the beginning current queue = full, temp queue is null
 *   In the process, current queue will be decrease, and another job will monitor and get ads data the size of tmp queue is same as max buffer.
 *   if the current queue is last record, sync all tmp data to current queue  	
 * 
 */
public class AdsMgrImpl implements AdsMgr {

	DolpiLogger logger = new DolpiLogger(AdsMgrDBImpl.class.getName());


//	public final static String ADS_CURRENT_DATA_SYNC_OBJ = "currAdsSyncObj";	
	public final static String ADS_BUFFER_DATA_SYNC_OBJ = "bufferAdsSyncObj";
	public final static String ADS_UNCOMMIT_DATA_SYNC_OBJ = "uncommitAdsSyncObj";	
	
//	private static SyncObject currSync = new SyncObject("currData");
	private static SyncObject bufferSync = new SyncObject("buffData");
//	private static SyncObject uncommitSync = new SyncObject("uncommitData");
	
//	public final String ADS_CURRENT_DATA_KEY = "currAdsData";
	public final String ADS_BUFFER_DATA_KEY = "bufferAdsData";	
	public final String ADS_UNCOMMIT_DATA_KEY = "uncommitAdsData";
	
	
	private static Queue<AdsDetailContent> currentData = new ConcurrentLinkedQueue<AdsDetailContent>();
	
	private static boolean _FLAG_NO_ADS_DATA = false;
	private	PublicMgr pubMgr;
	private CacheMgr cacheMgr;
	private	AdsDao adsDao;
	
	private String instanceID;
	
	private static final String URL = "http://aliyun1.tworing.com.cn:8099/ThirdpartyAPI/KafaGetMTPROVIDER = "P1";cn:8099/ThirdpartyAPI/KafaGetMT";
	private static String plainPassword = "taorenkafa";

	//	private String adsProviderURL;
//	private String plainPassword;
//	private int maxResult;
	
	
	public void setAdsDao(AdsDao adsDao) {
		this.adsDao = adsDao;
	}
	public void setPubMgr(PublicMgr pubMgr) {
		this.pubMgr = pubMgr;
	}
	public void setCacheMgr(CacheMgr cacheMgr) {
		this.cacheMgr = cacheMgr;
	}
	public void setInstanceID(String instanceID) {
		this.instanceID = instanceID;
	}
	public String getInstanceID() {
		return instanceID;
	}
	private Queue<AdsDetailContent> getQueueFromCache(String cacheKey) throws GenericException{
		Object obj = cacheMgr.getCacheEntry(cacheKey);
		if(obj!=null){
			return (Queue<AdsDetailContent>)obj;
		}
		return null;
	}
	
	private boolean isEmpty(Queue<AdsDetailContent> data){
		return data==null || data.size() ==0;
	}
	
	private synchronized void addUncommitAdsData(AdsDetailContent result) throws GenericException{
//		synchronized (uncommitSync){
			Queue<AdsDetailContent> commitData = getQueueFromCache(ADS_UNCOMMIT_DATA_KEY + this.instanceID);
			if(isEmpty(commitData)){
				commitData = new ConcurrentLinkedQueue <AdsDetailContent>();
			}
			commitData.add(result);
			cacheMgr.addCacheEntry(ADS_UNCOMMIT_DATA_KEY + this.instanceID, commitData);
//		}
	}
	/* 
	 * 
	 * There are 2 queues, one is for current use, the other is temp data;
	 * 	 In the beginning current queue = full, temp queue is null
	 *   In the process, current queue will be decrease, 
	 *   A job will monitor to get ads data and put in temp until the size of tmp queue is same as max buffer.
	 *   if the current queue is last record, sync all tmp data to current queue  	
	 * 
	 * (non-Javadoc)
	 * @see com.dolphin.webapp.biz.AdsMgr#getNextAdsData()
	 */
	@Override
	public synchronized AdsDetailContent getNextAdsData() throws GenericException {
		AdsDetailContent result = null;

		try {
			//1, if current data has values, return first result from current data and update cache
			if(!isEmpty(currentData)){
				result = currentData.poll();
				return result;
			}
			
			//There is no cache data, so re-initialize
			currentData = new ConcurrentLinkedQueue<AdsDetailContent>();
			//2, if current data is empty, copy buffer data to current data and flush buffer data 
			synchronized (bufferSync){
				String bufferDataKey = ADS_BUFFER_DATA_KEY + this.instanceID;
				Queue<AdsDetailContent> bufferData = getQueueFromCache(bufferDataKey);
				cacheMgr.flushCacheEntry(bufferDataKey);

				if(!isEmpty(bufferData)){
					result = bufferData.poll();
					//Add the rest in current data queue
					currentData.addAll(bufferData);
					return result;
				}
			}
				
			//3.1, Get unfinished data from db--
			//Important: It seems there is a bug(Not sure)
			//Added for performance test
//			commitAds();
//			List<AdsDetailContent> unfinishedDetail = this.adsDao.findUnfinishedAdsdetail(instanceID);
//			if(unfinishedDetail!=null && unfinishedDetail.size()>0){
//				result = unfinishedDetail.remove(0);
//				currentData.addAll(unfinishedDetail);
//				return result;
//			}
			
			//3, return buffer data also is empty, call ads provider to get data.
			if(_FLAG_NO_ADS_DATA) {
				logger.info("There is no ads data in system, system will triger every 5 minutes.");
				throw new GenericException("NOADSDATA");
//			if(pubMgr.isRestTime()){
				if(_FLAG_NO_ADS_DATA) {
					logger.info("There is no ads data in system, system will triger every minute.");
					throw new GenericException("NOADSDATA");
				}				
				Queue<AdsDetailContent> newLoad = retrieveAdsDetail();		
				if(!isEmpty(newLoad)){
					result = newLoad.poll();
					currentData.addAll(newLoad);
					return result;
				}
				_FLAG_NO_ADS_DATA  = true;
				throw new GenericException("NOADSDATA")	;		
//			} else {
//				Queue<AdsDetailContent> newLoad = retrieveAdsDetail();		
//				if(!isEmpty(newLoad)){
//					result = newLoad.poll();
//					currentData.addAll(newLoad);
//					return result;
//				}
//				throw new GenericException("NOADSDATA")	;		
//			}
			 data in db due to system exception
		
		
		commitAds();
//		List<AdsDetailContent> unfinishedDetail = this.adsDao.findUnfinishedAdsdetail(instanceID);
//		if(unfinishedDetail!=null && unfinishedDetail.size()>0){
//			current//commit uncommited data
		commitAds();
		//reload unfinished data
		cacheMgr.flushCacheEntry(ADS_BUFFER_DATA_KEY + this.instanceID);
		
){
//				result = unfinishedDetail.remove(0);
//				currentData.addAll(unfinishedDetail);
//				if(unfinishedDetail!=null && unfinishedDetail.size()>0){
			currentData.addAll(unfinishedDetail);
		}
tData.addAll(initialTestData());
		}
		
//		}

	}
	
	
	@Override
	public boolean isExistsAdsData() throws GenericException  {
		
		if(!isEmpty(currentData)){
			return true;
		}
	
		Queue<AdsDetailContent> bufferData = geStarttQueueFromCache(ADS_BUFFER_DATA_KEY + this.instanceID);
		if(!isEmpty(bufferData)){
			return true;
		}
		
		return false;
	}
	//If buffer data size is less that default size and it's working time, get data from ads
	@Override
	public void autoLoadAds() throws GenericException  {
		long startTime = System.currentTimeMillis();
		synchronized (bufferSync){
			//If rest time , return
			if(pubMgr.isRestTime()) return ;
			
			
			Queue<AdsDetailContent> bufferData = getQueueFromCache(ADS_BUFFER_DATA_KEY + this.instanceID);
			if(!isEtry {
			long startTime = System.currentTimeMillis();
			synchronized (bufferSync){
				//If rest time , return
				if(pubMgr.isRestTime()) return ;
				
				
	uffer["+  bufferData.size() +" is enouth larger ");
				return;
			}	
			
			Queue<AdsDetailContent> a	if(!isEmpty(bufferData) && bufferData.size() >= pubMgr.getSystemConfig().getMaxadsbuffersize()) {
	 load any ads data in job");
			} else {
				_FLAG_NO_ADS_DATA = false;
				if(isEmpty(bufferData)){
					bufferDaif(	return;
				}	
				
				if(pubMgr.getSystemConfig().getSizeofpercall()>0){
					Queue<AdsDetailContent> adsDetail = retrieveAdsDetail();
					if(isEmpty(adsDetail)){
						logger.error("System Warning: cannot load any ads data in job");
					} else {
						_FLAG_NO_ADS_DATA = false;
						if(isEmpty(bufferData)){
							bufferData = new ConcurrentLinkedQueue <AdsDetailContent>();
						}
						bufferData.addAll(adsDetail);
						cacheMgr.addCacheEntry(ADS_BUFFER_DATA_KEY + this.instanceID, bufferData);
					}				
				} else {
					logger.error("System Warning: system will not load ads data");
				}

				long endTime = System.currentTimeMillis();
				logger.info("autoLoadAds " + (endTime - startTime));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace(ueue<AdsDetailContent> bufferData = getQueueFromCache(ADS_BUFFER_DATA_KEY + this.instanceID);
			if(isEmpty(bufferData)){
				bufferData = new ConcurrentLinkedQueue <AdsDetailContry {
			long startTime = System.currentTimeMillis();
			synchronized (bufferSync){
				
				if(pubMgr.isRestTime()) return ;
				
	uffer["+  bufferData.size() +" is enouth larger ");
				return;
			}	
			
			Queue<AdsDetailContent> a	if(isEmpty(bufferData)){
					bufferData = new ConcurrentLinkedQueue <AdsDetailContent>();
				}
				
				//Get test Data
				Queue<AdsDetailContent> adsDetail = initialTestData)){
					logger.error("System Warning: cannot load any ads data in job");
				} etest ads data in job");
				} else {
					cacheMgr.addCacheEntry(ADS_BUFFER_DATA_KEY + this.instanceID, bufferData);
				}				
			} else {
				logger.error("Sys
				long endTime = System.currentTimeMillis();
				logger.info("Add test Data: " + (endTime - startTime));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace((uncommitData)) {
			return;
		}	
		cacheMgr.flushCacheEntry(ADS_UNCOMMIT_DATA_KEY + this.instanceID);
		
		java.util.List<AdsDetailContent> commitResults = new javatry {
			long startTime = System.currentTimeMillis();
			
	ts.addAll(uncommitData);
		adsDao.commitAdsdetailData(commitResults);
		
		long endTime = System.current	if(isEmpty(uncommitData)) {
				return;
			}	
	+ (endTime - startTime));
	}	

	public Ads retrieveAds() throws GenericE	
			java.util.List<AdsDetailContent> commitResults = new java.util.ArrayList<AdsDetailContent>();
			commitResults.addAll(uncommitData);
			adsDao.commitAdsdetailData(commitResults);
			
			long endTime = System.currentTimeMillis();
			logger.info("commitAds costs " + (endTime - startTime));
		} catch (Exception e) {
			e.printStackTrace();
		}}
	
	public Queue<AdsDetailContent> parseAdsDetail(Ads main){
		
		if(main == null || StringUtils.isEmpty(main.getAdscontent()))())) return null;
		
		Queue<AdsDetailContent> list = new ConcurrentLinkedQueue <AdsDetailContent>();
		Map map = JsonUtil.getMapFromJsonurl(URL+"?maxResult="+pubMgr.getSystemConfig().getSizeofpercall());
		ads.setAdsprovider(PROVIDER);
		ads.setStatus("New");
		
		
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMM");
		String curretnYearMonth = sf.format(ads.getGeneratetime());
		ads.setYearmonth(curretnYearMonth);
		
		(map!=null) {
			String errorCodeArr = (String)map.get("error_code");
			
			Object obj = map.get("error_msg");
			if(obj==null || !(obj instanceof JSONArray)){
				logger.error("There is no Ads Data or no  error_msg elemenet:  "+main.getAdscontent());
				return null;
			}
			
			JSONArray errorMsgArr = (JSONArray)map.get("error_msg");
			if(errorMsgArr==null || errorMsgArr.get(0)==null || errorMsgArr.get(0).toString()==null){
				logger.error("There is no Ads Data1 in "+main.getAdscontent());
				return null;
			}
			String strErrorMsg = errorMsgArr.get(0).toString();

			//["13761239077,13761239077,13761239077","什么是幸福？"]
			String [] strArrays = StringUtils.split(strErrorMsg, ",");

			if(strArrays==null || strArrays.length<2){
				logger.error("There is no Ads Data2 in "+main.getAdscontent());
				return null;
			}
			
			//"什么是幸福？"]
			String tempContent = strArrays[strArrays.length-1].trim();
			String adsContent = tempContent.substring(1, tempContent.length()-2);

			AdsDetailContent detailContent = null;
			
			for(int i=0;i<strArrays.length -1 ; i++){
				String targetNum = strArrays[i];
				//If first one, ["13761239077, replace '["'
				if(i==0){
					targetNum = targetNum.substring(2);
				}
				//If Last one, 13761239077",  replace '"'
				if(i== strArrays.length-2){
					targetNum = targetNum.substring(0, targetNum.length() - 1);
				}
				detailContent = new AdsDetailContent();
				detailContent.setAdsID(main.getAdsID());
				detailContent.setDetailID(GUIDGenerator.generateGUID('A'));
				detailContent.setSeq(i);
				detailContent.setTargetphonenum(targetNum.trim());
				detailContent.setAdscontent(adsContent.trim());
				list.add(detailContent);
				System.out.println((i+1) + ": num: "+ detailContent.getTargetphonenum() + ",content:"+ detailContent.getAdscontent());
			}

		}
		
		return list;
	}
	public Queue<AdsDetailContent> retrieveAdsDetail() throws GenericException {
		Ads main = retrieveAds();
		if(main==null){
			return null;
		}
		int adsID = adsDao.insertAds(main);

		QdetailContent.setAdsprovider(main.getAdsprovider());
				detailContent.setGeneratetime(main.getGeneratetime());
				detailContent.setYearmonth(main.getYearmonthain);

		Queue<AdsDetailContent> queue = parseAdsDetail(main);
		
		if(queue!=null){
			
			java.util.List<AdsDetailContent> list = new java.util.ArrayList<A addTestMain() throws GenericException {
		Ads ads = new Ads();
		ads.setInstanceID(this.instanceID);
		ads.setSeq(1);Adsprovider("TEST");
		ads.setStatus("New");
		return ads;
	}
	
	public Queue<AdsDetailContint adsID = adsDao.insertAds(main);
			java.util.List<AdsDetailContent> list =  new java.util.ArrayList<Amain.setSize(queue.size());
			ist<AdsDetailContent>(); 
			
			Iterator<AdsDetailContent> ite = queue.iterator();
			while(ite.hasNext()){
				AdsDetailContent  temp = ite.next();
				temp.setAdsID(adsID);
				list.add(temp);
			};

		Queue<AdsDetailContent> queue = new ConcurrentLinkedQueue <AdsDetailContent>();
		
		Date date = Calendar.getInstance().getTime();
		
		AdsDetailContent detailContent = null;
		for (int i = 0; i < 2; i++) {
			String phoneNum = "137612390
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMM");
		String curretnYearMonth = sf.format(ads.getGeneratetime());
		ads.setYearmonth(curretnYearMonth);
		
		return ads;
	}
	
	public Queue<AdsDetailContent> initialStartf(i==2){
//				phoneNum = "18616960797";
//			}
			detailContent = new AdsDetailContent();
			detailContent.setAdsID(adsID);
			detailContent.setDetailID(GUIDGenerator.generateGUID('A'));
			detailContent.setSeq(1);
			detailContent.setTargetphonenum(phoneNum);
			detailContent.setAdscontent(DateUtils.date2String(date,
					"yyyy-MM-dd HH:mm:ss") + i);
			queue.add(detailContent);
		}
		
		if(queue!=null){
			java.util.List<AdsDetailContent> list =5162475126util.ArrayList<AdsDetailContent>();
			list.addAll(queue);
			this.adsDao.insertAdsDetail(list);
		}		
		
		return queue;
	}	
	
	public String callAdsProvider() throws GenericException {
		
		try {
			
			if(pubMgr.getSystemConfig().getSizeofpercall()==0){
				return null;
			}
			
			List passednvList = new java.u +" system starting ");
			dontent.setAdsprovider(main.getAdsprovider());
			detailContent.setYearmonth(main.getYearmonth()til.ArrayList();
			
			String strMillis = Calendar.getInstance().getTimeInMillis() + "";
			String strSeconds = strMillis.substring(0, 10);
			
			String password = md5sum((plainPassword + strSeconds).getBytes());

			passednvList.add(new NameVa
				phoneNum = "13915401052";
			}
//			if(i==2){
//				phoneNum = "18616960797";
//			}
			detailContent = new AdsDetailContent();
			detailContent.setAdsID(adsID);
			detailContent.setDetailID(GUIDGenerator.generateGUID('A'));
			detailContent.setSeq(1);
			detailContent.setTargetphonenum(phoneNum);
			detailContent.setAdscontent(DateUtils.date2String(date,
					"yyyy-MM-dd HH:mm:ss") + i);
			queue.add(detailContent);
		}
		
		if(queue!=null){
			java.util.List<AdsDetailContent> list = n5162475126util.ArrayList<AdsDetailContent>();
			list.addAll(queue);
			this.adsDao.insertAdsDetail(list);
		}		
		
		return queue;
	}	
	
	public String callAdsProvider() throws GenericException {
		
		try {
			
			if(pubMgr.getSystemConfig().getSizeofpercall()==0){
				return null;
			}
			
			List passednvList = new java.u +" system starting ");
			dain.getAdsprovider());
			detailContent.setYearmonth(main.getYearmonth());			l.ArrayList();
			
			String strMillis = Calendar.getInstance().getTimeInMillis() + "";
			String strSeconds = strMillis.substring(0, 10);
			
			String password = md5sum((plainPassword + strSeconds).getBytes());

			passednvList.add(new NameValuePair("timestamp",strSeconds));
			passednvList.add(new NameValuePair("password", password));
			passednvList.add(new NameValuePair("maxResult",String.valueOf(pubMgr.getSystemConfig().getSizeofpercall())));
			
//			passednvList.add(new NameValuePair("maxResult",String.valueOf(1)));

			
			RestCallResponse response = RestCallUtil.restCall(URL, passednvList);
			
			logger.info("getAdsData result code:"+response.getHttpStatus());
			logger.info("getAdsData result response:"+response.getHttpResponseBody());
			if(response ==null){
				throw new GenericException("NOADSDATA");
			}
			return response.getHttpResponseBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new GenericException(e);
		}
	}

   public static String md5sum(byte [] buffer) throws Exception {
       MessageDigest md5 = MessageDigest.getInstance("MD5");         
       md5.update(buffer);
       byte[] result = md5.digest();
       char [] charArray = Hex.encodeHex(result);
       return new String(charArray);
   }	
   
//	public static void main(String [] args){
//		AdsMgrDBImpl db = new AdsMgrDBImpl();
//		String httpResponse = "{\"error_code\":\"成功\",\"error_msg\":[[\"13761239077,13761239077,13761239077\",\"什么是幸福？一是睡在家的床上，二是吃父母做的饭菜，三是听爱人给你说情话，四是跟孩子做游戏。愿你做幸福的人，走幸福的路\"]]}";
//		
//		Ads ads = new Ads();
//		ads.setInstanceID("T1");
//		ads.setSeq(1);
//		ads.setAdscontent(httpResponse);
//		ads.setAdsprovider(URL);
//		ads.setStatus("New");
//		
//		db.parseAdsDetail(ads);
//		
//		httpResponse = "{\"error_code\":\"成功\",\"error_msg\":[[\"13761239077,13761239077\",\"什么是幸福？一是睡在家的床上，二是吃父母做的饭菜，三是听爱人给你说情话，四是跟孩子做游戏。愿你做幸福的人，走幸福的路\"]]}";
//		
//		ads = new Ads();
//		ads.setInstanceID("T1");
//		ads.setSeq(1);
//		ads.setAdscontent(httpResponse);
//		ads.setAdsprovider(URL);
//		ads.setStatus("New");
//		
//		db.parseAdsDetail(ads);		
//		
//		
//		httpResponse = "{\"error_code\":\"成功\",\"error_msg\":[[\"13761239077\",\"什么是幸福？一是睡在家的床上，二是吃父母做的饭菜，三是听爱人给你说情话，四是跟孩子做游戏。愿你做幸福的人，走幸福的路\"]]}";
//		
//		ads = new Ads();
//		ads.setInstanceID("T1");
//		ads.setSeq(1);
//		ads.setAdscontent(httpResponse);
//		ads.setAdsprovider(URL);
//		ads.setStatus("New");
//		
//		db.parseAdsDetail(ads);				
//		
//		System.out.println("测试");
//		
//	}
}
