package com.test.daoauto;
// default package
// Generated Sep 14, 2016 6:11:58 PM by Hibernate Tools 5.1.0.Beta1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.training.entity.WishList;
import com.training.entity.WishListId;

/**
 * Home object for domain model class WishList.
 * @see .WishList
 * @author Hibernate Tools
 */
@Stateless
public class WishListHome {

	private static final Log log = LogFactory.getLog(WishListHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(WishList transientInstance) {
		log.debug("persisting WishList instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(WishList persistentInstance) {
		log.debug("removing WishList instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public WishList merge(WishList detachedInstance) {
		log.debug("merging WishList instance");
		try {
			WishList result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public WishList findById(WishListId id) {
		log.debug("getting WishList instance with id: " + id);
		try {
			WishList instance = entityManager.find(WishList.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
