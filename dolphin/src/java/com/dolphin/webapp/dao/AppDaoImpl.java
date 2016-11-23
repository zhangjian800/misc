package com.dolphin.webapp.dao;

import java.sql.SQLException;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.dolphin.common.exception.ExceptionKeys;
import com.dolphin.common.exception.GenericException;
import com.dolphin.webapp.common.dao.DefaultGenericDaoImpl;
import com.dolphin.webapp.vo.App;
import com.dolphin.webapp.vo.AppFile;
import com.ibatis.sqlmap.client.SqlMapClient;

public class AppDaoImpl extends DefaultGenericDaoImpl<App> implements AppDao<App> {
	
	private static final String _SQL_STATEMENT_UPLOADFILE = "uploadAppFile";
	private static final String _SQL_STATEMENT_REMOVE_FILE = "removeAppFile";
	private static final String _SQL_STATEMENT_GET_FILE = "getAppFile";
	
	private static final String _SQL_STATEMENT_GET_APPBYCODE = "getAppByCode";

	
	public void uploadAppFile(AppFile appfile) throws GenericException{
		
//		SqlMapClient client = getSqlMapClient();
		try {
//			client.startTransaction();
			getSqlMapClientTemplate().update(_SQL_STATEMENT_UPLOADFILE, appfile);
			
//			client.commitTransaction();
		} catch (Exception e) {
			throw new GenericException(ExceptionKeys.DAO_ERROR, e);		
		} finally {
//			try {
////				client.endTransaction();
//			} catch (SQLException e) {
//				throw new GenericException(ExceptionKeys.DAO_ERROR, e);		
//			}
		}
//		this.UPDATE(_SQL_STATEMENT_UPLOADFILE, appfile);

	}
	public void removeAppFile(int appID) throws GenericException{
		this.UPDATE(_SQL_STATEMENT_REMOVE_FILE, appID);
	}
	public AppFile getAppFile(int appID) throws GenericException{
		return (AppFile)this.queryForObject(_SQL_STATEMENT_GET_FILE, appID);
	}
	
	public App getAppByAppcode(String appCode)  throws GenericException{
		return (App)this.queryForObject(_SQL_STATEMENT_GET_APPBYCODE, appCode);
	}
}
