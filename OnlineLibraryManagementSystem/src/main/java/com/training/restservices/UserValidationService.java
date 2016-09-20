package com.training.restservices;

import javax.ws.rs.core.Response;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
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
	SessionFactory factory = new Configuration().configure().buildSessionFactory();

	@RequestMapping(value = "/uservalidation", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Response validateUserId(@RequestBody UserDetails userdetails) {

		String username = userdetails.getUserName();

		if (!username.isEmpty()) {

			try (Session session = factory.openSession()) {
				session.beginTransaction();
				logger.debug("User entered username is {} ", username);

				@SuppressWarnings("unchecked")
				Query<UserDetails> query = session.createQuery("FROM UserDetails WHERE userName = :uName");
				query.setParameter("uName", username);
				query.setMaxResults(1);

				if (query.getResultList().isEmpty()) {
					return Response.status(Response.Status.OK).entity("User does not exist").build();
				} else {
					return Response.status(Response.Status.CONFLICT).entity("User exist").build();
				}
			}
		} else {
			return Response.status(Response.Status.BAD_REQUEST).entity("Username should not be blank").build();
		}

	}
}