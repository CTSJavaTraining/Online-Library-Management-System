package com.training.factory;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApplicationSessionFactory {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationSessionFactory.class, args);

	}

	public static SessionFactory factoryProvider() {
		return new Configuration().configure().buildSessionFactory();
	}
}
