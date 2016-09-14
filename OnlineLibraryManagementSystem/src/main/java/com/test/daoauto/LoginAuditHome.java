package com.test.daoauto;
// default package
// Generated Sep 14, 2016 6:11:58 PM by Hibernate Tools 5.1.0.Beta1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.training.entity.LoginAudit;
import com.training.entity.LoginAuditId;

/**
 * Home object for domain model class LoginAudit.
 * @see .LoginAudit
 * @author Hibernate Tools
 */
@Stateless
public class LoginAuditHome {

	private static final Log log = LogFactory.getLog(LoginAuditHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(LoginAudit transientInstance) {
		log.debug("persisting LoginAudit instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(LoginAudit persistentInstance) {
		log.debug("removing LoginAudit instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public LoginAudit merge(LoginAudit detachedInstance) {
		log.debug("merging LoginAudit instance");
		try {
			LoginAudit result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public LoginAudit findById(LoginAuditId id) {
		log.debug("getting LoginAudit instance with id: " + id);
		try {
			LoginAudit instance = entityManager.find(LoginAudit.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
