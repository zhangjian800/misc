package com.dolphin.webapp.common.restcall;

import java.util.List;

import com.dolphin.webapp.vo.AdsDetailContent;

public class RestCallResponse {
	
	private int httpStatus;
	private String httpResponseBody;

	private String failureReason;
	/* response element*/
	private boolean isSuccess = false;//httpStatus=200 and process success, isSuccess=true
	
	public int isHttpStatus() {
		return httpStatus;
	}
	public void setHttpStatus(int httpStatus) {
		this.httpStatus = httpStatus;
		this.isSuccess = httpStatus==200;
	}
	public boolean isSuccess() {
		return isSuccess;
	}
	public int getHttpStatus() {
		return httpStatus;
	}
	public String getFailureReason() {
		return failureReason;
	}
	public void setFailureReason(String failureReason) {
		this.failureReason = failureReason;
	}
	public String getHttpResponseBody() {
		return httpResponseBody;
	}
	public void setHttpResponseBody(String httpResponseBody) {
		this.httpResponseBody = httpResponseBody;
	}
	
}
