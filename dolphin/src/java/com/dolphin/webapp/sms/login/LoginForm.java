package com.dolphin.webapp.sms.login;

import com.dolphin.webapp.common.web.struts.GenericForm;

public class LoginForm extends GenericForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3018806310812827632L;
	
	private String email;
	private String password;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
