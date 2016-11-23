package com.dolphin.common.log;

import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionEvent;

public class DolpiLogSessionListner implements HttpSessionListener{
    static {
        // Set the Logger to use the WbxLogger
        LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log", "com.dolphin.common.log.DolpiLogLogger");

    }

    public void sessionCreated(HttpSessionEvent event) {
        //Do nothing
    }

    public void sessionDestroyed(HttpSessionEvent event) {
        //Do nothing
    }
}
