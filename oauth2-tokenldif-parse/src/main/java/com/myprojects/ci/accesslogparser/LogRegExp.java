package com.myprojects.ci.accesslogparser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogRegExp implements LogExample {

    public static void main(String argv[]) {

//        String logEntryLine = "123.45.67.89 - - [27/Oct/2000:09:27:09 -0400] \"GET /java/javaResources.html HTTP/1.0\" 200 10450 \"-\" \"Mozilla/4.6 [en] (X11; U; OpenBSD 2.8 i386; Nav)\"";

//        String logEntryPattern = "^([\\d.]+) (\\S+) (\\S+) \\[([\\w:/]+\\s[+\\-]\\d{4})\\] \"(.+?)\" (\\d{3}) (\\d+) \"([^\"]+)\" \"([^\"]+)\"";

//        String logEntryLine  = "10.252.64.110 10.86.134.57  - - [16/Apr/2015:23:07:05 +0000] \"POST /idb/oauth2/v1/access_token HTTP/1.1\" 500 160 1 - NA_9513d865-ad3d-4dfb-86a1-418302a684d9 Mozilla/5.0 (Windows NT 6.3; WOW64; rv:32.0) Gecko/20100101 Firefox/32.0";

//        String logEntryPattern = "^([\\d.]+) ^([\\d.]+)  (\\S+) (\\S+) \\[([\\w:/]+\\s[+\\-]\\d{4})\\] \"(.+?)\" (\\d{3}) (\\d+) \"([^\"]+)\" \"([^\"]+)\" \"([^\"]+)\"";

        String logEntryPattern = "^([\\d.]+) (\\S+)  (\\S+) (\\S+) \\[([\\w:/]+\\s[+\\-]\\d{4})\\] \"(.+?)\" (\\d{3}) (\\d+) (\\d+) (\\S+) (\\S+) (\\S+) (\\S+) (\\S+) (\\S+) (\\S+) (\\S+) (\\S+) (\\S+)";

        System.out.println("Using RE Pattern:");
        System.out.println(logEntryPattern);

        System.out.println("Input line is:");
        System.out.println(logEntryLine);

        Pattern p = Pattern.compile(logEntryPattern);
        Matcher matcher = p.matcher(logEntryLine);
        System.out.println(matcher.matches());
//        if (!matcher.matches() || NUM_FIELDS != matcher.groupCount()) {
//            System.err.println("Bad log entry (or problem with RE?):");
//            System.err.println(logEntryLine);
//            return;
//        }
        System.out.println("IP Address: " + matcher.group(1));
        System.out.println("IP Address: " + matcher.group(2));
        System.out.println("Date&Time: " + matcher.group(4));
        System.out.println("Request: " + matcher.group(5));
        System.out.println("Response: " + matcher.group(6));
        System.out.println("Bytes Sent: " + matcher.group(7));
//        if (!matcher.group(8).equals("-"))
            System.out.println("Referer: " + matcher.group(8));
        System.out.println("Browser: " + matcher.group(9));
        System.out.println("Browser: " + matcher.group(10));
        System.out.println("Browser: " + matcher.group(11));

        System.out.println("user agent: " + matcher.group(12));
        System.out.println("Browser: " + matcher.group(13));
        System.out.println("Browser: " + matcher.group(14));
        System.out.println("Browser: " + matcher.group(15));
        System.out.println("Browser: " + matcher.group(16));
        System.out.println("Browser: " + matcher.group(17));
        System.out.println("Browser: " + matcher.group(18));
        System.out.println("Browser: " + matcher.group(19));


    }
}
