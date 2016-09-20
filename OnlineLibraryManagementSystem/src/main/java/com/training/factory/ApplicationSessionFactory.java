package com.training.factory;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.training.restservices.UserServices;

/**
 * this class creates and provides session factory
 * 
 * @author 447383
 *
 */
public class ApplicationSessionFactory {

	private static final Logger logger = Logger.getLogger(UserServices.class);

	/**
	 * this method returns sessionfactory
	 * 
	 * @return
	 */
	public static SessionFactory returnFactory() {
		logger.info("Loading sessiong factory");
		return new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	}
}
