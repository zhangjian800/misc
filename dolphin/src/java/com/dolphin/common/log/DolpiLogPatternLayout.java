package com.dolphin.common.log;

import org.apache.log4j.PatternLayout;
import org.apache.log4j.spi.LoggingEvent;

import java.util.Enumeration;
import java.util.StringTokenizer;
import java.net.NetworkInterface;
import java.net.InetAddress;

/**
 * Created by IntelliJ IDEA.
 * User: VSingh
 * Date: May 18, 2006
 * Time: 2:08:19 PM
 * To change this template use File | Settings | File Templates.
 */

/**
 * This class extends the PatternLayout from Log4J and adds the IP address of the machine
 * to the format. In the ConversionPattern the string "^I" is replaced by the IP address
 * of the machine that generated the log record
 */
public class DolpiLogPatternLayout extends PatternLayout{
    private String ipaddress = null;
    private static final String IP_FORMAT = "^I";
    private static final String ETHERNET = "eth";
    /**
     * Formats the LoggingEvent based on the ConversionPattern defined in
     * the config files. Here is does the initial conversion using the base
     * class and then adds the IP address to the Conversion pattern
     *
     * @param event
     * @return
     */
    public String format(LoggingEvent event) {
        String result = super.format(event);
        int index = result.indexOf(IP_FORMAT);
        if(index != -1){
            StringBuffer sb = new StringBuffer(result.substring(0,index));
            sb.append(getIPAddress());
            sb.append(result.substring(index + 2));
            return sb.toString();
        } else {
            return result;
        }

    }

    private String getIPAddress(){
        if(ipaddress != null){
            return ipaddress;
        }
        try {
            String retip = null;
            Enumeration enumni = NetworkInterface.getNetworkInterfaces();
            while(enumni.hasMoreElements()){
                NetworkInterface ni = (NetworkInterface)enumni.nextElement();
                if (ni.getName().startsWith(ETHERNET)){
                    Enumeration iaenum = ni.getInetAddresses();
                    while(iaenum.hasMoreElements()){
                        InetAddress ia = (InetAddress) iaenum.nextElement();
                        byte[] ipAddr = ia.getAddress();
                        String ipAddrStr = "";
                        for (int i=0; i<ipAddr.length; i++) {
                            if (i > 0) {
                                ipAddrStr += ".";
                            }
                            ipAddrStr += ipAddr[i]&0xFF;
                        }
                        StringTokenizer st = new StringTokenizer(ipAddrStr,".");
                        if(st.countTokens() == 4){
                            retip = ipAddrStr;
                            break;
                        }
                    }
                }
                if(retip != null){
                    break;
                }
            }

            return retip;
        } catch (Exception e) {
            return "unknown";
        }
    }
}
