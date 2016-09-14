package com.test.daoauto;
// default package
// Generated Sep 14, 2016 6:11:58 PM by Hibernate Tools 5.1.0.Beta1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.training.entity.Movies;

/**
 * Home object for domain model class Movies.
 * @see .Movies
 * @author Hibernate Tools
 */
@Stateless
public class MoviesHome {

	private static final Log log = LogFactory.getLog(MoviesHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Movies transientInstance) {
		log.debug("persisting Movies instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Movies persistentInstance) {
		log.debug("removing Movies instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Movies merge(Movies detachedInstance) {
		log.debug("merging Movies instance");
		try {
			Movies result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Movies findById(String id) {
		log.debug("getting Movies instance with id: " + id);
		try {
			Movies instance = entityManager.find(Movies.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
