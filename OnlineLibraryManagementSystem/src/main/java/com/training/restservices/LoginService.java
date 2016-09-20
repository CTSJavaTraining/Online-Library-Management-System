package com.training.restservices;

import javax.persistence.Query;
import javax.ws.rs.core.Response;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
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

import com.training.entity.LoginDetails;

/**
 * 
 * @author 542224 Login service to validate the login details entered
 *
 */
@ComponentScan
@RestController
@EnableAutoConfiguration
public class LoginService {

	private static final Logger logger = LoggerFactory.getLogger(LoginService.class);

	SessionFactory factory = BootApplication.factory;

	@RequestMapping(value = "/login", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Response validateLoginDetails(@RequestBody LoginDetails loginDetails) {

		logger.info("Validating user for logging in");

		String userId = loginDetails.getUserId();
		String password = loginDetails.getPassword();

		if ((!userId.isEmpty()) && (!password.isEmpty())) {
			try (Session session = factory.openSession()) {

				session.beginTransaction();

				String hql = "SELECT password FROM LoginDetails WHERE userId= :id";
				Query query = session.createQuery(hql);

				query.setParameter("id", userId);
				query.setMaxResults(1);

				String results = query.getResultList().get(0).toString();

				if (results.isEmpty()) {
					return Response.status(Response.Status.NOT_FOUND).entity("User does not exist. Please signup").build();  
				} else if (password.equalsIgnoreCase(results)) {
					return Response.status(Response.Status.OK).entity("User " + userId + " logged in successfully").build(); 
				} else {
					return Response.status(Response.Status.BAD_REQUEST).entity("Incorrect Login Details").build();
				}
			}
		} else {
			return  Response.status(Response.Status.BAD_REQUEST).entity("userId or password should not be empty").build();
		}
	}

}
