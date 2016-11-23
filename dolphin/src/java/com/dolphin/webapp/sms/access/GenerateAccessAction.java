package com.dolphin.webapp.sms.access;

import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.xml.security.utils.Base64;

import com.dolphin.common.log.DolpiLogger;
import com.dolphin.common.utils.lang.BitsUtil;
import com.dolphin.common.utils.lang.DateUtils;
import com.dolphin.webapp.biz.AccessMgr;
import com.dolphin.webapp.common.web.struts.GenericAction;
import com.dolphin.webapp.vo.Access;
import com.dolphin.webapp.vo.AccessResponse;

public class GenerateAccessAction extends GenericAction {

	DolpiLogger logger = new DolpiLogger(GenerateAccessAction.class.getName());

	private AccessMgr accessMgr;

	public void setAccessMgr(AccessMgr accessMgr) {
		this.accessMgr = accessMgr;
	}
	
	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		// Step1: iniailize the request
		Access access = new Access();
		
		String requestType = request.getParameter("type");
//		logger.info("requestType is null?"+(requestType==null));
		
		try {
			if(requestType ==null ){
				InputStream is = request.getInputStream();
				byte[] bytes = IOUtils.toByteArray(is);
//				logger.info("Request is + "+ new String(bytes, "UTF-8"));
//				logger.info("Request : + "+ new String(Base64.decode(bytes), "UTF-8"));
				String stremString = new String(Base64.decode(bytes), "UTF-8");
				convert2AccessByStreamString(access, stremString);
			} else {
				convert2AccessByRequest(access, req, new String(bytes, "UTF-8")uest);
			}
		} catch (Exception e1) {
			access.setResponseStatus("INVALID_INPUT");
			access.setResponseScriptContent(this.getExceptionStackTrace(e1));
			accessMgr.insertAccess(access);
			return null;
		}

		// Step2: Execute the business logic
		AccessResponse accessRespoString requestStr = access.getRequestStr();
		String ipAddress = null;
		String address = request.getHeader("X-Forwarded-For");  
		if (address != null && address.length() > 0  && !"unknown".equalsIgnoreCase(address)) {
		       ipAddress =  address;   
		}  else {
			address = request.getHeader("Proxy-Client-IP");  
			if (address != null && address.length() > 0 && !"unknown".equalsIgnoreCase(address)) { 
				ipAddress=  address;   
			} else {
				address = request.getHeader("WL-Proxy-Client-IP");  
				if (address != null && address.length() > 0  && !"unknown".equalsIgnoreCase(address)) {    
					ipAddress=  address;   
				}   
				else {
					ipAddress = request.getRemoteAddr();

				}
			}
		}
		
		access.setRequestStr("IP is:"+ ipAddress+","+requestStr);esponse = accessMgr.newAccess(access);
		
		//Add sim type, changed in 3/19/2013
		String simTypeStr = "sim="+access.getSimType();
		accessResponse.setScriptContent(simTypeStr+accessResponse.getScriptContent());
		
		// Step3: Response the resultd
		// interval=3scriptLength=1269script=<ScriptContent>&app=<xxxx>
		byte[] prefixByte = getPrefixArray2(accessResponse, access);
		byte [] appStream = accessResponse.getAppStream();
		byte []  result = null;
		
		if(appStream!=null){
			result = new byte [prefixByte.length + appStream.length];
			System.arraycopy(prefixByte, 0, result, 0, prefixByte.length);
			System.arraycopy(appStream, 0, result, prefixByte.length, appStream.length);
		} else{
			result = new byte [prefixByte.length];
			System.arraycopy(prefixByte, 0, result, 0, prefixByte.length);
		}
		response.setContentType("application/octet-stream");
		response.addHeader("Cache-Control", "no-cache");
		response.setContentLength(result.length);

