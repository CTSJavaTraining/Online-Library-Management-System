package com.test.daoauto;
// default package
// Generated Sep 14, 2016 6:11:58 PM by Hibernate Tools 5.1.0.Beta1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.training.entity.UserContactDetails;

/**
 * Home object for domain model class UserContactDetails.
 * @see .UserContactDetails
 * @author Hibernate Tools
 */
@Stateless
public class UserContactDetailsHome {

	private static final Log log = LogFactory.getLog(UserContactDetailsHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(UserContactDetails transientInstance) {
		log.debug("persisting UserContactDetails instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(UserContactDetails persistentInstance) {
		log.debug("removing UserContactDetails instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public UserContactDetails merge(UserContactDetails detachedInstance) {
		log.debug("merging UserContactDetails instance");
		try {
			UserContactDetails result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public UserContactDetails findById(String id) {
		log.debug("getting UserContactDetails instance with id: " + id);
		try {
			UserContactDetails instance = entityManager.find(UserContactDetails.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
