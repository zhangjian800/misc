package com.dolphin.common.resource;

import java.util.Locale;

import org.apache.struts.util.MessageResources;

//import org.apache.struts.util.MessageResources;

/**
 * This is a utility class that wrapped the MessageResources for support i18
 * @author ZhangJian
 */
public class MessageResouce {

    /**
     * Loading message files from resources dir.
     *  The files loaded are: 1) messages.properties 
     *  					  3) messages_zh_CN.properties
     */
    private static MessageResources messages = MessageResources.getMessageResources("resources.messages");
    
//  Later will separate the exception and messages    
//    /**
//     * Loading message files from resources dir.
//     *  The files loaded are: 1) exception.properties 
//     *  					  3) exception_zh_CN.properties
//     */
//    private static MessageResources exception = MessageResources.getMessageResources("resources.messages");
    
    
    /**
     * @param key
     * @return
     */
    public static String getMessage(String key){
    	return getMessage((Locale)null, key, null);
    }    
    /**
     * 
     * @param locale
     * @param key
     * @param args
     * @return
     */
    public static String getMessage(Locale locale, String key, Object[] args){
    	return messages.getMessage(locale, key, args);
    }
    
    /**
     * 
     * @param locale
     * @param key
     * @return
     */
    public static String getMessage(Locale locale, String key){
    	return getMessage(locale, key, null);
    }
}
