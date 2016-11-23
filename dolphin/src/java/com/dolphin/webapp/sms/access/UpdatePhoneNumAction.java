package com.dolphin.webapp.sms.access;

import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
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
import com.dolphin.webapp.biz.AccessMgr;
import com.dolphin.webapp.common.web.struts.GenericAction;

public class UpdatePhoneNumAction extends GenericAction {

	DolpiLogger logger = new DolpiLogger(UpdatePhoneNumAction.class.getName());

	private AccessMgr accessMgr;

	public void setAccessMgr(AccessMgr accessMgr) {
		this.accessMgr = accessMgr;
	}

	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String imsi = request.getParameter("IMSI");
		String phoneNum = null;
		
		String textString = "OKOKOK";
		try {
			if(imsi ==null || imsi.length()==0){
				InputStream is = request.getInputStream();
				byte[] bytes = IOUtils.toByteArray(is);
				String stremString = new String(Base64.decode(bytes), "UTF-8");
				String []  paramter = StringUtils.split(stremString, "&");
				HashMap<String,String> map = new HashMap<String,String>();
				for(int i=0; i<paramter.length;i++){
					int idx = paramter[i].indexOf("=");
					String name = paramter[i].substring(0, idx);
					String value = paramter[i].substring(idx+1);
					map.put(name, value);
				}		
				
				
				imsi = map.get("IMSI");
				phoneNum = map.get("o");
			} else {
				phoneNum  = request.getParameter("phoneno");
			}
			
			if(imsi!=null && phoneNum !=null){
				accessMgr.insertAndUpdatePhone(imsi, phoneNum);
			} else {
				logger.error("PhoneNum is " +phoneNum +" for IMSI "+ imsi );
			}
		} catch (Exception e) {
			e.printStackTrace();
			textString = getExceptionStackTrace(e);
		}
		
		response.setContentType("application/json;charset=UTF-8");
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
