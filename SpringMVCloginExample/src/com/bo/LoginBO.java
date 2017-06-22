package com.bo;

import com.dao.LoginDAO;

/**
 * This class is used to perform bo login
 * 
 * */
public class LoginBO {

	private LoginDAO loginDAO;
	
	public LoginDAO getLoginDAO() {
		return loginDAO;
	}

	public void setLoginDAO(LoginDAO loginDAO) {
		this.loginDAO = loginDAO;
	}

	public boolean isValidUser(String username, String password) throws Exception {
		return loginDAO.isValidUser(username, password);
	}

}
