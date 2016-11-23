package com.dolphin.webapp.context;

public class SessionState {
	
    private String email = null; // this is a unique key for each object
    private int uid = 0;
    
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}


}