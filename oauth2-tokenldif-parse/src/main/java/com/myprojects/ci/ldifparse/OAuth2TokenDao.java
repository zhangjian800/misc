package com.myprojects.ci.ldifparse;

import java.util.List;


public interface OAuth2TokenDao {
	public void insertOAuth2Token(OAuth2Token token) throws Exception;
	public void insertOAuth2Token(List<OAuth2Token> token) throws Exception;

}
