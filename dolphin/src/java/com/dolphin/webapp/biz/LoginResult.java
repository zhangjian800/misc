package com.dolphin.webapp.biz;

import com.dolphin.common.vo.DOLValueObject;

public class LoginResult  implements DOLValueObject{

	public static final int _SUCCESS = 0;
	public static final int _PASSWORD_INVALID = 1;
	public static final int _INACTIVE = 2;
	public static final int _ACCOUNT_NOTEXITED = 3;	
	
	private int loginStatus = _SUCCESS;
	private int uid;
	public int getLoginStatus() {
		return loginStatus;
	}
	public void setLoginStatus(int loginStatus) {
		this.loginStatus = loginStatus;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
}
