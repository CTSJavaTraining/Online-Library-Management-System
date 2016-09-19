package com.training.restservices;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.training.entity.UserDetails;

@ComponentScan
@RestController
@EnableAutoConfiguration
@RequestMapping("/home")
public class UserValidationService {

	private static final Logger logger = LoggerFactory.getLogger(UserValidationService.class);
	SessionFactory factory = BootApplication.factory;

	@RequestMapping(value = "/uservalidation", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String validateUserId(@RequestBody UserDetails userdetails) {

		String username = userdetails.getUserName();

		if (!username.isEmpty()) {

			try (Session session = factory.openSession()) {
				session.beginTransaction();
				logger.debug("User entered username is {} ", username);

				@SuppressWarnings("unchecked")
				Query<UserDetails> query = session.createQuery("FROM UserDetails WHERE userName = :uName");
				query.setParameter("uName", username);

				if (query.getResultList().isEmpty()) {
					return "User does not exist";
				} else {
					return "User exist";
				}
			}
		} else {
			return "Username should not be blank";
		}

	}
}