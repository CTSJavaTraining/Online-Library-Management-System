package com.test.daoauto;
// default package
// Generated Sep 14, 2016 6:11:58 PM by Hibernate Tools 5.1.0.Beta1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.training.entity.ItemFormat;
import com.training.entity.ItemFormatId;

/**
 * Home object for domain model class ItemFormat.
 * @see .ItemFormat
 * @author Hibernate Tools
 */
@Stateless
public class ItemFormatHome {

	private static final Log log = LogFactory.getLog(ItemFormatHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(ItemFormat transientInstance) {
		log.debug("persisting ItemFormat instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(ItemFormat persistentInstance) {
		log.debug("removing ItemFormat instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public ItemFormat merge(ItemFormat detachedInstance) {
		log.debug("merging ItemFormat instance");
		try {
			ItemFormat result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public ItemFormat findById(ItemFormatId id) {
		log.debug("getting ItemFormat instance with id: " + id);
		try {
			ItemFormat instance = entityManager.find(ItemFormat.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
