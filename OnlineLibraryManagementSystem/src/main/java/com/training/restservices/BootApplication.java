package com.training.restservices;

import javax.security.auth.message.config.AuthConfigFactory;

import org.apache.catalina.authenticator.jaspic.AuthConfigFactoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 * @author 447482
 *
 */
@SpringBootApplication
public class BootApplication {

	private static final Logger logger = LoggerFactory.getLogger(BootApplication.class);

	private BootApplication() {
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// Configuration factory is null for tomcat dependencies config.
		if (AuthConfigFactory.getFactory() == null) {
			AuthConfigFactory.setFactory(new AuthConfigFactoryImpl());
		}
		SpringApplication.run(BootApplication.class, args);

		logger.info("---------------------- Application starts--------------------");

	}

}
