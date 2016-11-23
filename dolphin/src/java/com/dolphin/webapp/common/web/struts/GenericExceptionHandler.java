package com.dolphin.webapp.common.web.struts;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.config.ExceptionConfig;

public class GenericExceptionHandler extends org.apache.struts.action.ExceptionHandler  {
    private static Log log = LogFactory.getLog(GenericExceptionHandler.class);

    private static final String _EXCEPTION_URI = "pub/Exception.jsp";
    
    private static final String FAILURE_VALUE = "FAILURE";

    public ActionForward execute(Exception ex, ExceptionConfig ae,
            ActionMapping mapping, ActionForm formInstance,
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
    	
    	request.setAttribute("ExctionStace", "Excetion happened...");
        return new ActionForward(null, _EXCEPTION_URI, false, true);
    }
}