package com.myprojects.ci.accesslogparser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.myprojects.ci.ldifparse.OAuth2Token;
import com.myprojects.ci.ldifparse.Parser;

public class AccessLogParserImpl implements Parser {

    private AccessLogDao accessLogDao = null;

    public AccessLogDao getAccessLogDao() {
        return accessLogDao;
    }
    public void setAccessLogDao(AccessLogDao accessLogDao) {
        this.accessLogDao = accessLogDao;
    }


    public void parse(String fileName) throws Exception {
        List<String> lineList = new ArrayList<String>();
        String line = null;
        // FileReader reads text files in the default encoding.
        try {
            FileReader fileReader =  new FileReader(fileName);
            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            OAuth2Token newToken = null;
            while((line = bufferedReader.readLine()) != null) {
                if(lineList.size() > 50000) {
                    parseAndInsertLogs(lineList, fileName);
                    lineList = null;
                    System.gc();
                    lineList = new ArrayList<String>();
                }
                lineList.add(line);
            }
            //insert the rest
            parseAndInsertLogs(lineList, fileName);
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseAndInsertLogs(List<String> lineList, String accesslogFileName) throws Exception {
        List<AccessLog> logList = new ArrayList<AccessLog>();
        for (String line: lineList) {
            AccessLog log = parseAccessLogByLine(line);
            if(log != null) {
                log.setAccesslogFileName(accesslogFileName);
                logList.add(log);
            }
        }

        this.accessLogDao.insertAccessLog(logList);
    }

   public static String logEntryPattern = "^([\\d.]+) (\\S+)  (\\S+) (\\S+) \\[([\\w:/]+\\s[+\\-]\\d{4})\\] \"(.+?)\" (\\d{3}) (\\d+) (\\d+) (\\S+) (\\S+) (\\S+) (\\S+) (\\S+) (\\S+) (\\S+) (\\S+) (\\S+) (\\S+)";

   public static AccessLog parseAccessLogByLine(String line) {
       if(line == null || line.isEmpty()) {
           return null;
       }
       String [] lineArr = line.split(" ");
       AccessLog log = new AccessLog();

       log.setSourceIP(lineArr[0]);
       log.setSourceIP2(lineArr[1]);

       log.setAccesstime(lineArr[6] + " " + lineArr[7]);
       log.setEndpoint(lineArr[8] + " " + lineArr[9] + " " + lineArr[10]);

       log.setStatuscode(Integer.parseInt(lineArr[11]));
       try {
           log.setResponsesize(Integer.parseInt(lineArr[12]));
       } catch (NumberFormatException e) {
           // TODO Auto-generated catch block
//           e.printStackTrace();
       }
       try {
           log.setResopnsetime(Integer.parseInt(lineArr[13]));
       } catch (NumberFormatException e) {
           // TODO Auto-generated catch block
//           e.printStackTrace();
       }
       log.setReqtrackingID(lineArr[14]);
       log.setRsptrackingID(lineArr[15]);

       int idx = line.indexOf(lineArr[15]);

       String useragnet = line.substring(idx + lineArr[15].length());

       log.setUseragent(useragnet);
       return log;

   }

    //10.252.64.110 10.86.134.57  - - [16/Apr/2015:23:07:05 +0000] "POST /idb/oauth2/v1/access_token HTTP/1.1"
    //  500 160 1 - NA_9513d865-ad3d-4dfb-86a1-418302a684d9 Mozilla/5.0 (Windows NT 6.3; WOW64; rv:32.0) Gecko/20100101 Firefox/32.0
    private AccessLog parseAccessLogByLine2(String line) {
        if(line == null || line.isEmpty()) {
            return null;
        }

        Pattern p = Pattern.compile(logEntryPattern);
        Matcher matcher = p.matcher(line);
        boolean isMatch = matcher.matches();

        if(isMatch) {
            AccessLog log = new AccessLog();
            log.setSourceIP(matcher.group(1));
            log.setSourceIP2(matcher.group(2));

            log.setAccesstime(matcher.group(5));
            log.setEndpoint(matcher.group(6));

            log.setStatuscode(Integer.parseInt(matcher.group(7)));
            try {
                log.setResponsesize(Integer.parseInt(matcher.group(7)));
            } catch (NumberFormatException e) {
                // TODO Auto-generated catch block
//                e.printStackTrace();
            }
            try {
                log.setResopnsetime(Integer.parseInt(matcher.group(9)));
            } catch (NumberFormatException e) {
                // TODO Auto-generated catch block
//                e.printStackTrace();
            }
            log.setReqtrackingID(matcher.group(10));
            log.setRsptrackingID(matcher.group(11));


            String useragent = matcher.group(12) + " "
                        + matcher.group(13) + " "
                        + matcher.group(14) + " "
                        + matcher.group(15) + " "
                        + matcher.group(16) + " "
                        + matcher.group(17) + " "
                        + matcher.group(18) + " "
                        + matcher.group(19) + " ";

            log.setUseragent(useragent);
            return log;

        } else {
            System.out.println("Not matched!!!" + line);
            return null;
        }

    }

//    String logEntryLine = "123.45.67.89 - - [27/Oct/2000:09:27:09 -0400] \"GET /java/javaResources.html HTTP/1.0\" 200 10450 \"-\" \"Mozilla/4.6 [en] (X11; U; OpenBSD 2.8 i386; Nav)\"";


    public static void main(String [] args) {
        String line = "10.252.64.110 10.86.134.57  - - [16/Apr/2015:23:07:05 +0000] \"POST /idb/oauth2/v1/access_token HTTP/1.1\" 500 160 1 - NA_9513d865-ad3d-4dfb-86a1-418302a684d9 Mozilla/5.0 (Windows NT 6.3; WOW64; rv:32.0) Gecko/20100101 Firefox/32.0";
               line = "10.252.64.110 10.242.13.61 http-nio-10886-exec-33 - - 20-Feb-2016:00:00:00.093UTC 20-Feb-2016:00:00:00.061UTC 20-Feb-2016:00:00:00.093UTC \"POST /idb/token/v1/actions/GetBearerToken/invoke HTTP/1.1\" 401 214 32 TrackingID:- TrackingID:NA_e782e10f-3fbc-466a-9581-b4a3c31e3b26 amlbcookie:- Apache-HttpClient/4.3.6 (java 1.5)";
//        String [] lineArr = line.split(" ");
//        System.out.println("total length"+lineArr.length);
//        for(int i=0; i< lineArr.length; i++) {
//            System.out.println(lineArr[i]);
//        }
        AccessLog log = parseAccessLogByLine(line);
        System.out.println(log.getSourceIP());
        System.out.println(log.getSourceIP2());
        System.out.println(log.getStatuscode());
        System.out.println(log.getEndpoint());
        System.out.println(log.getResponsesize());
        System.out.println(log.getResopnsetime());
        System.out.println(log.getRsptrackingID());
        System.out.println(log.getUseragent());


    }

}
