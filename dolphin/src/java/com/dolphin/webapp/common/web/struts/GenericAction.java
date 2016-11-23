package com.dolphin.webapp.common.web.struts;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.dolphin.webapp.context.SessionState;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.dolphin.common.log.DolpiLogger;

public abstract class GenericAction extends Action {

	public static String _ACTION_FORM = "form";
	private static String _LOGIN_PATH = "/login";
	private static String _CLIENT_ACCESS_PATH = "/access";
	
	private static String _LOGIN_URL = "/index.jsp";
	
	private static String _UPDATE_PHONENO_PATH= "/updatephoneno";
	
	private static String _CONFRIM_CHARGE_PATH= "/confirmcharge";


	private static final String [] _NO_LOGIN_PATH_ARRAY= {_LOGIN_PATH,
														  _CLIENT_ACCESS_PATH,
														  _UPDATE_PHONENO_PATH,
														  _CONFRIM_CHARGE_PATH
														 };
	
	private static Set<String> _NO_LOGIN_PATCH_SET  = new HashSet<String>();
	static {
		_NO_LOGIN_PATCH_SET.addAll(Arrays.asList(_NO_LOGIN_PATH_ARRAY));
	}	
	
	private static final DolpiLogger logger = new DolpiLogger(GenericAction.class.getName());

	/**
	 * Override the execute method to invoke our own business method and
	 * integrates the permission check function in the action
	 * 
	 * @param mapping
	 *            the ActionMapping
	 * @param form
	 *            the ActionForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return the ActionForward
	 * @throws Exception
	 *             throw this exception when there is an error occurred in the
	 *             processing
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//1, Check if user has logon
		HttpSession session=request.getSession();
		Object object = session.getAttribute("SessionState");
		boolean isLogon = false;
		if(object!=null){
			SessionState ss = (SessionState)object;
			if(ss!= null && ss.getUid()>0) {
				isLogon = true;
			}
		}
		// 1.1 If user did not login, redirect to login page, but bypass login action
		if(!isLogon){
			boolean noLogin = _NO_LOGIN_PATCH_SET.contains(mapping.getPath());
			if(!noLogin){
				ActionForward forward = new ActionForward(null, _LOGIN_URL, false, true);
				return forward;
			}
		}
		
		//2, Check if User has the related Privilege  //todo
		
		//3, Process Implemented Action
		response.addHeader("pragma", "no-cache");
		response.addHeader("cache-control", "no-cache");
		response.addHeader("expires", "0");
		ActionForward forward;
		try {
			forward = process(mapping, form, request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			String exs = getExceptionStackTrace(e);
			logger.error("Exception:+ "+ exs);		
			return null;
		}
		request.setAttribute(_ACTION_FORM, form);
		
		
		return forward;

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

	/**
	 * The place to write our own business logic
	 * 
	 * @param mapping
	 *            the ActionMapping
	 * @param form
	 *            the ActionForm
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return the ActionForward
	 * @throws Exception
	 *             throw this exception when there is an errrr occurred during
	 *             the processing
	 */
	public abstract ActionForward process(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception;
}
