package com.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppContextUtil {

	public static String applicationContextfile = null;
	private static ApplicationContext appContext = null;

	/**
	 * @return path of Applicationcontext
	 * @description this method for return class path of Applicationcontext
	 */
	public static ApplicationContext getAppcontext() {
		if (appContext == null) {
			appContext = new ClassPathXmlApplicationContext(applicationContextfile);
		}
		return appContext;
	}

}
