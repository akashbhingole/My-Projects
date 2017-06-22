package com.servlet;

import java.io.File;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.util.AppContextUtil;

public class AppService extends HttpServlet {

	/**
	 * Below code for initiallize log4j for this class
	 */
	protected static final Logger logger = Logger.getLogger(AppService.class);

	/*
	 * (non-Javadoc)
	 *
	 * @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig)
	 *
	 * @description this life cycle init method for initializing the servlet
	 *
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		String path = getServletContext().getRealPath(new String(new char[] { File.separatorChar }))
					  + "WEB-INF" + new String(new char[] { File.separatorChar })
					  + "classes" + new String(new char[] { File.separatorChar });

		String springcfgFile = "applicationContext.xml";

		AppContextUtil.applicationContextfile = springcfgFile;
		AppContextUtil.getAppcontext();
		initLog4j(path);
	}

	/**
	 * @param path
	 * @description this method for configure log4j path
	 */
	private void initLog4j(String path) {
		try {
			PropertyConfigurator.configure(path + "log4j.properties");
		} catch (Exception ex) {
			System.out.println("Log4J can not be initialized");
			logger.info("Log4J can not be configured");
		}
	}

}
