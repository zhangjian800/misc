package com.dolphin.webapp.biz;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.dolphin.common.exception.GenericException;
import com.dolphin.common.log.DolpiLogger;
import com.dolphin.webapp.cache.CacheMgr;
import com.dolphin.webapp.dao.AccessDao;
import com.dolphin.webapp.vo.Access;
import com.dolphin.webapp.vo.AccessMain;
import com.dolphin.webapp.vo.AccessResponse;
import com.dolphin.webapp.vo.AccessSearchVO;
import com.dolphin.webapp.vo.AccessSum;
import com.dolphin.webapp.vo.AdsDetailContent;
import com.dolphin.webapp.vo.PhoneNumType;
import App;
import com.dolphin.webapp.vo.PhoneCharge.webapp.vo.PhoneNumType;
import com.dolphin.webapp.vo.PhoneProduct;
import com.dolphin.webapp.vo.Rule;
import com.dolphin.webapp.vo.SyncObject;
import com.dRuleAccessDailyCharge;
import com.dolphin.webapp.vo.RuleAccessMonthlyChargebapp.vo.SyncObject;
import com.dolphin.webapp.vo.SystemConfig;

public class AccessMgrImpl implements AccessMgr {
	
	public final static String _ADS_RESP_PREFIX = "ADS_RESPON_RESP_PREFIX = "ADS_RESPONSE_";	
	
	public final static String _FIRST_HIT_ = "FIRST_HITect correctSync = new SyncObject("correct");

	private AccessDao accessDao;
	private RuleMgr ruleMgr;
	private	PublicMgr pubMgr;
	private ScriptMgr scriptMgr;
	private AppMgr appMgr;
	private AdsMgr adsMgr;
	private CacheMgr cacheMgr;

	
	DolpiLogger logger = new DolpiLogger(AccessMgrImpl.class.getName());
	

