package com.test.daoauto;
// default package
// Generated Sep 14, 2016 6:11:58 PM by Hibernate Tools 5.1.0.Beta1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.training.entity.LibraryItems;

/**
 * Home object for domain model class LibraryItems.
 * @see .LibraryItems
 * @author Hibernate Tools
 */
@Stateless
public class LibraryItemsHome {

	private static final Log log = LogFactory.getLog(LibraryItemsHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(LibraryItems transientInstance) {
		log.debug("persisting LibraryItems instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(LibraryItems persistentInstance) {
		log.debug("removing LibraryItems instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public LibraryItems merge(LibraryItems detachedInstance) {
		log.debug("merging LibraryItems instance");
		try {
			LibraryItems result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public LibraryItems findById(String id) {
		log.debug("getting LibraryItems instance with id: " + id);
		try {
			LibraryItems instance = entityManager.find(LibraryItems.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
