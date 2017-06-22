
package com.services.factory;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.util.AppContextUtil;

public class Factory {

	/*
	 * Below code for initializing Log4j
	 */

	protected static final Logger logger = Logger.getLogger(Factory.class);

	/**
	 * @return
	 * @description this method for create instance of itself class
	 */
	public static final Factory getInstance() {
		return Initiated.instance;
	}

	/**
	 * @description this inner class for create instance of itself class
	 */
	private static class Initiated {
		static Factory instance = new Factory();
	}

	/**
	 * @param <T>
	 * @param svcClass
	 * @return instance of class
	 * @description this method for return instance of singletone/non
	 *              singletone/request/session class getting from spring IOC
	 *              context by sending class name as proxy and provide
	 *              dependency features,IOC controller
	 */
	@SuppressWarnings("unchecked")
	public <T> T getInstanceService(Class<T> svcClass) {
		logger.info("Inside getService method in Factory class");
		try {
			final T service;
			ApplicationContext appContext = AppContextUtil.getAppcontext();
			service = (T) appContext.getBean(svcClass.getSimpleName());
			logger.info("Exit from getService method in Factory class");
			return service;
		} catch (ClassCastException e) {
			logger.error("Exception in getService method in Factory", e);
			throw new RuntimeException("Failed to create service instance of  "+ svcClass.getSimpleName());
		} catch (Exception e) {
			logger.error("Exception in getService method in Factory", e);
			throw new RuntimeException("Failed to create service instance of "+ svcClass.getSimpleName());
		}
	}

	/**
	 * @param <T>
	 * @param svcClass
	 * @return instance of class
	 * @description this method for return instance of singletone/non
	 *              singletone/request/session class getting from spring IOC
	 *              context by sending proxy name and provide dependency
	 *              features, IOC controller
	 */
	@SuppressWarnings("unchecked")
	public <T> T getInstanceProxyService(String proxy) {
		logger.info("Inside getInstanceProxyService method in Factory class");
		try {
			final T service;
			ApplicationContext appContext = AppContextUtil.getAppcontext();
			service = (T) appContext.getBean(proxy);
			logger.info("Exit from getInstanceProxyService method in Factory class");
			return service;
		} catch (ClassCastException e) {
			logger.error("Exception in getInstanceProxyService method in Factory",e);
			throw new RuntimeException("Failed to create service proxy of  "+ proxy);
		} catch (Exception e) {
			logger.error("Exception in getInstanceProxyService method in Factory",e);
			throw new RuntimeException("Failed to create service proxy of "	+ proxy);
		}
	}

}
