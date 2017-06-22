package com.dao.common;

import com.services.persistence.HibernateTemplate;

public class DAOSupport {

	public static final String READ = "READ";
	public static final String WRITE = "WRITE";
	private HibernateTemplate hibernateTemplate;

	/**
	 * @return the hibernateTemplate
	 */
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	/**
	 * @param hibernateTemplate
	 *            the hibernateTemplate to set
	 */
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

}
