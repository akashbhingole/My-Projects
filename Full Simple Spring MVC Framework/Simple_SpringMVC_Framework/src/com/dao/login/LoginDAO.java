
package com.dao.login;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.dao.common.DAOSupport;
import com.model.login.Login;
public class LoginDAO extends DAOSupport{

	/*
	 * Below code for initiallize log4j for this class
	 */
	private static final Logger logger = Logger.getLogger(LoginDAO.class);

	/**
	 * @param emailId
	 * @param password
	 * @return object of user if user successfully loged in else null.
	 * @author Shib das
	 */

	public  Login checkLogin(String emailId,String password){
		logger.info("Inside checkLogin method in LoginDAO class");
		/*
		 * org.hibernate.Session declaration and initialization for transaction with database
		 */
		Session session = null;
		Login login=null;
		try {
			session=getHibernateTemplate().getMasterSession();
			login = (Login)session.createCriteria(Login.class)
			.add(Restrictions.eq("emailId",emailId))
			.add(Restrictions.eq("password",password))
			.uniqueResult();
		}catch(HibernateException e){
			logger.error("Exception in checkLogin method in LoginDAO class",e);
		}catch(Exception e){
			logger.error("Exception in checkLogin method in LoginDAO class",e);
		}
		logger.info("Exit from checkLogin method in LoginDAO class");
		return login;
	}

}
