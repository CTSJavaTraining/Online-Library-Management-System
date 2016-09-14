package com.training.application;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Application {

	public static void main(String[] args) {

	}

	public SessionFactory factoryProvider() {
		return new Configuration().configure().buildSessionFactory();
	}
}
