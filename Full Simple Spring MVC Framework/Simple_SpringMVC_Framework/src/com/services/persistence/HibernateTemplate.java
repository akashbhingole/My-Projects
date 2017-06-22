package com.services.persistence;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class HibernateTemplate extends HibernateDaoSupport {

	/*
	 * Below code for initializing Log4j
	 */
	private static final Logger logger = Logger.getLogger(HibernateTemplate.class);

	/*
	 * The ThreadLocal variable for keeping hibernate session and closing
	 * runtime after completion request
	 */
	private ThreadLocal<Session> threadSession = new ThreadLocal<Session>();

	/*
	 * The variable for flag check read or write
	 */
	public static final String READ = "READ";
	public static final String WRITE = "WRITE";

	/**
	 * @description Constractor for initialize ThreadLocal variable
	 */
	public HibernateTemplate() {
		threadSession = new ThreadLocal<Session>() {
			protected Session initialValue() {
				return null;
			}
		};
	}

	/**
	 * @return hibernate session of master database
	 */
	public Session getMasterSession() {
		return getHibernateTemplate().getSessionFactory().getCurrentSession();
	}


	/**
	 * @param success
	 * @description this method for close hibernate session with commit/rollback
	 */
	public void closeSession(Boolean success) {
		Session s = (Session) threadSession.get();
		try {
			if (s != null && s.isOpen()) {
				if (success != null && success == false) {
					rollbackTransaction(s.getTransaction());
				} else if (success != null && success == true && !s.connection().isReadOnly()) {
					commitTransaction(s.getTransaction());
				}
			}
		} catch (Exception e) {
			logger.error("Exception in closeSession method in HibernateTemplate class",e);
		} finally {
			closeSession();
		}
	}

	/**
	 * @param tx
	 * @description this method for commit transaction
	 */
	public void commitTransaction(Transaction tx) {
		try {
			if (tx != null) {
				tx.commit();
			}
		} catch (HibernateException e) {
			logger.error("Exception in commitTransaction method in HibernateTemplate class",e);
		}
	}

	/**
	 * @param tx
	 * @description this method for rollback transaction
	 */
	public void rollbackTransaction(Transaction tx) {
		try {
			if (tx != null && !tx.wasCommitted() && !tx.wasRolledBack()) {
				tx.rollback();
			}
		} catch (Exception e) {
			logger.error("Exception in rollbackTransaction method in HibernateTemplate class",e);
		}
	}

	/**
	 * @param tx
	 * @description this method for rollback transaction
	 */
	public void rollbackTransaction() {
		Session s = (Session) threadSession.get();
		Transaction tx = s.getTransaction();
		try {
			if (tx != null && !tx.wasCommitted() && !tx.wasRolledBack()) {
				tx.rollback();
			}
		} catch (Exception e) {
			logger.error("Exception in rollbackTransaction method in HibernateTemplate class",e);
		}
	}

	/**
	 * @param tx
	 * @description this method for close hibernate session
	 */
	public void closeSession() {
		Session session = (Session) threadSession.get();
		if (session != null && session.isOpen()) {
			session.flush();
			session.close();
		}
		threadSession.set(null);
	}
}
