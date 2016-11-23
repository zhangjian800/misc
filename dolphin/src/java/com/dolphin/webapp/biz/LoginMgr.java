package com.dolphin.webapp.biz;

import com.dolphin.common.exception.GenericException;

public interface LoginMgr {
	
	public LoginResult login(String email, String password) throws GenericException;
}