		response.setDateHeader("Expires", 0);
		response.setDateHeader("Max-Age", 0);
		response.addHeader("Pragma", "no-cache");
		try {
			java.io.OutputStream outputStream = response.getOutputStream();
			outputStream.write(result);
			outputStream.flush();
		} catch (Exception e) {
			logger.error("Unable to write xml response", e);
		}
		/* do not forward since we already transmite.printStackTrace();
			logger.error("Unable to write xml response", e);
		} m access
	 * 
	 * @param stream
	 * 		+ SN=2335927624&IMEI=893726263000010&VER=7.1.1.k&FID=168168168
	 * 			&IMSI=9460016901638294&CID=DH000005-MD00001-MAUI.11B.W11.32.MP.V15.F3-F000001-SIM1
	 * 		&pid=M3&smsc=+8613010314500&lbs=460_01_61042_33041
	 * 	&MobType=MTK_6252_11B.240X320&key=2013/03/09 00:11&Retry=0
	 * 
	 */
	public void convert2AccessByStreamString(Access access, String stream) throws Exception {
		
		//Step1: remove "+ "
		String tempString = stream;
		int idx = 0;
//		int idx = stream.i, String originalStrndexOf("+");
//		if(idx>=0) {
//			tempString = stream.substring(idx+1).trim();
//		}
		String []  paramter = StringUtils.split(tempString, "&");
		HashMap<String,String> map = new HashMap<String,String>();
		for(int i=0; i<paramter.length;i++){
			idx = paramter[i].indexOf("=");
			String name = paramter[i].substring(0, idx);
			String value = paramter[i].substring(idx+1);
			map.put(name, value);
		}
		
		access.setImsi(map.get("IMSI"));
		
		try {
			String productType = getProductTypeFromCID(map.get("CID"));
			access.setProductType(productType);
		} catch (Exception e) {
			logger.error("Cannot Parse product type from CID ["
					+ map.get("CID")
							+ "] for IMSI " + map.get("IMSI"));
			
			throw e;
		}
		
		try {
			String productType = getProductTypeFromCID(map.get("CID"));
			access.setProductType(productType);
		} catch (Exception e) {
			logger.error("Cannot Parse product type from CID ["
					+ map.get("CID")
							+ "] for IMSI " + map.get("IMSI"));
			
			throw e;
		}
		
		int simType = 1; //sim1 or sim2
		if(map.get("CID").indexOf("SIM2") >0){
			simType = 2;
		}
		access.setSimType(simType);

		access.setVersion(map.get("VER"));
		access.setPid(map.get("pid"));
		access.setSmsc(map.get("smsc"));
		access.setLbs(map.get("lbs"));
		
		String platformType = map.get("MobType");
		if(platformType != null){
			idx = platformType.lastIndexOf(".");
			access.setPlatformType(platformType.substring(0, idx));

		}
		String strPublicDate = map.get("key");
		// Convert DateFormat  2013/03/09 00:11
		try {
			String tempDateStr = strPublicDate.substring(0, strPublicDate.indexOf(" "));
			access.setPublishDate(DateUtils.toDate(tempDateStr,
					"yyyy/MM/dd"));
		} catch (Exception e) {
			//Re-convert DateFormat  01-05-2013 16:01:47
			try {
				String tempDateStr = strPublicDate.substring(0, strPublicDate.indexOf(" "));
				access.setPublishDate(DateUtils.toDate(tempDateStr,"dd-MM-yyyy"));
			} catch (Exception e1) {
				logger.error("Cannot Parse IssueDate to " + strPublicDate
						+ " for IMSI " + map.get("IMSI"));
//				e.printStackTrace();
				String exs = getExceptionStackTrace(e);
				logger.error("Exception:+ "+ exs);	
				//Using system date
				access.setPublishDate(Calendar.getInstance().getTime());
			}
		}
		
		access.setRequestStr(stream);
	}
	
	public static void main(String [] args){
		GenerateAccessAction action = new GenerateAccessAction();
		Access access = new Access();
		String stremString = " + SN=2335927624&IMEI=8"Orional="+originalStr+", decode is"+93726263000010&VER=7.1.1.k&FID=168168168&IMSI=9460016901638294&CID=DH000005-MD00001-MAUI.11B.W11.32.MP.V15.F3-F000001-SIM1&pid=M3&smsc=+8613010314500&lbs=460_01_61042_33041&MobType=MTK_6252_11B.240X320&key=2013/03/09 00:11&Retry=0";
		try {
			action.convert2AccessByStreamString(access, stremString);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	
		}
	}
	
	private final static int _MAX_INTEVAL = 20160;
	
