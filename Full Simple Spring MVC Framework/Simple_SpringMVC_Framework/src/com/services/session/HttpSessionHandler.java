
package com.services.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

public class HttpSessionHandler {
	/*
	 * Below code for initializing Log4j
	 */
	private static final Logger logger = Logger.getLogger(HttpSessionHandler.class);

	public HttpSession httpSession = null;

	/**
	 * @param request
	 * @description this method for set HttpServletRequest
	 */
	public void setHttpSession(HttpServletRequest request) {
		if (request != null) {
			this.httpSession = request.getSession();
		}

	}

	/**
	 * @param request
	 * @description this method for get HttpServletRequest
	 */
	public HttpSession getHttpSession() {
		return this.httpSession;
	}

	/**
	 * @param request
	 * @description this method for get session attributes value by key name
	 */
	public Object getAttribute(String name) {
		Object obj = null;
		try {
			if (httpSession != null) {
				obj = httpSession.getAttribute(name);
			}
		} catch (Exception e) {
			logger.error("Exception in getAttribute method in HttpSessionHandler class",e);
		}
		return obj;
	}

	/**
	 * @param request
	 * @description this method for set session attributes value for key name
	 */
	public void setAttribute(String name, Object obj) {
		try {
			if (httpSession != null) {
				httpSession.setAttribute(name, obj);
			}
		} catch (Exception e) {
			logger.error("Exception in setAttribute method in HttpSessionHandler class",e);
		}

	}

	/**
	 * @param request
	 * @description this method for remove attributes value for key name
	 */
	public void removeAttribute(String name) {
		try {
			if (httpSession != null && httpSession.getAttribute(name) != null) {
				httpSession.removeAttribute(name);
			}
		} catch (Exception e) {
			logger.error("Exception in removeAttribute method in HttpSessionHandler class",e);
		}

	}

	/**
	 * @param request
	 * @description this method for set invalid httpsession
	 */
	public void destroySession() {
		try {
			if (httpSession != null) {
				httpSession.invalidate();
			}
		} catch (Exception e) {
			logger.error("Exception in destroySession method in HttpSessionHandler class",e);
		}
	}

}
