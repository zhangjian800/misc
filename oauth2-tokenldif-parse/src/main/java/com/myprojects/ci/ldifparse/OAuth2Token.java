package com.myprojects.ci.ldifparse;

import java.util.Set;

public class OAuth2Token {

	private String oauth2TokenId;
	private String realm;
	private String clientid;
	private Set<String> scopes;
	private String tokenType;
	private String uid;
	private String uuid;
	private String expireTime;
	private String createTime;
	private String modifyTime;
	
	private String oauth2tokenreuseindex;
	
	public String getOauth2TokenId() {
		return oauth2TokenId;
	}
	public void setOauth2TokenId(String oauth2TokenId) {
		this.oauth2TokenId = oauth2TokenId;
	}
	public String getRealm() {
		return realm;
	}
	public void setRealm(String realm) {
		this.realm = realm;
	}
	public String getClientid() {
		return clientid;
	}
	public void setClientid(String clientid) {
		this.clientid = clientid;
	}
	public Set<String> getScopes() {
		return scopes;
	}
	public void setScopes(Set<String> scopes) {
		this.scopes = scopes;
	}
	public String getTokenType() {
		return tokenType;
	}
	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(String expireTime) {
		this.expireTime = expireTime;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getOauth2tokenreuseindex() {
		return oauth2tokenreuseindex;
	}
	public void setOauth2tokenreuseindex(String oauth2tokenreuseindex) {
		this.oauth2tokenreuseindex = oauth2tokenreuseindex;
	}
	public String getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

}