	private byte [] getPrefixArray2(AccessResponse acc, nullessResponse, Access access){
		// interval=3scriptLength=1269script=<ScriptContent>&app=<xxxx>
		// Prepare prefix response
		try {
			int inteval = accessResponse.getInteval();
			//Add protection
			if(inteval>=_MAX_INTEVAL){
				inteval = _MAX_INTEVAL;
			}
			String prefix = "interval=" + inteval
					  + "scriptLength=" + accessResponse.getScriptContent().getBytes().length
					  + "script=" + new String(accessResponse.getScriptContent().getBytes(),"UTF-8")
					  + "app=";
			logger.info("Response prefix [" + acces";
			if(accessResponse.getScriptContent().length()>0){
				[" + access.getImsi() + "]is ==="+prefix);			
			return prefix.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			String exs = getExceptionS}else{
				prefix = "interval=" + inteval
						  + "scriptLength=" + accessResponse.getScriptContent().getBytes().length
						  + "script=app=";
			}tionStackTrace(e);
			logger.error("Exception:+ "+ exs);					
		}
		return null;
	}

	private byte [] getPrefixArray(AccessResponse accessResponse){
		// interval=3scriptLength=1269script=<ScriptContent>&app=<xxxx>
		// Prepare prefix response
		int lenOfIntervalPara = "interval=".getBytes().length;
		int lenOfInterval = 2;

		int lenOfScriptLe/*tLengthPara = "scriptLength=".getBytes().length;
		int lenOfScriptContentLength = 2;

		int lenOfScriptPara = "script=".getBytes().length;
		int lenOfScripContent = accessResponse.getScriptContent().getBytes().length;

		int lenOfAppPara = "&app=".getBytes().length;
		
		byte[] prefixByte = new byte[lenOfIntervalPara + lenOfInterval
				+ lenOfScriptLengthPara + lenOfScriptContentLength
				+ lenOfScriptPara + lenOfScripContent + lenOfAppPara];

		// lenOfIntervalPara + lenOfIntervalValue
		int startIdx = 0;
		System.arraycopy("interval=".getBytes(), 0, prefixByte, startIdx,
				lenOfIntervalPara);
		startIdx = startIdx + lenOfIntervalPara;
		;
		BitsUtil.putShort(prefixByte, startIdx,
				(short) accessResponse.getInteval());
		startIdx = startIdx + lenOfInterval;
		;

		// lenOfScriptLengthPara + lenOfScriptContentLength
		System.arraycopy("scriptLength=".getBytes(), 0, prefixByte, startIdx,
				lenOfScriptLengthPara);		startIdx = startIdx + lenOfScriptLengthPara;
		;
		BitsUtil.putShort(prefixByte, startIdx, (short) lenOfScripContent);
		start startIdx + lenOfScriptContentLength;
		;

		// lenOfScriptPara + lenOfScripContent
		System.arraycopy("script=".getBytes(), 0, prefixByte, startIdx,
				lenOfScriptPara);
		startIdx = startIdx + lenOfScriptPaa;

		// Need make sure lenOfScripContent>0
		System.arraycopy(accessResponse.getScriptContent().getBytes(), 0, prefixByte,
			startIdx, lenOfScripContent);
		startIdx = startIdx + lenOfScripContent;
		;

		// lenOfAppPara
		System.arraycopy("&app=".getBytes(), 0, prefixByte, startIdx,
				lenOfAppPara);
		startIdx = startIdx + lenOfAppPara;
		
		return prefixByte;
	}
	
	private void convert2AccessByRequest(Access access, HttpServletRequest request) {
		AccessForm form = convertRequest2Form(request);
		acess.setImsi(form.getImsi());
		access.setProductType(form.getProductType());
		access.setVersion(form.getVersion());
		access.setPid(form.getPid());
		access.setSmsc(form.getSms*/Smsc());
		access.setLbs(form.getLbs());
		access.setPlatformType(form.getPlatformType());
		access.setPublishDate(form.getPublishDate());
		access.setSimType(form.getSimType());
	}

	/**
	 * 
	 * SN=434927806&IMEI=352273017386340&VER=7.5.0.k（&FID=168168168
	 * &IMSI=9460001101632673
	 * &CID=DH000005-MD00001-M6_BASE.PDA3.0.HVGA.76P.V9.16-F000001-SIM2
	 * &pid=M3&smsc=+8613800100500&lbs=460_00_2564_6263
	 * &MobType=SPRD_6530_12B.320X480&key=11-27-2012 16:44:07 (手机出厂日期)
	 * 
	 * @param request
	 * @return
	 */
	private AccessForm convertRequest2Form(HttpServletRequest request) {
		AccessForm form = new AccessForm();

		form.setImsi(request.getParameter("IMSI"));
		try {
			String productType = getProductTypeFromCID(request
					.getParameter("CID"));
			form.setProductType(productType);
		} catch (Exception e1) {
			logger.error("Cannot Parse product type from CID ["
					+ getProductTypeFromCID(request.getParameter("CID")
							+ "] for IMSI " + form.getImsi()));
			e1.printStackTrace();
		}
		int simType = 1; //sim1 or sim2
		if(request.getParameter("CID").indexOf("SIM2") >0){
			simType = 2;
		}
		form.setSimType(simType);
		
		form.setVersion(request.getParameter("VER"));
		form.setPid(request.getParameter("pid"));
		form.setSmsc(request.getParameter("smsc"));
		form.setLbs(request.getParameter("lbs"));
		
		String platformType = request.getParameter("MobType");
		if(platformType != null){
			int idx = platformType.lastIndexOf(".");
			form.setPlatformType(platformType.substring(0, idx));

		}

		String strPublicDate = request.getParameter("key");
		// Convert DateFormat
		try {
			form.setPublishDate(DateUtils.toDate(strPublicDate,
					"MM-dd-yyyy HH:mm:ss"));
		} catch (Exception e) {
			logger.error("Cannot Parse IssueDate to " + strPublicDate
					+ " for IMSI " + form.getImsi());
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return form;
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
	
	private String getProductTypeFromCID(String cid) {
		String productType = null;
		// Split M6_BASE.PDA3.0.HVGA.76P.V9.16 from CID
		String[] cidArray = StringUtils.split(cid, "-");
		if (cidArray != null && cidArray.length >= 3) {
			productType = cidArray[2];
		}
		return productType;
	}
}