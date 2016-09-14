package com.test.daoauto;
// default package
// Generated Sep 14, 2016 6:11:58 PM by Hibernate Tools 5.1.0.Beta1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.training.entity.LikedList;
import com.training.entity.LikedListId;

/**
 * Home object for domain model class LikedList.
 * @see .LikedList
 * @author Hibernate Tools
 */
@Stateless
public class LikedListHome {

	private static final Log log = LogFactory.getLog(LikedListHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(LikedList transientInstance) {
		log.debug("persisting LikedList instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(LikedList persistentInstance) {
		log.debug("removing LikedList instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public LikedList merge(LikedList detachedInstance) {
		log.debug("merging LikedList instance");
		try {
			LikedList result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public LikedList findById(LikedListId id) {
		log.debug("getting LikedList instance with id: " + id);
		try {
			LikedList instance = entityManager.find(LikedList.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
