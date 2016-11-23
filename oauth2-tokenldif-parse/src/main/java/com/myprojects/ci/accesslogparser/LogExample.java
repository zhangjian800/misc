package com.myprojects.ci.accesslogparser;

public interface LogExample {
    /** The number of fields that must be found. */
    public static final int NUM_FIELDS = 9;

    /** The sample log entry to be parsed. */
//    public static final String logEntryLine = "123.45.67.89 - - [27/Oct/2000:09:27:09 -0400] \"GET /java/javaResources.html HTTP/1.0\" 200 10450 \"-\" \"Mozilla/4.6 [en] (X11; U; OpenBSD 2.8 i386; Nav)\"";

//    public static final String logEntryLine  = "10.252.64.110 10.86.134.57  - - [16/Apr/2015:23:07:05 +0000] \"POST /idb/oauth2/v1/access_token HTTP/1.1\" 500 160 1 - NA_9513d865-ad3d-4dfb-86a1-418302a684d9";
    public static final String logEntryLine  = "10.252.64.110 10.86.134.57  - - [16/Apr/2015:23:07:05 +0000] \"POST /idb/oauth2/v1/access_token HTTP/1.1\" 500 160 1 - NA_9513d865-ad3d-4dfb-86a1-418302a684d9 Mozilla/5.0 (Windows NT 6.3; WOW64; rv:32.0) Gecko/20100101 Firefox/32.0";

}
