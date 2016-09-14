package com.test.daoauto;
// default package
// Generated Sep 14, 2016 6:11:58 PM by Hibernate Tools 5.1.0.Beta1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.training.entity.AddressDetails;
import com.training.entity.AddressDetailsId;

/**
 * Home object for domain model class AddressDetails.
 * @see .AddressDetails
 * @author Hibernate Tools
 */
@Stateless
public class AddressDetailsHome {

	private static final Log log = LogFactory.getLog(AddressDetailsHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(AddressDetails transientInstance) {
		log.debug("persisting AddressDetails instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(AddressDetails persistentInstance) {
		log.debug("removing AddressDetails instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public AddressDetails merge(AddressDetails detachedInstance) {
		log.debug("merging AddressDetails instance");
		try {
			AddressDetails result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public AddressDetails findById(AddressDetailsId id) {
		log.debug("getting AddressDetails instance with id: " + id);
		try {
			AddressDetails instance = entityManager.find(AddressDetails.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
