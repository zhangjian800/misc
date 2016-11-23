package com.dolphin.common.exception;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.exception.NestableException;


/**
 * Common Exception for IdeaTouch.All system exception should extend it.\
 * 
 * If meet Exception, The system will response as below:
		<ServletResponse>
			<response>
				<result>FAILURE</result>
				<!-- exceptionKey: Module.Function.IDKEY -->
				<exceptionKey>USER.LOGIN.PASSWORD_EXPIRED</exceptionKey>
				<!-- Exception Message: which will be defined in the resource file		
			    <exceptionMessage>Login failed. Password has been expired.</exceptionMessage>
			</response>
			<exception>
				<!--Show the Excetpion Message and Loop the extraInfo-->			
				<messages>
					<message>Login failed. Password has been expired.</message>
					<message>Error = Password is incorrect</message>
				<messages>
				<!--There will be a config, define current if the mode is debug, if yes, system will response this element -->
				<stackTrace>
						at com.webex.UtilFilter.doFilter(UtilFilter.java:64)
						at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:235)
						at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:206)
						at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:233)
						at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:191)
						at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:128)
						at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:102)
						at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:109)
						at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:293)
						at org.apache.coyote.http11.Http11Processor.process(Http11Processor.java:849)
						at org.apache.coyote.http11.Http11Protocol$Http11ConnectionHandler.process(Http11Protocol.java:583)
						at org.apache.tomcat.util.net.JIoEndpoint$Worker.run(JIoEndpoint.java:454)
						at java.lang.Thread.run(Thread.java:619)					
				</stackTrace>
			</exception>
		</ServletResponse> 
 * @author ZhangJian
 */
public class GenericException extends NestableException implements java.io.Serializable {

   /**
     * map of additional info that will be logged with this message. 
     * The map is expected to be a list of name / value pairs that add further context to the error.
     */
    private Map<String, Object> extraInfo = null;
    
    /**
     * Hide constructors that users should not be using *
     */
    private GenericException() {
    };

    /**
     * Constructor that sets the error key
     * 
     * @param message
     *            Message to report for the exception.
     */
    public GenericException(String message) {
        super(message);
    }

    /**
     * Constructor that sets the error key and takes a map of extra info.
     * 
     * @param message
     *            of the execption
     * @param extraInfo
     *            additional info as a map
     */
    public GenericException(String message, Map extraInfo) {
        super(message);
        this.extraInfo = extraInfo;
    }

    /**
     * Constructor based on an existing exception
     * 
     * @param cause
     *            The root exception to chain.
     */
    public GenericException(Throwable cause) {
        super(cause);
    };

    /**
     * Constructor based on a message and an existing exception.
     * 
     * @param message
     *            Message to use for the exception
     * @param cause
     *            The root caues to chain
     */
    public GenericException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor based on a message and a nested exception. This version also
     * takes a map of extra info.
     * 
     * @param message
     *            of the execption
     * @param cause
     *            the Throwable being wrapped
     * @param extraInfo
     *            additional info as a map
     */
    public GenericException(String message, Throwable cause, Map extraInfo) {
        super(message, cause);
        this.extraInfo = extraInfo;
    }

    /**
     * @return Returns the extraInfo.
     */
    public Map<String, Object> getExtraInfo() {
        return extraInfo;
    }

    /**
     * @param extraInfo
     *            The extraInfo to set.
     */
    public void setExtraInfo(Map<String, Object> extraInfo) {
        this.extraInfo = extraInfo;
    }

    /**
     * Appends a key / value pair to the extraInfo map. Returns the exception so
     * that you can cascade append calls. Example: <p/> <code>
     * (new WAPIException("xyz")).appendExtraInfo("abc", "def").appendExtraInfo("qqq", "zzz");
     * </code>
     * 
     * @param key
     * @param value
     * @return
     */
    public GenericException appendExtraInfo(String key, Object value) {
        if (extraInfo == null) {
            extraInfo = new HashMap();
        }
        extraInfo.put(key, value);
        return this;
    }
    /**
     * Version of appendExtraInfo that takes a list of objects. The key is used
     * as a template and sequenced.
     * 
     * @param key
     * @param listOfValues
     * @return
     */
    public GenericException appendExtraInfo(String key, List listOfValues) {
        if (extraInfo == null) {
            extraInfo = new HashMap();
        }
        if ((listOfValues == null) || (listOfValues.isEmpty())) {
            extraInfo.put(key + "-1", "null");
        } else {
            Iterator listIter = listOfValues.iterator();
            int idx = 1;
            while (listIter.hasNext()) {
                extraInfo.put(key + "-" + Integer.toString(idx), listIter.next());
                idx++;
            }
        }
        return this;
    }
    
    /**
     * Formats the extra info into a string array.
     * 
     * @return string array of extra info.
     */
    public String[] printExtraInfo() {
        String[] returnThis = null;
        if (extraInfo == null) {
            returnThis = new String[1];
            returnThis[0] = "";
        } else {
            returnThis = new String[extraInfo.size()];
            Iterator eInfoIter = extraInfo.keySet().iterator();
            Object itemName, itemValue;
            int idx = 0;
            while (eInfoIter.hasNext()) {
                itemName = eInfoIter.next();
                itemValue = extraInfo.get(itemName);
                returnThis[idx] = ((itemName == null) ? "" : itemName
                    .toString())
                    + " = " + ((itemValue == null) ? "" : itemValue.toString());
                idx++;
            }
        }
        return returnThis;
    }    
}
