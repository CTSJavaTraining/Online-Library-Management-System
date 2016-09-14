package com.test.daoauto;
// default package
// Generated Sep 14, 2016 6:11:58 PM by Hibernate Tools 5.1.0.Beta1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.training.entity.LoginDetails;

/**
 * Home object for domain model class LoginDetails.
 * @see .LoginDetails
 * @author Hibernate Tools
 */
@Stateless
public class LoginDetailsHome {

	private static final Log log = LogFactory.getLog(LoginDetailsHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(LoginDetails transientInstance) {
		log.debug("persisting LoginDetails instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(LoginDetails persistentInstance) {
		log.debug("removing LoginDetails instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public LoginDetails merge(LoginDetails detachedInstance) {
		log.debug("merging LoginDetails instance");
		try {
			LoginDetails result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public LoginDetails findById(String id) {
		log.debug("getting LoginDetails instance with id: " + id);
		try {
			LoginDetails instance = entityManager.find(LoginDetails.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
