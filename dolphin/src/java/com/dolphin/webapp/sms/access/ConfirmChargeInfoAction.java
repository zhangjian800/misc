package com.dolphin.webapp.sms.access;

import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URLDecoder;
import java.util.Htext.SimpleDateFormat;
import java.util.Calendaport java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.dolphin.common.log.DolpiLogger;
import com.dolphin.webapp.biz.AccessMgr;
import com.dolphin.webapp.common.web.struts.GenericAction;
import com.dolphin.webapp.vo.PhoneCharge;

publicAccessSumn.webapp.vo.PhoneCharge;

public class ConfirmChargeInfoAction extends GenericAction {

	DolpiLogger logger = new DolpiLogger(ConfirmChargeInfoAction.class.getName());

	private AccessMgr accessMgr;

	public void setAccessMgr(AccessMgr accessMgr) {
		this.accessMgr = accessMgr;
	}

	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String mobile = request.getParameter("mobile");
		String linkid = request.getParameter("linkid");
		String receivetime = request.getParameter("receivetime");
		String content = request.getParameter("content");

		
		String textString = "OKOKOK";
		if		String imsi = request.getParameter("imsi");

		boolean isSuccess = true;		String textString = "OKOKOK";
		if(StringUtils.isNotBlank(imsi) && StringUtils.isNotBlank(mobile)){
			try {
				PhoneCharge pc = new PhoneCharge();
				pc.setImsi(imsi);
				pc.setMobile(mobile);
				pc.setLinkid(linkid);
				pc.setRecif("999999".equals(mobile)){
					if(StringUtils.isNotBlank(linkid)){
						if(linkid.indexOf("_")>0){
							String tempString = linkid;
							String []  paramter = StringUtils.split(tempString, "_");
							if(paramter!=null && paramter.length>0){
								pc.setChannelID(paramter[0]);	
							}
							if(paramter!=null && paramter.length>1){
								pc.setRuleID(paramter[1]);
							}
							if(paramter!=null && paramter.length>2){
								pc.setAccessID(paramter[2]);
							}
							if(paramter!=null && paramter.length>3){
								String strCharge = paramter[3];
								try {
									pc.setFailmount(Float.parseFloat(strCharge));
								} catch (Exception e) {
									pc.setFailmount(0);
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}							
						}
					}
					isSuccess = content.indexOf("SUCCESS") >=0 ;
					pc.setResult(isSuccess?"SUCCESS":"FAIL");
				}
								setReceivetime(receivetime);
				if(StringUtils.isNotBlank(content)){
					pc.setContent(URLDecoder.decode(content));
				}accessMgr.insertPh
				String curretnYearMonth = new SimpleDateFormat("yyyyMM").format(Calendar.getInstance().getTime());
				String currdate = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
				pc.setYearmonth(curretnYearMonth);
				pc.setCurrdate(currdate);
				//TODO set IMSI, province,version ,city
				
				long newChargeID = accessMgr.insertPhoneCharge(pc);

				if(!isSuccess && "999999".equals(mobile)){
					//Suppose accessSum should not be null
					AccessSum accessSum = accessMgr.findAccessSumByIMSIAndYearMonth(imsi, curretnYearMonth);
					int times = accessSum.getTimes();
					if(times < 2 ){
						String chargeID = accessSum.getChargeids();
						if(chargeID==null){
							chargeID = String.valueOf(newChargeID);
						} else{
							chargeID = chargeID + "_"+ newChargeID;	
						}
						
						float failamount = accessSum.getCorrectfailmount() + pc.getFailmount();
						accessSum.setTimes(times + 1);
						accessSum.setCorrectfailmount(failamount);
						accessSum.setChargeids(chargeID);
						accessSum.setChargstatus("CorrectFail");
					} else {
						accessSum.setTimes(0);
						accessSum.setChargstatus("ResetCorrectFail");
					}
					accessMgr.updateAccessSum(accessSum);
					
				}ring = getExceptionStackTrace(e);
			}
		} else{
			logger.error("Not provided the mobile!");			
		} else {
			logger.error("Both mobile and ims are not provided!");
		}
		");
		response.setCharacterEncoding("UTF-8");
		PrintWriter pw = response.getWriter();
		pw.write(textString);
		pw.flush();
		
		return null;
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

}
