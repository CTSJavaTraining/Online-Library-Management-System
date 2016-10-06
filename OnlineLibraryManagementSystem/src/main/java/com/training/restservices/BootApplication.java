package com.training.restservices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * this is the main class where the application starts
 */
@SpringBootApplication
@ImportResource("applicationContext.xml")
public class BootApplication {

	private static final Logger logger = LoggerFactory.getLogger(BootApplication.class);

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		SpringApplication.run(BootApplication.class, args);
		logger.info("**************Application Starts****************/n/n/n/n");
	}


}
