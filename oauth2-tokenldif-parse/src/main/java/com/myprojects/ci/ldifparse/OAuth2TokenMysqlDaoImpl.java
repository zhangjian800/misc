package com.myprojects.ci.ldifparse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.myprojects.ci.common.ConfigProp;

/*
*
create table T_OAUTH_TOKENS (
	oauth2tokenid VARCHAR(32), 
	oauth2clientid VARCHAR(64),
	realm VARCHAR(64),
	username VARCHAR(64), 
	cisUUID VARCHAR(64), 
	scopes  VARCHAR(128),  
	tokentype VARCHAR(32), 
	tokenreuseindex VARCHAR(64), 
	createtime VARCHAR(64), 
	expiretime VARCHAR(64)
);

ALTER TABLE T_OAUTH_TOKENS ADD UNIQUE INDEX (oauth2tokenid);
ALTER TABLE T_OAUTH_TOKENS ADD  INDEX (oauth2clientid);
ALTER TABLE T_OAUTH_TOKENS ADD  INDEX (realm);
ALTER TABLE T_OAUTH_TOKENS ADD  INDEX (cisUUID);
ALTER TABLE T_OAUTH_TOKENS ADD  INDEX (username);
ALTER TABLE T_OAUTH_TOKENS ADD  INDEX (tokenreuseindex);
);
 */
public class OAuth2TokenMysqlDaoImpl implements OAuth2TokenDao{

	private static final String driver = ConfigProp.getProperty("connDriver");
	private static final  String url = ConfigProp.getProperty("connURL");
	private static final  String uName = ConfigProp.getProperty("connUserName");
	private static final String uPwd = ConfigProp.getProperty("connPass");
	static Connection conn = null;
    public boolean setDriver(String driver) {
        try {
            Class.forName(driver);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public Connection getConnection() {
        try {
        		setDriver(driver);
            return DriverManager.getConnection(url, uName, uPwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    
	private static final String INSERT_SQL = "INSERT INTO t_oauth_tokens (oauth2importdate, oauth2tokenid, oauth2clientid, realm, username, cisUUID, scopes, tokentype, "
																	+ "	tokenreuseindex, createtime, expiretime) "
																	+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	public void insertOAuth2Token(OAuth2Token token) throws Exception {
		if(conn == null) {
			conn = getConnection();;
		}
		Calendar rightNow = Calendar.getInstance();       
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");        
		String currDate = fmt.format(rightNow.getTime());   
		
		PreparedStatement pstmt = conn.prepareStatement(INSERT_SQL);
		int i = 1;
		pstmt.setString(i, currDate);
		i++;
		pstmt.setString(i, token.getOauth2TokenId());
		i++;
		pstmt.setString(i, token.getClientid());
		i++;
		pstmt.setString(i, token.getRealm());
		i++;
		pstmt.setString(i, token.getUid());
		i++;
		pstmt.setString(i, token.getUuid());
		i++;
		pstmt.setString(i, convertScopesArray2Str(token.getScopes()));
		i++;
		pstmt.setString(i, token.getTokenType());
		i++;
		pstmt.setString(i, token.getOauth2tokenreuseindex());
		i++;
		pstmt.setString(i, token.getCreateTime());
		i++;
		pstmt.setString(i, token.getExpireTime());
		
		pstmt.execute();
		
	}
	
	public void insertOAuth2Token(List<OAuth2Token> tokenList) throws Exception {
		
		if(conn == null) {
			conn = getConnection();;
		}
		PreparedStatement pstmt = conn.prepareStatement(INSERT_SQL);
		Calendar rightNow = Calendar.getInstance();       
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");        
		String currDate = fmt.format(rightNow.getTime());   
		for(int j=0; j<tokenList.size(); j++) {
			OAuth2Token token = (OAuth2Token) tokenList.get(j);
			int i = 1;
			pstmt.setString(i, currDate);
			i++;
			pstmt.setString(i, token.getOauth2TokenId());
			i++;
			pstmt.setString(i, token.getClientid());
			i++;
			pstmt.setString(i, token.getRealm());
			i++;
			pstmt.setString(i, token.getUid());
			i++;
			pstmt.setString(i, token.getUuid());
			i++;
			pstmt.setString(i, convertScopesArray2Str(token.getScopes()));
			i++;
			pstmt.setString(i, token.getTokenType());
			i++;
			pstmt.setString(i, token.getOauth2tokenreuseindex());
			i++;
			pstmt.setString(i, token.getCreateTime());
			i++;
			pstmt.setString(i, token.getExpireTime());

			pstmt.addBatch();
		}
		pstmt.clearParameters();
		pstmt.executeBatch();
	}

	
	private static String convertScopesArray2Str(Set<String> scopes) {
		if(scopes == null || scopes.isEmpty()) {
			return null;
		}
		Iterator<String> ite = scopes.iterator();
		String scopeStr = "";
		while (ite.hasNext()) {
			scopeStr = scopeStr + ite.next() + "|";
		}
		return scopeStr;
	}
}