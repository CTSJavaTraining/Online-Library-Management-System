package com.test.daoauto;
// default package
// Generated Sep 14, 2016 6:11:58 PM by Hibernate Tools 5.1.0.Beta1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.training.entity.SubscribedList;
import com.training.entity.SubscribedListId;

/**
 * Home object for domain model class SubscribedList.
 * @see .SubscribedList
 * @author Hibernate Tools
 */
@Stateless
public class SubscribedListHome {

	private static final Log log = LogFactory.getLog(SubscribedListHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(SubscribedList transientInstance) {
		log.debug("persisting SubscribedList instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(SubscribedList persistentInstance) {
		log.debug("removing SubscribedList instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public SubscribedList merge(SubscribedList detachedInstance) {
		log.debug("merging SubscribedList instance");
		try {
			SubscribedList result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public SubscribedList findById(SubscribedListId id) {
		log.debug("getting SubscribedList instance with id: " + id);
		try {
			SubscribedList instance = entityManager.find(SubscribedList.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
