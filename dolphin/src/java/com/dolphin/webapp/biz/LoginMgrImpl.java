package com.dolphin.webapp.biz;

import com.dolphin.common.exception.ExceptionKeys;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.dolphin.common.exception.GenericException;

import com.dolphin.webapp.vo.User;
import com.dolphin.webapp.dao.UserDao;

public class LoginMgrImpl implements LoginMgr {

	UserDao userDao = null;

	public LoginResult login(String email, String password)
			throws GenericException {
		User user = userDao.getUserByEmail(email);
		LoginResult result = new LoginResult();
		// 1, Check if user existed
		if (user == null) {
			result.setLoginStatus(LoginResult._ACCOUNT_NOTEXITED);
			return result;
		}
		result.setUid(user.getUid());
		// 2, Check password
		boolean isValidPassword = generateHashOfPassword(password, user.getSalt()).equals(user.getPassword());
		if (!isValidPassword) {
			result.setLoginStatus(LoginResult._PASSWORD_INVALID);
			return result;
		}
		// 3, Check password
		if (!"Normal".equals(user.getStatus())) {
			result.setLoginStatus(LoginResult._INACTIVE);
			return result;
		}
		return result;
	}

	private String generateHashOfPassword(String plainText,String salt) throws GenericException {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			md.update((plainText + salt).getBytes());

			byte[] array = md.digest();
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < array.length; ++j) {
				int b = array[j] & 0xFF;
				if (b < 16) {
					sb.append('0');
				}
				sb.append(Integer.toHexString(b));
			}
			return sb.toString();

		} catch (NoSuchAlgorithmException e) {
			throw new GenericException(ExceptionKeys.NO_SHA2512_ALGORITHM, e);
		}
	}
	
	public static void main(String[] args) {
		String plainText = "Uabd*T@09F1";
		String salt = "893ab4";
		try {
			String hashPass = new LoginMgrImpl().generateHashOfPassword(plainText,salt);
			System.out.println(hashPass);
		} catch (GenericException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
}
