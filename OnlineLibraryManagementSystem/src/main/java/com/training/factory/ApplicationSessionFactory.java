package com.training.factory;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ApplicationSessionFactory {

	public static void main(String[] args) {

	}

	public SessionFactory factoryProvider() {
		return new Configuration().configure().buildSessionFactory();
	}
}
