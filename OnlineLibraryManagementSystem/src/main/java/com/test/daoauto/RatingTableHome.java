package com.test.daoauto;
// default package
// Generated Sep 14, 2016 6:11:58 PM by Hibernate Tools 5.1.0.Beta1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.training.entity.RatingTable;
import com.training.entity.RatingTableId;

/**
 * Home object for domain model class RatingTable.
 * @see .RatingTable
 * @author Hibernate Tools
 */
@Stateless
public class RatingTableHome {

	private static final Log log = LogFactory.getLog(RatingTableHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(RatingTable transientInstance) {
		log.debug("persisting RatingTable instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(RatingTable persistentInstance) {
		log.debug("removing RatingTable instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public RatingTable merge(RatingTable detachedInstance) {
		log.debug("merging RatingTable instance");
		try {
			RatingTable result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public RatingTable findById(RatingTableId id) {
		log.debug("getting RatingTable instance with id: " + id);
		try {
			RatingTable instance = entityManager.find(RatingTable.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
