package com.myprojects.ci.main;

public class GetSecreToken {

	
	public static final byte [] SUPPRESS_LAST_MOD = {48, 5, -128, 3, 10, 1, 3};
	
	public static void main(String [] args) {
		escapeDoulbeQuote();
	}
	
    public static String getObscuredTokenId(String tokenId) {
        if (tokenId == null || tokenId.isEmpty()) {
            return tokenId;
        }
        String obscuredTokenId = "***...***" + tokenId.substring(tokenId.length()/2);
        return obscuredTokenId;
    }
    
    public static void escapeDoulbeQuote() {
    	String message = "{\"reference_id\": [\"RTZTUyYzhkZDktMTU4OS00ZGU0LThjNzgtO001\", \"RTZTUyYzhkZDktMTU4OS00ZGU0LThjNzgtO002\"],\"token_id\": [\"ZTUyYzhkZDktMTU4OS00ZEUIREUWRUX001\", \"ZTUyYzhkZDktMTU4OS00ZEUIREUWRUX002\"]}";
    	
    	System.out.println(message);
    	
    	String message2 = message.replaceAll("\"","\\\\\"");
    	
    	System.out.println(message2);
    }
    
    
}
