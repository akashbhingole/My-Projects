package com.bo.login;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.controller.LoginController;
import com.dao.login.LoginDAO;
import com.model.login.Login;

public class LoginBO {

	/*
	 * Below code for initializing Log4j
	 */
	private static final Logger logger = Logger.getLogger(LoginBO.class);

	@Autowired
	LoginDAO logindao;

	/**
	 * @return the logindao
	 */
	public LoginDAO getLogindao() {
		return logindao;
	}

	/**
	 * @param logindao
	 *            the logindao to set
	 */
	public void setLogindao(LoginDAO logindao) {
		this.logindao = logindao;
	}

	/**
	 * @param emailId
	 * @param password
	 * @return object of User
	 * @Description this method for check login
	 */
	public Login getLoginDetails(String emailId, String password) {
		return logindao.checkLogin(emailId, password);
	}

}
