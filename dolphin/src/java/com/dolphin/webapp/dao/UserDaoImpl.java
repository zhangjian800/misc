package com.dolphin.webapp.dao;

import com.dolphin.webapp.common.dao.DefaultGenericDaoImpl;

import com.dolphin.common.exception.GenericException;
import com.dolphin.webapp.vo.User;

public class UserDaoImpl extends DefaultGenericDaoImpl<User> implements UserDao<User> {
		
	public User getUserByEmail(String email) throws GenericException{
		return (User)queryForObject("getUserByEmail", email.toLowerCase());
	}
}
