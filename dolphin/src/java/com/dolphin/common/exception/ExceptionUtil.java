package com.dolphin.common.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.dolphin.common.resource.MessageResouce;



import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.LinkedList;
import java.util.Locale;
import java.util.ResourceBundle;


/**
 * This class contains helper methods for Exception message processing.
 * @author ZhangJian 
 */
public class ExceptionUtil {

    private static final Log logger = LogFactory.getLog(ExceptionUtil.class);	
	
	//=======================Process Exception ID======================================================================
    /**
     * If the first message is a ItouchException, return the message as the
     * exception ID. It should be a string formatted as "xxx.yyy_zzz_qqq".
     * 
     * @param throwable
     * @return
     */
    public static String getExceptionID(Throwable throwable) {
        String exceptionKey;
        if (throwable instanceof GenericException) {
        	exceptionKey = ((GenericException) throwable).getMessage().trim();
            if (isReasonableExceptionID(exceptionKey)) {
                return exceptionKey;
            }
        }
        return ExceptionKeys.WEB_FRAMEWORK_UNKNOWN_ERROR;
    }	
    
    /**
     * Scans the potential exception ID and returns true if it consists of only
     * letters, numbers, period and underbar (_).
     * 
     * @param theID
     * @return
     */
    private static boolean isReasonableExceptionID(String theID) {
        if ((theID == null) || (theID.length() == 0)) {
            return false;
        }
        char testChar;
        for (int idx = 0; idx < theID.length(); idx++) {
            testChar = theID.charAt(idx);
            if ((testChar != '.') && (testChar != '_')
                && (!Character.isLetterOrDigit(testChar))) {
                return false;
            }
        }
        return true;
    }    
    
    
    /**
     * Get Exception Message
     * @param throwable
     * @return
     */
    public static String getExceptionMessage(String exceptionID) {
    	return translateExceptionKey(exceptionID,null,null);
    }	    
    
    /**
     * Get Exception Message
     * @param throwable
     * @return
     */
    public static String getExceptionMessage(String exceptionID,Locale locale) {
    	return translateExceptionKey(exceptionID,null,locale);
    }	    
    //=======================Process Exception Messages========================================================    
    /**
     * Get the list of messages from the current throwable If the exception is
     * an instance of WAPIException then, translate the message and add it to
     * the messages list add all the extra info as message to the messages list
     * else add the message of the exception to the messages list
     * 
     * @param throwable
     *            Throwable
     * @return the list of locale specific messages
     */
    public static List<String> getMessages(Throwable throwable) {
        return getMessages(throwable, new LinkedList<String>(), null);
    }

    /**
     *Get the list of messages from the execption Process all the throwables of
     * that exception If the throwable is an instance of WAPIException then,
     * translate the message and add it to the messages list add all the extra
     * info as message to the messages list else add the message of the
     * exception to the messages list
     * 
     * @param exWBE
     *            Top level WAPIException
     * @param locale
     *            Locale for message translation
     * @return the list of locale specific messages
     */
    public static List<String> getMessages(GenericException itExec, Locale locale) {
        int idx;
        List<String> messages = new LinkedList<String>();
        Throwable[] throwables = itExec.getThrowables();
        Throwable curThrowable;
        for (idx = 0; idx < throwables.length; idx++) {
            curThrowable = throwables[idx];
            getMessages(curThrowable, messages, locale);
        }
        return messages;
    }

    /**
     * Get the list of messages from the current throwable If the exception is
     * an instance of WAPIException then, translate the message and add it to
     * the messages list add all the extra info as message to the messages list
     * else add the message of the exception to the messages list
     * 
     * @param throwable
     *            Throwable
     * @param messages
     *            List to added message too
     * @param locale
     *            Locale for message translation
     * @return the list of locale specific messages
     */
    private static List<String> getMessages(Throwable throwable,
        List<String> messages, Locale locale) {
    	GenericException itExec;
        String curMessage;
        String[] extraInfo;
        int jdx;
        if (throwable instanceof GenericException) {
        	itExec = (GenericException) throwable;
//            curMessage = translateExceptionKey(itExec.getMessage(), null, locale);//todo
//            messages.add(curMessage);
            extraInfo = itExec.printExtraInfo();
            for (jdx = 0; jdx < extraInfo.length; jdx++) {
                if (extraInfo[jdx].length() > 0) {
                    messages.add(extraInfo[jdx]);
                }
            }
        } else {
            String msg = throwable.getLocalizedMessage();
            if (msg == null) {
                msg = throwable.toString();
            }
            messages.add(msg);
        }
        return messages;
    }    
    
    /**
     * Translate the expection key to the exception string
     * 
     * @param theKey
     *            Execption key
     * @return the locale specific error message.
     */
    public static String translateExceptionKey(String theKey) {
        return translateExceptionKey(theKey, null, null);
    }

    /**
     * Translate the expection key to the exception string
     * 
     * @param theKey
     *            to the exception message
     * @param args
     *            for the message.
     * @return Locale specific error message
     */
    public static String translateExceptionKey(String theKey, Object[] args,
        Locale locale) {
        // strip off any concatenated message text.
        String keyOnly = null;
        int iStripHere = theKey.indexOf(' ');
        if (iStripHere == (-1)) {
            keyOnly = theKey;
        } else {
            keyOnly = theKey.substring(0, iStripHere);
        }
//        if (locale == null && AppContext.getSessionState() != null) {
//            locale = AppContext.getSessionState().getLocale();
//        }
        String theReturn = MessageResouce.getMessage(locale, keyOnly, args);//todo
        if (theReturn == null) {
            theReturn = theKey + " - untranslated.";
        } else {
            try {
                theReturn = theKey + " - "
                    + new String(theReturn.getBytes("ISO8859-1"), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                logger.error("convert msg string met exception", e);
                theReturn = theKey + " - " + theReturn;
            }
        }
        return theReturn;
    }
    
    public static void main(String [] args){
    	
    	Locale locale=new Locale("zh","CN");
    	ResourceBundle messages = ResourceBundle.getBundle("resources.messages",locale);
    	String title = messages.getString("ERR.system.common.unknown_error");    	
    	System.out.println("title======"+title);
    	
    	String resouces = ExceptionUtil.translateExceptionKey("ERR.system.common.unknown_error",null ,locale);
    	System.out.println("title======"+title);    	
    }
}