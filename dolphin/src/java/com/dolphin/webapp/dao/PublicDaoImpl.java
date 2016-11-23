package com.dolphin.webapp.dao;

import java.util.HashMap;
import java.util.List;

import com.dolphin.common.exception.GenericException;
import com.dolphin.webapp.common.dao.AbstractIbatisDao;
import com.dolphin.webapp.vo.ConfirmChargeRptSearchVO;
import com.dolphin.webapp.vo.PhoneCharge;
import com.dolphin.webapp.vo.PhoneNumType;
import com.dolphin.webapp.vo.PhoneProduct;
import com.dolphin.webapp.vo.SystemConfig;


public class PublicDaoImpl extends AbstractIbatisDao implements PublicDao {
	
	private static final String _SQL_STATEMENT_LISTALL_PRODUCTS = "listAllProducts";
	private static final String _SQL_STATEMENT_LISTALL_VERSIONS = "listAllVersions";
	private static final String _SQL_STATEMENT_LISTALL_PROVINCES = "listAllProvinces";
	private static final String _SQL_STATEMENT_LISTALL_CITIESSBYPROVICNE = "listAllCitiesByProvinces";
	
	private static final String _SQL_STATEMENT_FINDPHONENUMTYPEBYPREFIX = "findPhoneNumTypeByPrefix";
	private static final String _SQL_STATEMENT_LISTALLPHONETYPES = "listAllPhoneNumTypes";

	
	private static final String _SQL_STATEMENT_INSERT_PHONE_PRODUCT = "insertPhoneProduct";
	
	private static final String _SQL_STATEMENT_GETSYSTEMCONFIG = "getSystemConfig";
	private static final String _SQL_STATEMENT_UPDATESYSTEMCONFIG = "updateSystemConfig";

	private static final String _SQL_STATEMENT_LIST_PHONE_CHARGE = "listConfirmCharge";
	
	@Override
	public List<PhoneProduct> getAllProducts() throws GenericException {
		return (List<PhoneProduct>)queryForList(_SQL_STATEMENT_LISTALL_PRODUCTS, null);
	}

	@Override
	public List<HashMap> getAllVersions() throws GenericException {
		return (List<HashMap>)queryForList(_SQL_STATEMENT_LISTALL_VERSIONS, null);
	}

	@Override
	public List<HashMap> getAllProvinces() throws GenericException {
		return (List<HashMap>)queryForList(_SQL_STATEMENT_LISTALL_PROVINCES, null);
	}
	@Override
	public List<HashMap> getCitiesByProcince(String provinceCode) throws GenericException {
		return (List<HashMap>)queryForList(_SQL_STATEMENT_LISTALL_CITIESSBYPROVICNE, provinceCode);
	}

	@Override	
	public PhoneNumType getPhoneNumType(String prefix) 	throws GenericException {
		Object obj = queryForObject(_SQL_STATEMENT_FINDPHONENUMTYPEBYPREFIX, prefix);
		if(obj != null){
			return (PhoneNumType)obj;
		}
		return null;		
	}
	@Override	
	public List<PhoneNumType> getAllPhoneNumTypes() throws GenericException {
		Object obj = queryForList(_SQL_STATEMENT_LISTALLPHONETYPES);
		if(obj != null){
			return (List<PhoneNumType> )obj;
		}
		return null;		
	}
	

	@Override	
	public void insertPhoneProduct(PhoneProduct product) 	throws GenericException{
		this.insert(_SQL_STATEMENT_INSERT_PHONE_PRODUCT, product);
	}

	@Override
	public SystemConfig getSystemConfig() throws GenericException {
		Object obj = queryForObject(_SQL_STATEMENT_GETSYSTEMCONFIG, null);
		if(obj != null){
			return (SystemConfig)obj;
		}
		return null;		
	}

	@Override
	public void updatetSystemConfig(SystemConfig sc) throws GenericException {
		this.insert(_SQL_STATEMENT_UPDATESYSTEMCONFIG, sc);
	}

	@Override
	public List<PhoneCharge> listPhoneCharge(ConfirmChargeRptSearchVO vo)
			throws GenericException {
		Object obj = queryForList(_SQL_STATEMENT_LIST_PHONE_CHARGE, vo);
		if(obj != null){
			return (List<PhoneCharge>)obj;
		}
		return null;
	}
	
}
