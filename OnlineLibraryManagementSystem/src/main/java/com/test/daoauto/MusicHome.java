package com.test.daoauto;
// default package
// Generated Sep 14, 2016 6:11:58 PM by Hibernate Tools 5.1.0.Beta1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.training.entity.Music;

/**
 * Home object for domain model class Music.
 * @see .Music
 * @author Hibernate Tools
 */
@Stateless
public class MusicHome {

	private static final Log log = LogFactory.getLog(MusicHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Music transientInstance) {
		log.debug("persisting Music instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Music persistentInstance) {
		log.debug("removing Music instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Music merge(Music detachedInstance) {
		log.debug("merging Music instance");
		try {
			Music result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Music findById(String id) {
		log.debug("getting Music instance with id: " + id);
		try {
			Music instance = entityManager.find(Music.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
