package com.test.daoauto;
// default package
// Generated Sep 14, 2016 6:11:58 PM by Hibernate Tools 5.1.0.Beta1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.training.entity.MemberDetails;

/**
 * Home object for domain model class MemberDetails.
 * @see .MemberDetails
 * @author Hibernate Tools
 */
@Stateless
public class MemberDetailsHome {

	private static final Log log = LogFactory.getLog(MemberDetailsHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(MemberDetails transientInstance) {
		log.debug("persisting MemberDetails instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(MemberDetails persistentInstance) {
		log.debug("removing MemberDetails instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public MemberDetails merge(MemberDetails detachedInstance) {
		log.debug("merging MemberDetails instance");
		try {
			MemberDetails result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public MemberDetails findById(Integer id) {
		log.debug("getting MemberDetails instance with id: " + id);
		try {
			MemberDetails instance = entityManager.find(MemberDetails.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
