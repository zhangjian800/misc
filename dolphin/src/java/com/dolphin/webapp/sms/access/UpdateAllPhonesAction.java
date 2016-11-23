package com.dolphin.webapp.sms.access;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.dolphin.common.log.DolpiLogger;
import com.dolphin.webapp.sms.SMSGenericAction;

public class UpdateAllPhonesAction extends SMSGenericAction {

	DolpiLogger logger = new DolpiLogger(UpdateAllPhonesAction.class.getName());


	public ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String textString = "OKOKOK";
		try {
			accessMgr.updateAllPhones();
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
