package com.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;

public class Constants {

	/*
	 * Below code for initialize Log4j
	 */
	protected static final Logger logger = Logger.getLogger(Constants.class);

	/*
	 * Below constants variable related to database configuration value
	 */
	public static String MYSQL_CONNECTION_URL = "jdbc:mysql://localhost:3306/";
	public static String HIBERNATE_DIALECT = "org.hibernate.dialect.MySQLDialect";
	public static String MYSQL_DRIVER_NAME = "com.mysql.jdbc.Driver";
	public static String MYSQL_USER = "root";
	public static String MYSQL_PASSWORD = "root";

	public static Map english_us_resource = new HashMap();

	/**
	 * @description Static block for add all constants key and value in hash map
	 *
	 */
	static {
		if (english_us_resource == null || english_us_resource.size() < 1) {
			setResourceEnglishUSLanguage();
		}
	}

	/**
	 * @description this method for add all constants key and value in hash map
	 *
	 */
	private static void setResourceEnglishUSLanguage() {
		String value = "";

		try {
			logger.info("Started English US resource properties");
			Properties propsEnvEnglish = new Properties();
			Constants con = new Constants();
			propsEnvEnglish = con
					.getPropertiesFromClasspath("resources/resource_english_us.properties");

			Set set = propsEnvEnglish.keySet();
			Iterator itr = set.iterator();

			while (itr.hasNext()) {
				String key = (String) itr.next();
				value = (String) propsEnvEnglish.get(key);

				english_us_resource.put(key, value);

			}
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
	}

	/**
	 * @param propFileName
	 * @return inputstream of properties file
	 * @throws IOException
	 */
	private Properties getPropertiesFromClasspath(String propFileName)
			throws IOException {

		Properties props = new Properties();
		InputStream inputStream = this.getClass().getClassLoader()
				.getResourceAsStream(propFileName);

		if (inputStream == null) {
			logger.info("property file '" + propFileName
					+ "' not found in the classpath");
			throw new FileNotFoundException("property file '" + propFileName
					+ "' not found in the classpath");
		}

		props.load(inputStream);

		return props;
	}

	/**
	 * @param key
	 * @param localeCode
	 * @return string of value by filtering key
	 * @description this method for return value from specific properties file
	 *              by filtering key and local code
	 */
	public static String getResourceStringValue(String key, String localeCode) {
		String value = "";
		try {
			if (localeCode != null && localeCode.equals("en_US")) {
				value = (String) english_us_resource.get(key);

				if (value == null) {
					value = key;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return value;
	}

}
