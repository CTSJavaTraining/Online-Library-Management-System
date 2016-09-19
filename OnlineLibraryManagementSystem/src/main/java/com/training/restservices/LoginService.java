package com.training.restservices;

import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author 542224 Login service to validate the login details entered
 *
 */
@ComponentScan
@RestController
@EnableAutoConfiguration
public class LoginService {

	private static final Logger logger = Logger.getLogger(LoginService.class);

	SessionFactory factory = returnFactory();

	@RequestMapping(value = "/login", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String validateLoginDetails(@RequestBody LoginDetails loginDetails) {

		String userId = loginDetails.getUserId();
		String password = loginDetails.getPassword();

		if ((!userId.isEmpty()) && (!password.isEmpty())) {
			try (Session session = factory.openSession()) {

				session.beginTransaction();

				String hql = "SELECT password FROM LoginDetails WHERE id= :id";
				Query query = session.createQuery(hql);

				query.setParameter("id", userId);

				List<?> results = query.list();

				if (results.isEmpty()) {
					return "User does not exist. Please signup";
				} else if (password.equalsIgnoreCase((String) results.get(0))) {
					return "User " + userId + " logged in successfully";
				} else {
					return "Incorrect Login Details";
				}
			}
		} else {
			return "userId or password should not be empty";
		}
	}

	public static SessionFactory returnFactory() {
		logger.info("Loading session factory");
		return new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	}

	public static void main(String[] args) {
		SpringApplication.run(LoginService.class, args);
		logger.info("---------------------- Application starts--------------------");
	}

}