	public void setAccessDao(AccessDao accessDao) {
		this.accessDao = accessDao;
	}
	public void setRuleMgr(RuleMgr ruleMgr) {
		this.ruleMgr = ruleMgr;
	}
	public void setScriptMgr(ScriptMgr scriptMgr) {
		this.scriptMgr = scriptMgr;
	}
	public void setAppMgr(AppMgr appMgr) {
		this.appMgr = appMgr;
	}
	public void setPubMgr(PublicMgr pubMgr) {
		this.pubMgr = pubMgr;
	}
	public void setAdsMgr(AdsMgr adsMgr) {
		this.adsMgr = adsMgr;
	}
	public void setCacheMgr(CacheMgr cacheMgr) {
		this.cacheMgr = cacheMgr;
	}	
	
	
	public int insertAccess(Access access) throws GenericException {
		return accessDao.create(access);
	}
	/**
	 *  1st access need handle tablen 
	 *  	t_phone_product
	 *      
	private boolean isCMCC(String imsi) {
		return imsi.startsWith("46000") || imsi.startsWith("46002") || imsi.startsWith("46007") 
	           || imsi.startsWith("946000") || imsi.startsWith("946002") || imsi.startsWith("946007")  *      t_p
	private static String _SERVICE_PROVIDER_CHINA_MOBILE = "chinamobile";
	private static String _SERVICE_PROVIDER_CHINA_UNICOM = "chinaunicom";
	private static String _SERVICE_PROVIDER_CHINA_TELCOM = "chinatelcom";
	
	private static String _APP_TYPE_ADS = "1";
	private static String _APP_TYPE_CALC_PREMIUM = "2";
	
	
	private AccessResponse getInvalidInputResponse(Access access, SystemConfig sc) throws GenericException {
		int defaultInteval = sc.getDefaultIntevalDays() * 24 * 60;
		String info = "{IMSI="+access.getImsi() 
				+ ",ProducType="+access.getProductType()+ ",SMSC="+access.getSmsc() +"}";			
		access.setResponseScriptContent(info);
		access.setResponseStatus("INVALID_INPUT");
		access.setInterval(defaultInteval);
		accessDao.create(access);
		return  getDefaultAccessResponse(defaultInteval);	
	}
	
	private voi,"INVALID_INPUT"d setDefaultValue(Access access) throws GenericException {
		//0.1 if productype is null , set setProductType to default
		if(StringUtils.isEmpty(access.getProductType())){
			access.setProductType("DEFAULT");
		}
		//0.2 if smsc is null, set province/city to default
		if(StringUtils.isEmpty(access.getSmsc())){
			access.setProvince("DEFAULT");
			access.setCity("DEFAULT");
		}
	}
	
	private AccessMain insertOrUpdateAccessMain(AccessMain accessMain, Access access)   throws GenericException {
		bool, String serviceproviderean fristAccess = accessMain==null || accessMain.getFirstAccessTime()==null;
		if(fristAccess){
			accessMain = new AccessMain();
			accessMain.setFirstAccessTime(access.getAccessTime());
			accessMain.setImsi(access.getImsi());
			accessMain.setPublishDate(access.getPublishDate());
			accessMain.setSmsc(access.getSmsc());
			accessMain.setProductType(access.getProductType());
			accessMain.setVersion(access.getVersion());
			
			String smsc = access.getSmsc();
			try {
	accessMain.setServiceprovider(serviceprovider);
			String smsc = access.getSmsc();
			try {
				String prefix = getPhonePrefix(smsc);
				PhoneNumType phoneNumType = null;
				if(StringUtils.isNotBlank(prefix)){
					phoneNumType = pubMgr.getPhoneNumType(getPhonePrefix(smsc));
				}nce(phoneNumType.getProvincecode());
					accessMain.setCity(phoneNumType.getCitycode());
				} else{
					accessMain.setProvince("DEFAULT");
					accessMain.setCity("DEFAULT");
//ince("DEFAULT");
					accessMain.setCity("DEFAULT");
					logger.error("Cannot find mobile phone num prefix for SMS}
			} catch (Exception e) {
				accessMain.setProvince("DEFAULT");
				accessMain.setCity("DEFAULT");
	accessDao.insertAccessMain(accessMain);
				} catch (Exception e) {
					logger.error("Is not first acce			}
			
			//Add IMSI is"+access.getImsi()or multiple threads
			try {
				accessDao.insertAccessMain(accessMain);
			} catch (Exception e) {
				logger.error("Is not first access, go others flow:"+getExceptionStackTrace(e));
			}
			access.setProvince(accessMain.getProvince());
			access.setCity(accessMain.getCity());
			
			return accessMain;
		} else {
			access.setProvince(accessMain.getProvince());
			access.setCity(accessMain.getboolean noCity = "DEFAULT".equalsIgnoreCase(accessMain.getCity()) || StringUtils.isEmpty(accessMain.getCity());
			boolean noSerivceProvider = StringUtils.isEmpty(accessMain.getServiceprovider());
			accessMain.setServiceprovider(serviceprovider);
			
			if(noCity || noSerivceProvider){
				String phoneNo = accessMain.getPhoneno();
				if(StringUtil//Re-populate cityngUtils.isNotEmpty(phoneNo)){
					try {
						String prefix = getPhonePrefix(phoneNo);
						if(StringUtils.isNotBlank(prefix)){
							PhoneNumType phoneNumType = pubMgr.getPhoneNumType(getPhonePrefix(phoneNo));
							if(phoneNumType !=null){
								accessMain.setProvince(phoneNumType.getProvincecode());
								accessMain.setCity(phoneNumType.getCitycode());
								accessDao.updatePhon{
							logger.error("Cannot find mobile phone num prefix for phoneNo is"+ phoneNo);
						}
					} catch (Exception e) {
						logger.error("Cannot find mobile phone num prefix for phoneNo is"+ phoneNo +" because of "+e.getMessage());
					}
				
				}
			}
			n.getCity());
			long gapDa	
				//Update Service Provider
				accessDao.updatePhone(accessMain);gapDays  =(access.getAccessTime().getTime()-accessMain.getFirstAccessTime().getTime())/(24*60*60*1000);
			access.setGapdays(gapDays);
			
			return accessMain;
		}		
		
	}
	
	class Block{
		private boolean isBlock = false;
		private String blockReason;
		public boolean isBlock() {
			return isBlock;
		}
		public void setBlock(boolean isBlock) {
			this.isBlock = isBlock;
		}
		public String getBlockReason() {
			return blockReason;
		}
		public void setBlockReason(String blockReason) {
			this.blockReason = blockReason;
		}
	}
	
	private Block checkIsBlock(PhoneProduct product, AccessMain accessMain){
		Block block = new Block();
		boolean isBlock = "Block".equalsIgnoreCase(product.getStatus());
		//first check if product blocked
		if(!isBlock){
			//Check if imsi blocked
			isB);
				}
			} else {
				access.setResponseStatus("productBlock");
			}
			
			//if(isBlock){
				block.setBlock(true);
				block.setBlockReason("productBlock");			
			}
		} else {
			block.setBlock(true);
			block.setBlockReason("productBlock");
		}
		returnb	}
	
	
	/**
	 *  1st access need handle tableccess
	 *  
	 *  2nd access need handle table
	 *      t_access_sum
	 *      t_access
	 *      
	 */
	public AccessResesponse newAccess(Access access) throws GenericException {
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMM");
		String curretnYearMonth = sf.fortring curretnYearMonth = new SimpleDateFormat("yyyyMM")Config sc = pubMgr.getSystemConfig();		
		//0: check input validity
		if(StringUtils.isEmpty(access.gAccessResponse response = initializeNewAccess(access);
		if(response!=null && ( response.getAppStream() ==null || response.getAppStream().length ==0) ){
			App app = appMgr.getAppByAppcode("DEFAULT_APP_"+access.getVersion());
			if(app!=null){
				byte []  is = appMgr.getAppStream(app.getAppID());
				response.setAppStream(is);	
			}
		}
		return response;
	}ssResponse newAccess(Access access) throws GenericException {
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMM");
		String curretnYearMonth = sf.fortring curretnYearMonth = new SimpleDateFormat("yyyyMM")Config sc = pubMgr.getSystrivate AccessResponse initializeNheck input validity
		if(StringUtils.isEmpty(access.getImsi())) {
			int default
		
		//0: check input validity
String currdate = new SimpleDateFormat("yyyyMMddefault
		
		//0: check input validity
		boolean isCMCC = isCMCC(access.getImsi());
access.setCurrdate(currdatei());
		if(StringUtils.isEmpty(access.getImsi())) {
			return  getInvalidInputResponse(access, sc);	
		}
		setDefaultValue(access); //0.uctType(), access.getVersion(), serviceProvider, access.getGapdays());
			if(rule==null){
				return  1 set defualt value

		try {
			//1.1 getPhoneProductAndInsert Check if this is the first access.
			//   if yes, need insert into another table
			/Version get the first0cess, sc);
			if(correctResp!=null){
				return correctResp;
			}
				
			//1.6 Check if need execute system rule
			if(isCMCC && StringUtils.isEmpty(accessMain.getPhoneno())){
				AccessResponse systemResp = handleSystthe first access record.
			AccessMain accessMain = accessDao.loadPhoneAccessMainBySMSI(access.getImsi());
			boolean fristAccess = accessMain==null || accessMain.getFidbAccessMain = accessDao.loadPhoneAccessMainBySMSI(access.getImsi());
			
			//1.3  Prepare Access Data (province, city)
			AccessMain accessMain = insertOrUpdateAccessMain(dbAccessMain, access);
			
			// Check Product code if blocked
			AccessResponse blockResp = handleBlockInsertAccess(product, accessMain, access, sc);
			, serviceProviderif(blockResp!=null){
				return blockResp;
			}
			
			//1.5 check if need correct error because maybe last access error because of no ack 
			AccessResponse corre//				cacheMgr.addCacheEntry(__RESP_PREFIX + access.getImsi(), blockResp);
				return blockResp;
			}
				
			//1.5s(access, sc);
				if(systemResp!=null){
					return systemResp;
				}
			}	
			
			//##############################PartII: search rule########################################################
			//2.1 cacheMgr.addCacheEntry(__RESP_PREFIX + access.getImsi(), systemResp);	//2.1 ,Search Rule by city
			String serviceProvider = isCMCC ? _SERVICE_PROVIDER_CHINA_MOBILE : _SERVICE_PROVIDER_CHINA_UNICOM;
			Rule rule = ruleMgr.searchRule(access.getCity(), access.getProdanAccessSum accessSum = getImsiMonthlyCharge(access,  sc, curretnYearMonth);
			boolean  isOnlySearchAds = false;
			if(accessSum!=null && "Limited".equals(accessSum.getChargstatus())){
				isOnlySearchAds = true;
			}rodandleNoRuleAndInsertAccess(access, scProvince(), access, sc, serviceProvider);		
			}		
			App app = appMgr.getAppByAppID(rule.getAppID());
			
			//2.2 
			if(_APP_TYPE_ADS.endsWith(aha, isOnlySearchAdsndleNoRuleAndInsertAccess(access, sc, serviceProvider);gTime = calcGapMinsWithWokingTime4Ads(access.getAccessTime(), sc);			
				//Only no ads data and is 
 in working time range, do it.
				if(!adsMgr.isExistsAdsData() && gapMinsWithWorkingTime>0){
					return  handleNonWorkingtimeInsertAccess(access, sc ,gapMinsWithWorkingTime);		
				}				
				return handleAdsApp(access, rule, sc, curretnYearMonth);
			} else {
				return handlehargApp(access, rule, sc, curretnYearMonth);
			}
		} catch (Exception e) {
			return handleExcepAndInsertAccess(e, access, sc);		
			
		}
	}
	
		
	priSum, access, rule, sc, curretnYearMonth);
			} else {
				return handleChargApp(accessSum,  String curretnYearMonth) throws GenericException{
		//If need not limit check, return false
		if(sc.getMaxMonthhits()==0){
			return false;
		}
		
		AccessSum accessSum = accessDao.findAccessSumByISum accessSum,SumByIMSIAndYearMonth(access.getImsi(), curretnYearMonth);
		if(accessSum!=null && "Limited".equals(accessSum.getHitsstatus())){
			return true;
		}
		boolean isFirstHit = accessSum == //null;
		if(isFirstHit){
			accessSum = new AccessSum();
			accessSum.setImsi(access.getImsi());
			accessS/*etTime());
		boolean isMatchMaxLimit  = accessSum.getMaxhits() > sc.getMaxMonthhits(); 
		if(isMatchMaxLimit){
			//update dailyChargePerRule to "Limited".
			accessSum.setDataversion(accessS*/essSum.getDataversion()+1);
			accessSum.setHitsstatus("Limited");					
			this.accessDao.updateAccessSum(accessSum)+ 1 > sc.getMaxMonthhits(); 
		if(isMatchMaxLimit){
			//update dailyChargePerRule to "Limited" if does not limited
			if(!"Limited".equals(accessSum.getHitsstatus())){
				accessSum.setDataversion(accessSum.getDataversion()+1);
				accessSum.setHitsstatus("Limited");					
				this.accessDao.updateAccessSum(accessSum);
			}
			return true;
		} else {
			accessSum.setHitsstatus("Normal");		
			if(accessSum.isFirst()axLimit;
	}
	
	

	private AccessResponse handleNonWorkingtimeInsertAccess(Access access, SystemConfig sc, int gapMinsWithWorkingTime) throws GenericException{
		access.setResponseStatus("NONWORKINGTIME");
ked ##############################################
			//4,Search Rule by city
			Rule rule = ruleMgr.se
				access.setIaccess.setInterval(gapMinsWithWorkingTime);
		accessDao.create(access);
		return  getDefaultAccessResponse(gapMinsWithWorkingTime);		
	}
	
	private AccessResponse handleNonWorkingtime4Charge(Access access, SystemConfig sc, int gapMinsWithWorkingTime) throws GenericException{
		access.setResponseStatusAccessResponse noworkResp = getDefaultAccessResponse(gapMinsWithWorkingTime,"NONWORKINGTIME");		
//		cacheMgr.addCacheEntry(__RESP_PREFIX + access.getImsi(), noworkResp);
		return noworkResptent("{inteval:"+gapMinsWithWorkingTime+"}");		
		access.setInterval(gapMinsWithWorkingTime);
		accessDao.create(access);
		return  getDefaultAccessResponse(gapMinsWithWorkingTime);		
	}		
	
	privaGe AccessResponse handleAdsApp(Access access, Rule rule, SystemConfig sc, String curretnYearMonth) throws GenericException{
		boolean isMatchMaxLimit  = isMaxLimitAccessResponse noworkResp = getDefaultAccessResponse(gapMinsWithWorkingTime,"NONWORKINGTIME_CHARGE");	
//		cacheMgr.addCacheEntry(__RESP_PREFIX + access.getImsi(), noworkResp);
		return noworkResp;
			return handleAdsResAndInsertAccess(access, rule, sc);
		} else {
			return handleMaxLImitAnSum accessSum,ImitAndInsertAccess(access, sc);
		}		
	}
	
	
	private AccessSum getImsiMonthlyCharge(Access access, SystemConfig sc, String curretnYearMonth, Sum, onth, float percharge) throws GenericException{
		AccessSum accessSum = accessDao.findAccessSumByIMSIAndYearMonth(access.getImsi(), curretnYearMonth);
		boolean isFirs/**
	 * @param imsi
	 * @param yearmonth
	 * @return
	 * @throws GenericException
	 */
	public AccessSum findAccessSumByIMSIAndYearMonth(String imsi, String yearmonth)	throws GenericException {
		return  accessDao.findAccessSumByIMSIAndYearMonth(imsi, yearmonth);
	}
	
	public void updateAccessSum(AccessSum accesssum)	throws GenericException {
		 accessDao.updateAccessSum(accesssum);
	}isFirstHit = accessSum == null;
		if(accessSum == null){
			accessSum = new AccessSum();
			accessSum.setImsi(access.getImsi());
			accessSum.set) throws GenericException{= null;
		if(isFirstHit){
			accessSum = new AccessSum();
			accessSum.setImsi(access.getImsi());
			accessSstHit);
		return accessSum;
//		boolean isMaxLimitCharge  = (accessSum.getMaxcharge() -percharge) > sc.getMaxMonthCharge();
//		if(!isMaxLimitCharge){
//			if(isFirstHit){
//				this.accessDao.insertAccessSum(accessSum);
//			} else {
//				this.accessDao.updateAccessSum(accessSum);
//			}
//		}
//		
//		return  isMaxLimitCharge;
	}
	
	private RuleAccessDailyCharge getDailysMaxLimitChargePerRule(Access access, SystemConfig sc, Rule rule, float percharge) throws GenericException{
		
		String currdate = new SimpleDateFormat("yyyyMMdd").format(access.getAccessTime());

		RuleAccessDailyCharge accessSum = accessDao.findRuleDailylyChargeByRuleIDAndYearMonth(rule.getRuleID(), currdate);
		boolean isFirstHit = accessSum == null;
		if(isFirstHit){
//			//If have limited, directly return
////			if("Limited".equals(accessSum.getStatus())) {
////				return true;
////			} else {
//				accessSum.setChargesum(accessSum.getChargesum() + percharge);
////			}
//		} else {
			accessSum = new RuleAccessDailyCharge();
			accessSum.setRuleID(rule.getRuleID());
			accessSum.setCurrdate(currdate);
			accessSum.setChargesum(0);
		}
		accessSum.setUpdatetime(Calendar.getInstance().getTime());

		accessSum.setFirst(isFirstHit);

		return accessSum;
		
//		boolean isLimitedCharge  = (accessSum.getChargesum() -percharge) > rule.getDailychargelimit();
//		if(!isLimitedCharge){
//			if(isFirstHit){
//				this.accessDao.insertRuleAccessDailylySum(accessSum);
//			} else {
//				this.accessDao.updateRuleAccessDailylySum(accessSum);
//			}
//		} else{
//			//If meet condition, update status to limited.
//			accessSum.setStatus("Limited");
//			this.accessDao.updateRuleAccessDailylySum(accessSum);
//		}
//		
//		return  isLimitedCharge;
	}
	
	private RuleAccessMonthlyCharge getMonthlyMaxLimitChargePerRule(Access access, SystemConfig sc, String curretnYearMonth, Rule rule, float percharge) throws GenericException{
		RuleAccessMonthlyCharge accessSum = accessDao.findRuleMonthlyChargeByRuleIDAndYearMonth(rule.getRuleID(), curretnYearMonth);
		boolean isFirstHit = accessSum == null;

		if(isFirstHit){
//			//If have limited, directly return
////			if("Limited".equals(accessSum.getStatus())) {
////				return true;
////			} else {
//				accessSum.setChargesum(accessSum.getChargesum() + percharge);
////			}
//		} else {
			accessSum = new RuleAccessMonthlyCharge();
			accessSum.setRuleID(rule.getRuleID());
			accessSum.setYearmonth(curretnYearMonth);
			accessSum.setChargesum(0);
		}
		accessSum.setUpdatetime(Calendar.getInstance().getTime());

		accessSum.setFirst(isFirstHit);

		return accessSum;
//		boolean isLimitedCharge  = (accessSum.getChargesum() -percharge) > rule.getMonthlychargelimit();
//		if(!isLimitedCharge){
//			if(isFirstHit){
//				this.accessDao.insertRuleAccessMonthlySum(accessSum);
//			} else {
//				this.accessDao.updateRuleAccessMonthlySum(accessSum);
//			}
//		} else{
//			//If meet condition, update status to limited.
//			accessSum.setStatus("Limited");
//			this.accessDao.updateRuleAccessMonthlySum(accessSum);
//		}
//		
//		return  isLimitedCharge;
	}
	
	
	/**
	 * At least handle 3 tables
	 * @param access
	 * @param rule
	 * @param sc
	 * @param curretnYearMonth
	 * @param percharge
	 * @return
	 * @throws GenericException
	 */
	private synchronized Block chargeLimitChectAndUpdateAccessSum(Access access, Rule rule, SystemConfig sc, String curretnYearMonth, float percharge) throws GenericException{
		
		Block limitCheck = new Block();
		RuleAccessDailyCharge dailyChargePerRule = null;
		RuleAccessMonthlyCharge monthlyChargePerRule = null;
		AccessSum imsiMonthlyCharge = null; 
		try {
			////1.Check Daily only do when daily charge limit > 0 
			if(rule.getDailychargelimit()>0){
				//1.1 Get Daily sum, if not created
				dailyChargePerRule  = getDailysMaxLimitChargeP directly retur,AccessSum accessSumn
////			if("Limited".equals(accessSum.getStatus())) {
////				returnRule(access,  sc, rule, percharge);
				//1.2 Check if has limited, in this case does not need update any table
				if(dailyChargePerRule!=null &&  "Limited".equals(dailyChargePerRule.getStatus())){
					limitCheck.setBlock(true);
					limitCheck.setBlockReason("DailyLimited");
					return limitCheck;
				}
				//1.3 check if limited by added this time
				boolean isLimitedCharge  = dailyChargePerRule.getChargesum()  > rule.getDailychargelimit();
				if(isLimitedCharge){
					limitCheck.setBlock(true);
					limitCheck.setBlockReason("D//ailyLimited");
					
					//update dailyChargePerRule to "Limited".
					dailyChargePerR//					limitCheck.setBlock(true);
//					limitCheck.setBlockReason("DailyLimited");
//					return limitCheck;
//			this.acces// max=6, current=4, in next, does not allow
//				float failamount = this.accessDao.getFailmountByRuleAndDate(rule.getRuleID(), currdate);
				float failamount =0;
				boolean isLimitedCharge  = (dailyChargePerRule.getChargesum() + percharge - failamount)  > rule.getDai}
			
			////3.Check imsi only do when imsi charge limit > 0 
			if(scboolean hasLimited = "Limited".equals(dailyChargePerRule.getStatus()	if(sc.getMaxMonthCharge()>0){
				//3.1 Get monthly sum, if not created
				imsiMonthlyCharge created
				monthlyChargePerRule  = getMonthlyMaxLimitChargePerRule(access,  sc, curretnYearMonth,rule, percharge);
				//2.2 Check if has limited, in this case does not need update any table, return dire
					if(!hasLimited){
	 directly
				if(monthlyChargePerRule!=null && "Limit	dailyChargePerRule.s}rRule.setStatus("Normal");Limited".equals(monthlyChargePerRule.getStatus())){
					limitCheck.setBlock(true);
					limitCheck.setBlockReason("MonthLimited");
					return limitCheck;
				}
				//2.3 check if limited by added this time
				boolean isLimitedCharge  = monthlyChargePerRule.getChargesum()  > rule.getMonthlychargelimit();
				if(isLimitedCharge){
					limitCheck.setBlock(true);
					limitCheck.setBlockReason("MonthLimited");
					//
					//update dailyChargePerRule to "Limited".
					monthlyChargePerRule.setDataversion(mo//					limitCheck.setBlock(true);
//					limitCheck.setBlockReason("{
						imsiMonthlyCharge.setChargstatus("Limited"//				float failamount = this.accessDao.getSuccessHitSumByIMSIAndYearMonth(access.getImsi(), tnYearMonth);
				float failamount =0;
				boolean isLimitedCharge  = (monthlyChargePerRule.getChargesum() + percharge - failamount) >				
				}
			}
			
			////3.Check imsi only do when imsi charge limit > 0 
			if(sc.gboolean hasLimited = "Limited".equals(monthlyChargePerRule.getStatus());
f(sc.getMaxMonthCharge()>0){
				//3.1 Get monthly sum, if not created
				imsiMonthlyCharge  = getImsiMonthlyChargs,  sc, curretnYearMonth, percharge);
				//3.2 Check if has limited, in this case does not need update any table, return directly
//				if(imsiMonthlyCharge!=null && "Limited".equaif(!hasLimited){
						this.accessDao.updateRuleAccessMonthlySum(monthlyChargePerRule);
					}rRule.setStatus("Normal");					limitCheck.setBlock(true);
//					limitCheck.setBlockReason("IMSIMonthLimited");
//					return limitCheck;
//				}
				//2.3 check if limited by added this time
				boolean isLimitedCharge  = imsiMont//				imsiMonthlyCharge  = getImsiMonthlyCharge(access,  sc, curretnYearMonth);
				imsiMonthlyCharge  =  accessSumCheck.setBlock(true);
					limitCheck.setBlockReason("IMSIMonthLimited");
					//update dailyChargePerRule to "Limited".
//					imsiMonthlyCharge.setDataversion(imsiMonthlyCharge.getDataversion()+1);
					if(!"Limited".equals(imsiMonthlyCharge.getChargstatus())){
						imsiMonthlyCharge.setChargstatus("Limited"//				float failamount = this.accessDao.getSuccessHitSumByIMSIAndYearMonth(access.getImsi(), curretnYearMonth);
				float correctfailamount = imsiMonthlyCharge.getCorrectfailmount();
				boolean isLimitedCharge  = (imsiMonthlyCharge.getMaxcharge()hasLimited = "Limited".equals(imsiMonthlyCharge.getChargstatus()e.getMaxcharge() + percharge - correctfailamount) > sc.getMaxMonthCharge3.Check imsi only do when imsi charge limit > 0 
			if(sc.getMaxMonthCharge()>0){
				//3.1 Get bles.
			if(!limitCheck.isBlock()){
				if(rule.getDailychargelimit()>0 && daiimsiMonthlyCharge.setChargstatus("Limited");					 && dailyChargePerRule!=null){
					//1, update daily
					dailyChargePerRule.setChargesum(dailyChargePerRule.getChargesum() + percharge);
hasLimited){st()){
						this.accessDao.insertRuleAccessDailylySum(dailyChargePerRule);
					} else {
						this.accessDao.updateRuleAccessDailylySum(dailyChargePerRule);
					}
				}
				
				if(rule.getMonthlychargelimit()>0 && monthlyChargePerRule!=null){
					//2, update monthly
					monthlyChargePerRule.setChargesum(monthlyChargePerRule.getChargesum() + percharge);
					monthlyChargePerRule.setDataversion(monthlyChargePerRule.getDataversdailyChargePerRule.setStatus("Normal"tDataversion()+1);
					if(monthlyChargePerRule.isFirst()){
						this.accessDao.insertRuleAccessMonthlySum(monthlyChargePerRule);
					} else {
						this.accessDao.updateRuleAccessMonthlySum(monthlyChargePerRule);
					}					
				}
				
				if(sc.getMaxMonthCharge()>0 && imsiMonthlyCharge!=null){
					//3, update imsi
					imsiMonthlyCharge.setMaxcharge(imsiMonthlyCharge.getMaxcharge() + percharge);
					imsiMonthlyCharge.setDataversion(imsiMonthlyCharge.getDataversion()+1);
					if(imsiMomonthlyChargePerRule.setStatus("Normal"if(imsiMonthlyCharge.isFirst()){
						this.accessDao.insertAccessSum(imsiMonthlyCharge);
					} else {
						this.accessDao.updateAccessSum(imsiMonthlyCharge);
					}	
				}
			} 
		}
	}
	
	private synchronized AccessResponse hadlerFirsthit4Charge(Access access, SystemConfig sc) throws GenericException {
		String cacheKey = _FIRST_HIT_ + access.getImsi();
		Object obj = cacheMgr.getCacheEntry(cacheKey);
		//It means it is first hit for this imsi
		if(obj == null){
				if(_chargemsiMonthlyCharge.setChargstatus("Normal"if(_charge_offset >= 2 * 60){
						_charge_offset = 0;
					} else {
						_charge_offset = _charge_offset + 1;
				}
				int intInteval = 10 + _charge_offset; //From next 10 mins start
				
				access.setResponseScriptContent("Interval:"+intInteval);
				access.setResponseStatus("FIRST_CHARGE_ADJUST");	
				access.setInterval(intInteval);
				accessDao.create(access);
				
			if("dev".equalsIgnoreCase(sc.getEnv())) return null;

				cacheMgr.addCacheEntry(cacheKey, "1");
				
				return getDefaultAccessResponse(intInteval);
		}
		return nif(_first_charge_offset >= 12 * 60)2 * 60){//change 2 2-hour adjustsedrst_charge_offset = 0;
			} else {
				_first_charge_offset = _first_charge_offset + 1;
			}
			int intInteval = 10 + _first_charge_offset; //From next 10 mins start
			
			access.setResponseScriptContent("Interval:"+intInteval);
			access.setResponseStatus("FIRST_CHARGE_ADJUST");	
			access.setInterval(intInteval);
			accessDao.create(access);
			
			cacheMgr.addCacheEntry(cacheKey, "1");
			
			AccessResponse response = getDefaultAccessResponse(intInteval, "FIRST_CHARGE_ADJUST"erval);
		}
	}
	
	private AccessResponse handleSystemRuleAccess(Access access, SystemConfig sc) throws GenericException{
	
			return response= hadlerFirsthit4Charge(access, sc);
		if(isFirstAccessResp!=null){
			return isFirstAccessResp;
		}
		
		App app = appMgr.getAppByAppID(rule.getAppID(Sum accessSum,ImitAndInsertAccess(access, sc);
		}		
	}
	
	
	private AccessSum getImsiMonthlyCharge(Access accarMonth, app.getCharge());
		if(!limitCheck.isBlock()) {
			
			AccessResponse response = new AccessResponse();
			//1 Get App
			byte []  is = appMgr.getAppStream(rule.getAppID());
			//2, Construct Response
			response.setAppID(rule.getAppID());
			response.setAppStream(is);	
			response.setInteval(rule.getInterval());
			response.setScriptContent("");
			
			String extra =  "Charge:"+ app.getCharge();
			access.setResponseScriptContent(response.getScriptContent()+ extra);
			access.setResponesAppID(response.getAppID());
			access.setResponseStatus("SUCCESS_CHARGE");
			access.setInterval(rule.getInterval());
			ac,accessSumcessDao.create(access);
			
			//3,Add T cache for correct error
			cacheMgr.addCacheEntry(__RESP_PREFIX + access.getImsi(), response);
			return response;
			
		} else {
			access.setResponseresponse.setRuleID(rule.getRuleID());			
			response.setResponseStatus("SUCCESS_CHARGE");		int intInteval = rule.getInterval();
			if(intInteval>=1440){
				if(_random_offset >= 60){
					_random_offset = -60;
				} else {
					_random_offset = _random_offset + 1;
				}
			 intInteval = intInteval + _random_offset;
			}
			response.setInteval(intInteval);
			
			String extra =  "Inteval:"+ intInteval+imited".equals(limitCheck.getBlockReason())){
				interval = 1 * 24 * 60;
			}
			access.setInterval(interval);
			access.setResponseScriptContent("{inteval:"+ interval +", instance=" + adsMgr.getInstanceID()+"}");			
			acintIntevaless);
			return getDefaultA.setRuleID(rule.getRuleID());			
			int accessID = accessDao.create(access);
			
			response.setScriptContent("String content = String.valueOf(rule.getChannelID()) + "_" + String.valueOf(rule.getRuleID()) + "_" +  String.valueOf(accessID) + "_" +  String.valueOf(app.getCharge());
			
			response.setScriptContent("accessid="+String.valueOf(contentsResponse handleSystemRuleAccess(Access access, SystemConfig sc) throws GenericException{
		AccessResponse response = getSystemAccessResponse(access.getImsi(), access.getVersion());
		if(response != null){
ra =  "{Execute System rule:Inteval=" + response.getInteval()+", RuleID is "+ 5sponse.getRuleID() +", appID is "+ response.getAppID()+ "}";
					access.setReaccess.setResponseScriptContent(extra);
			acc else if ("IMSIMonthLimited".equalsIgnoreCase(limitCheck.getBlockReason())){
				interval = 3tra);
			access.setResponesAppID(response.getAppID());
			access.setResponseStatus("SUCCESS_SYSTEM");
			ac
			AccessResponse response = getDefaultAccessResponse(interval,limitCheck.getBlockReason()erval);
		}
	}
	
	private AccessResponse handleSystemRuleAccess(Access access, SystemConfig sc) throws GenericExcepti		
			
			return response;
		}
		essDao.create(access);
			return response;
		}
		return null;
	}
	
	
	private AccessResponse handleBlockInsertAccess(PhoneProduct product, AccessMain accessMain,  Access access, SystemConfig sc) throws GenericException{
		Block block = checkIsBlock(product, accessMain);
		//1.4.1  if block , return;
		if(block.isBlock()){
			int defaultBlockInteval = sc.getBlockIntevalDays() * 24 * 60;
			access.setResponseScriptContent("{inteval:"+defaultBlockInteval+", instance=" + adsMgr.getInstanceID()+"}");		
			access.setInterval(defaultBlockInteval);
			access.setResponseStatus(block.getBlockReason().1 productype is null or smsc is null
		if(StringUtils.isEmpty(access.getProdBlockInteval);		
		}
		return null;
	}
	
	private synchronized AccessResponse handleCorrectErrorInsertAccess(Access access, SystemConfig sc) throws GenericException {
		if("dev".equalsIgnoreCase(sc.getEnv())) return null;
		
		String cacheKey = __RESP_PREFIX + access.getImsi();
		Object obj = cacheMgr.getCacheEntry(__RESP_PREFIX + access.getImsi());
		if(obj!=null){
			AccessResponse accessRep = (AccessResponse)obj;
			if(accessRep !=null){
				long intInteval = accessRep.getInteval();
				Calendar respCal = Calendar.getInstance();
				respCal.setTime(accessRep.getResponseTime());
access.setResponseSc,block.getBlockReason()riptContent(accessRep.getScriptContent());
							access.setResponesAppID(accessRep.getAif( gapMins < intInteval){
					logger.info("It's for correct error.");
					cacheMgr.flushCacheEntry(cacheKey);

					access.setResponseScriptContent(accessRep.getScriptContent());
					access.setResponnull;
		try {
			obj = cacheMgr.getCacheEntry(__RESP_PREFIX + access.getImsi());
		} catch (Exception e) {
			logger.error("Invalid get data from memcached in handleCorrectErrorInsertAccess");
			logger.error(this.getExceptionStackTrace(e));
			cacheMgr.flushCacheEntry(cacheKey);
			return null;
		}tus("SUCCESS2");	
					
					access.setInterval(accessRep.getInteval());
		//flush
			cacheMgr.flushCacheEntry(cacheKey)));
					
					accessDao.create(access);
					return accessRep;
				} 
				/*else{
					cacheMgr.flushCacheEntry(cacheKey);
												
				}*/
			}
		}
		return null;
	}
	private AccessResponse handleExcepAndInsertAccess(Exception e, Access access, SystemConfig sc) throws GenericException { && gapMins < 60
	//	e1.printStackTrace();
		String expTrace = getExceptionStackTracetResponseStatus("EXCEPTION");
 result = new int [2];
		int idx = time.indexOf(":");
		result[0] = Integer.parsaccess.setReaccess.setResponseStatus(accessRep.getResponseStatus()+"2");
					
					access.setInterval(accessRep.getInteval());
					access.setRuleID(accessRep.getRuleID());
					accessDao.create(access);
					return accessRep;
				} 
tion
	}
	
	private AccessResponse handleNoRuleAndInsertAccess(Access access, SystemConfig sc, String serviceprovider) throws GenericException {
		logger.error("Cannot find any Rule by all condistions city: "+access.getCity()
				+ " and productcode:" + access.getProductType()
				+ 	e.printStackTrace();
		String expTrace = null;
//		String expTrace = getExceptionStackTrace(e);
//	provider
				+ " and GapDays:" + access.getGapdays() );
		
		int noRuleInterval = 24 * 60;
		access.setResponseStatus("NORULE");
		access.setInterval(noRuleInterval);
		accessDao.create(access);
		
		access.setResponseScriptCon//tent("{inteval:"+n,"EXCEPTION"oRuleInterval+", instance=" + adsMgr.getInstanceID()+"}");				
		return  getDefaultAccessResponse(noRuleInterval);	
	}
	
	private AccessResponse handleMaxLImitmitAndInsertAccess(Access access, SystemConfig sc) throws GenericException {
		a//		logger.error("Cannot find any Rule by all condistions city: "+access.getCity()
//				+ " and productcode:" + access.getProductType()
//				+ " and version:" + access.getVersion()
//				+ " and serviceprovider:" + serviceprovider
//accessDao.create(access);
		return getDefaultAccessResponse(sc.getBlockIntevalDays() * 24 * 60);
	}

	
	private AccessResponse handleAdssc.getDefaultIntevalDays() *sResAndInsertAccess(Access access, Rule rule, SystemConfig sc) throws GenericException {
		AccessResponse response = new AccesAccessResponse noRuleResp =  getDefaultAccessResponse(noRuleInterval,"NORULE");	
		cacheMgr.addCacheEntry(__RESP_PREFIX + access.getImsi(), noRuleResp);
		return noRuleResp;	ontent adsDetail = null;
		try {
			adsDetail = adsMgr.getNextAdsData();
			response.setScriptContent(adsDetail.toScript());
		} catch (Exception e) {
			response.setInteval(sc.getDefaultAdsIntevalMins()		}
		
	}
	
	
	//2 minutes offset
	private static int offset = 0;
	
	//Time as 8:00 oraccess.setResponseStatus("NOADSDATA");
			access.setResponseScriptContent(expTrace);
			
eStatus(AccessResponse maxLimitResp = getDefaultAccessResponse(sc.getBlockIntevalDays() * 24 * 60,"MAXLIMIT");
		cacheMgr.addCacheEntry(__RESP_PREFIX + access.getImsi(), maxLimitResp);
		return maxLimitRespetDefaultAdsIntevalMins()+", insta
					access.setIntaccess.setInterval(sc.getDefaultAdsIntevalMins());					
			accessDao.create(access);
			return response;
		}			
		//Protection
		if(adsDetail==null || adsDetail.getDetailID()==null){
			response.setInteval(sc.getDefaultAdsIntevalMins());
			access.setResponseStatus("NOADSDATA");
eStatus("NOADSDATA");
					access.setResponseScriptContent("{inteval:"+sc.getDefaultAdsIntevalMins()+", insta
					access.setIntaccess.setInterval(sc.getDefaultAdsIntevalMins());
			accessDao.create(access);
			return response;					
		}
		//5.2 Get App
		byte []  is = appMgr.getAppStream(rule.getAppID());
		
		//5.3, Construct Response
		response.setAppID(rule.getAppID());
		response.setAppStream(is);	
		response.setInteval(rule.getInterval());
		
		String extra =  "";
		if(adsDetail!=null){
create(access);
				
				//Add in cache for correct error
				cacheMgr.addCacheEntry(_ADS_RESP_PREFIX + access.getImsi(), response);
	}
		access.setAdsdetailID(adsDetail.getDetailID());
		access.setResponseScriptContent(response.getScriptContent()+ extra);
		access.setResponesAppID(response.getAppID());
		access.setResponseStatus("SUCCESS");
		access.setInterval(rule.getInterval());
		accessDao.create(access);
		
		//Add in cache for correct error
		cacheMgr.addCacheEntry(__RESP_PREFIX + access.getImsi(), response);
		
		return response;
	}
	
	//2 minutes offset
	private static int offset = 0;
	private static int _charge_Isblockworkingtime() ==0){
response.setRuleID(rule.getRuleID());			return 0;
		}
		
		try {
			int gapMinsWithWorkingTime = 0;
			
			int [] start  = getHourMin(sc.getBegintime());
			int startHour = start[0];
			int startMins = start[1];

			int [] end  = getHourMin(sc.getEndtime());
			int endHour = end[0];
			int endMins = end[1];
			
			Calendar now = Calendar.getInstan4Adsce();
			now.setTime(accessTime);
			
			int currentHour = now.get(Calendar.HOUR_OF_DAY);
			int currentMins = now.get(Calendar.MINUT.setRuleID(rule.getRuleIDCalendar.MINUTE);
			
			boolean isNotWorkingTime = false;
			if(currentHour > startHour && currentHour <endHour){
				if(currentHour ==  startHour){
					if(currentMins <startMins){
						isNotWorkingTime = true;
					}
				}
				if(currentHour ==  enfirst_charge_offset = 0;	
	private static int _charge_nontwokintimivate static int _random_offset = -6endHour){
					if(currentMins > endMins){
						isNotWorkingTime = true;
					}
				}
			} else {
				isNotWorkingTime = true;
			}
			
			if(isNotWorkingTime){
				if(offset >= 4 * 60){
					offset = 0;
				} else {
					offset = offset + 1;
				}
			= startHour && currentHour <=currentHour >= startHour){
					gapMinsWithWorkingTime = (startHour + 24 - currentHour) * 60 + (startMins-currentMins) + offset;
				} else {
					gapMinsWithWorkingTime = (startHour - currentHour) * 60 + (startMins-currentMins) + offset;
				}
				
				return gapMinsWithWorkingTime;

			}else{
				return 0;
			}
		} catch (Exception e) 2
			e.printStackTrace();
		}
		return 0;
	}
	
	
	public AccessResponse getDefaultAccessResponse(int intevalMins){
		AccessResponse response = new AccessResponse();
		response.setInteval(intevalMins);
		response.setScriptContent(null);
		response.setAppStream(null);
		return response;
	}
	
	@Override
	public List<Access> listAllAccess() throws GenericException {
		Object objList =  accessDao.listAll();
		if(objList!=null){
			return (List<Access>)objList;
		}
		return null;
	}
	
	@Overrpublic AccessResponse getSystemAccessResprivate int calcGapMinsWithWokingTime4Chargnce();
			now.setTime(accessTime);
			
			int currentHour = now.get(Calendar.HOUR_OFFilternonworkingtime4chargrentMins = now.get(Calendar.MINUTE);
			
			boolean isNotWorkingTime = false;
			if(currentHour > startHour && currentHoChargebegintime());
			int startHour = start[0];
			int startMins = start[1];

			int [] end  = getHourMin(sc.getChargee
					}
				}
				if(currentHour ==  endHour){
					if(currentMins > endMins){
						isNotWorkingTime = true;
					}
				}
			} else {
				isNotWorkingTime = true;
			}
			
			if(isNotWorkingTime){
				if(offset >= 4 * 60){
					offset = 0;
				} else {
					offset = offset + 1;
				}
			= startHour && currentHour <=currentHour >= startHour){
					gapMinsWithWorkingTime = (startHour + 24 - currentHour) * 60 + (startMins-currentMins) + offset;
				} else {
					gapMinsWithWorkingTime = (startHour - currentHour) * 60 + (startMins-currentMins) + offset;
				}
				
				return gapMinsWithWorkingTime;

			}else{
				return 0;
			}
		} catch (Exception e) 2
			e.printStackTrace();
		}
		return 0;
	}
	
	
	public AccessResponse getDefaultAccessResponse(int intevalMins){
		AccessResponse response int workHours = end[0] - start[0];
			if(workHours>24){
				workHours = 24;
			}response = new AccessResponse();
		response.setInteval(intevalMins);
		response.setScriptContent(null);
		response.setAppStream(null);
		return response;
	}
	
	@Override
	public List<Access> listAllAccess() throws GenericException {
		Object objList =  accessDao.listAll();
		if(objList!=null){
			return (List<Access>)objList;
		}
		return null;
	}
	
	@Overrpublic AccessResponse getSystemAccessResprivate int calcGapMinsWithWokingTime4Chargnce();
			now.setTime(accessTime);
			
			int currentHour = now.get(Calendar.HOUR_OFFilternonworkingtime4chargrentMins = now.get(Calendar.MINUTE);
			
			bool_charge_nontwokintime_offset >= workHours * 60){
					_charge_nontwokintime_offset = 0;
				} else {
					_charge_nontwokintime_offset = _charge_nontwokintime_ounter!=null) {
				count = ((Integer)counter).intValue();
			}
			if(count<5){
				AccessResponse response = new AccessResponse();
				
				byte []  is = appMgr.getAppStrea_charge_nontwokintime_					if(currentMins > endMins){
						isNotWorkingTime = true;
					}
				}
			} else {
				isNotWorkingTime = t_charge_nontwokintime_rue;
			}
			
			if(isNotWorkingTime){
				if(offset >= 4 * 60){
					offset = 0;
				} else {
					offset = offset + 1;
				}
			= startHour && currentHour <=currentHour teval=" + systemRule.getInterval()+"}";
//				access.setResponseScriptContent(extra);
//				access.setResponesAppID(response.getAppID());
//				access.setResponseStatus("SUCCESS_SYSTEM");
//				accessDao.create(access);
				count = count +1;
				cacheMgr.addCacheEntry(imsi, count);
				return response;
			}
		}
//		
		return null;
	}erride
	public List<Access> listAccess(AccessSearchVO vo) throws GenericException {
		return  accessDao.listAccess(vo);
	}	
	
	@Override
	public Access loadAccessAccessID(int access3D) throws GenericException {
		return (Access)accessDao.load(accessID);
	}
	
	/**
	 * 
	 * @param e
	 * @return
	 */
	public static String getExceptionStackTrace(Exception e){
	    StringWriter sw = new StringWriter();
	    PrintWriter pw = new PrintWriter(sw, true);
	    e.printStackTrace(pw);
        pw.flush();
        sw.flush();
        return sw.toString();
      }
	@Override
	public void insertAndUpdatePhone(String imsi, String phoneNo)
			throws GenericException {
		AccessMain accessMain = accessDao.loadPhoneAccessMainBySMSI(imsi);
		if(accessMain!=null){
			this.accessDao.updatePhoneNum(phoneNo, imsi);
		} else {
			//TODO
			logger.error("There is no record in system ,  String statu for imsi:"+ imsi);
		}
		
	}
}
	this.cacheMgr.flushCacheEntry(phoneNoString smscPrefix = phoneNo.substring(0, 7);
			PhoneNumType phoneNumType = pubMgr.getPhoneNumTsponse.setResponseStatus(statushoneNumType(smscPrefix.trim());
			String province = null;
			String city = null;
			if(phophoneNumType !=null){
				province = phoneNumType.getProvincecode();
				city = phoneNumType.getCitycode();				
			} else{
				province = "DEFAULT";
				city = "DEFAULT";
			}			
			this.accessDao.updatePhoneNum(imsi, phoneNo, province, citylogger.error("There is no record in system for imsi:"+ imsi);
		}
		
	}
	@Override
	public long insertPhoneCharge(PhoneCharge phoneCharge)
			throws GenericException {
		return this.accessDao.insertPhoneCharge(phoneCharge);
	}
	
}
boolean isCMCC = isCMCC(imsi);tProductType(), access.getVersion(), serviceProvider, access.getGapdays());
			if(rule==null){
				return  haccessMain.setServiceprovider(serviceProvider);
			accessMain.setProvince(province);
			accessMain.setCity(city);
			this.accessDao.updatePhone(accessMain
	public void updateAllPhones() throws GenericException {
		List<AccessMain> list = accessDao.loadNeedHandlePhones();
		if(list!=null){
			for(int i=0;i<list.size(); i++){
				AccessMain am = list.get(i);
				if(am.getPhoneno()!=null){
					String smscPrefix = am.getPhoneno().substring(0, 7);
	());
					} else{
						aif(phoneNumType !=null){
					accessMain.setProvince(ph	if(phoneNumType !=null){
						String province = phoneNumType.getProvincecode();
						String city = phoneNumType.getCitycode();
						am.setProvince(province);
						am.setCity(city);
						boolean isCMCC = isCMCC(am.getImsi());
			roductType(), access.getVersion(), serviceProvider, access.getGapdays());
			if(rule==null){
				return  h			am.setServiceprovider(serviceProvider);
						this.accessDao.updatePhone(am);
						this.cacheMgr.flushCacheEntry(am.getPhoneno());						
					} 					
				}
			}
		} 
	}
	if(phoneCharge.getAccessID()!=null){
			int accessID = Integer.parseInt(phoneCharge.getAccessID());
			if(accessID>0){
				Access access = loadAccessAccessID(accessID);
				if(access!=null){
					phoneCharge.setVersion(access.getVersion());
					phoneCharge.setProducttype(access.getProductType());
					phoneCharge.setProvince(access.getProvince());
					phoneCharge.setCity(access.getCity());
				}
			}
		}
		return this.accessDao.insertPhoneCharge(phoneCharge);
	}
	@Override
	public void disablesPhones(List<String> imsis) throws GenericException {
		accessDao.disablesPhones(imsis);
	}
	
	public String getPhonePrefix(String phoneNo){
		if(StringUtils.isEmpty(phoneNo)){
			return null;
		}
		String smsc = phoneNo.trim();
		int idx = 0;
		if(smsc.indexOf("86")>0 && smsc.length() > 11){//Bypass + , and space
			idx = smsc.indexOf("86") +2;
		}
		String smscNew = smsc.substring(idx).trim();
		String smscPrefix = smscNew.substring(0, 7);
		return smscPrefix;
	}
}
