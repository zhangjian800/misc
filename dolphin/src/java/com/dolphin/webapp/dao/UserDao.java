package com.dolphin.webapp.dao;

import com.dolphin.webapp.common.dao.GenericDao;

import com.dolphin.common.exception.GenericException;
import com.dolphin.common.vo.DOLValueObject;

import com.dolphin.webapp.vo.User;

public interface UserDao <T extends DOLValueObject> extends GenericDao<T>{
	
	public User getUserByEmail(String email) throws GenericException;
}
