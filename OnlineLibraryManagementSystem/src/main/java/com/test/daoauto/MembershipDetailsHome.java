package com.test.daoauto;
// default package
// Generated Sep 14, 2016 6:11:58 PM by Hibernate Tools 5.1.0.Beta1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.training.entity.MembershipDetails;
import com.training.entity.MembershipDetailsId;

/**
 * Home object for domain model class MembershipDetails.
 * @see .MembershipDetails
 * @author Hibernate Tools
 */
@Stateless
public class MembershipDetailsHome {

	private static final Log log = LogFactory.getLog(MembershipDetailsHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(MembershipDetails transientInstance) {
		log.debug("persisting MembershipDetails instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(MembershipDetails persistentInstance) {
		log.debug("removing MembershipDetails instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public MembershipDetails merge(MembershipDetails detachedInstance) {
		log.debug("merging MembershipDetails instance");
		try {
			MembershipDetails result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public MembershipDetails findById(MembershipDetailsId id) {
		log.debug("getting MembershipDetails instance with id: " + id);
		try {
			MembershipDetails instance = entityManager.find(MembershipDetails.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
