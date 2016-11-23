package com.dolphin.webapp.common.restcall;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;

import com.dolphin.common.log.DolpiLogger;

public class RestCallUtil {
	
	private static DolpiLogger logger = new DolpiLogger(RestCallUtil.class.getName());

	private static final int DEFAULT_SO_TIME_OUT = 60000;
	private static final int DEFAULT_CONNECTION_TIME_OUT = 60000;
	private static final int DEFAULT_CONNECTIONMGR_TIME_OUT = DEFAULT_CONNECTION_TIME_OUT;
	
	
	/** The multi threaded HTTP connection manager */
	private static MultiThreadedHttpConnectionManager connectionManager;
	static {
		connectionManager = new MultiThreadedHttpConnectionManager();
		HttpConnectionManagerParams params = new HttpConnectionManagerParams();
		params.setSoTimeout(DEFAULT_SO_TIME_OUT);
		params.setConnectionTimeout(DEFAULT_CONNECTION_TIME_OUT);
		connectionManager.setParams(params);
	}
	
	public static RestCallResponse restCall(String url, List passednvList) throws Exception {
		RestCallResponse result = null;
		HttpClient theClient = new HttpClient(connectionManager);
		PostMethod method = new PostMethod(url);
		try {
			result = new RestCallResponse();
			if(passednvList!=null && passednvList.size()>0){
		        NameValuePair[] nvpa = new NameValuePair[passednvList.size()];
		        Iterator nvListIter = passednvList.iterator();
		        int nvpaIdx = 0;
		        while (nvListIter.hasNext()) {
		            nvpa[nvpaIdx] = (NameValuePair) nvListIter.next();
		            nvpaIdx++;
		        }
		        method.setRequestBody(nvpa);
			}
			theClient.getParams().setConnectionManagerTimeout(DEFAULT_CONNECTIONMGR_TIME_OUT);
			int statusCode = theClient.executeMethod(method);
			result.setHttpStatus(statusCode);
			if (statusCode == HttpStatus.SC_OK) {
				result.setHttpResponseBody(new String(method.getResponseBody(), "utf-8"));
			}
		} catch (Exception e) {
			result.setFailureReason(e.getMessage());
			logger.error("Connect [" + url +"] failure due to >>>>>>" +e.getMessage());
			throw e;
		} finally {
			method.releaseConnection();
		}
		return result;
	}
	

    public static void main(String[] args) {
    	try {
    		
//    		getAdsData();
    		
//    		Calendar c = Calendar.getInstance();
//    		
//    		String URL = "http://aliyun1.tworing.com.cn:8099/ThirdpartyAPI/KafaGetMT";
//    		//1363536000
//    		//1363582080614
//    		String strMillis = c.getTimeInMillis() + "";
//    		String strSeconds = strMillis.substring(0, 10);
//    		System.out.println(strMillis);
//    		System.out.println(strSeconds);
////    		RESTResponse response =	RestCallUtil.restCall("http://loginp.webexconnect.com/cas/version.txt", null);
////    		System.out.println(response.getHttpResponseBody());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
