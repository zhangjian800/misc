package com.dolphin.webapp.common.config;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;

import com.dolphin.common.log.DolpiLogger;

public class DolphinConfigUitl {

	private static final DolpiLogger logger = new DolpiLogger(DolphinConfigUitl.class.getName());		
	private static final String CONFIG_FILE_PATH = "/conf/dolphin.properties";
	private static Properties _PROP;
    
    static {
    	initConfig();    	
    }

    //Refresh for configuratIon changed.
    public static void initConfig(){
    	try {
    		//Reset old data
    		_PROP = null;
    		//1.Load Properties file
    		_PROP = new Properties();
    		
    		URL res = DolphinConfigUitl.class.getClassLoader().getResource(CONFIG_FILE_PATH);
    		if (res != null) {
    		    URLConnection resConn = res.openConnection();
    		    resConn.setUseCaches(false);
    		    InputStream in = resConn.getInputStream();
    		    _PROP.load(in);
    		    PropertyConfigurator.configure(_PROP);
    		}
	    } catch (Exception e) {
	    	// error happens
	    	logger.error("Cannot load " + CONFIG_FILE_PATH+ "."+e.getMessage());
	    }
    }
    
    public static String getProperty(String propertyName) {
        try {
        	String value = _PROP.getProperty(propertyName);
            if (value == null) {
            	logger.error("Error key : " + propertyName + " not defined");
            }
            return value;
        } catch (Exception e) {
        	logger.error("getProperty ["+propertyName+"]Error : ", e);
        	return null;
        }
    }
    
    
    public static int getIntProperty(String propertyName) {
        String value = getProperty(propertyName);
        return parseStr2Int(propertyName, value);
    }    
    /**
     * 
     * @param str
     * @return
     */
    private static int parseStr2Int(String propertyName, String str){
    	if(str==null || "".equals(str)) return -1;
    	int lValue = -1;
		try {
			lValue = Integer.parseInt(str.trim());
		} catch (NumberFormatException e) {
            logger.error("Parse propertyName to Int Exception", e);
		}    
		return lValue;
    }      
}
