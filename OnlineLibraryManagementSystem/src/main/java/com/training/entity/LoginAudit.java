package com.training.entity;
// default package

// Generated Sep 13, 2016 5:13:51 PM by Hibernate Tools 5.1.0.Beta1

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * LoginAudit class is an entity model of login_audit table in database
 */
@Entity
@Table(name = "login_audit")
public class LoginAudit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "userId", column = @Column(name = "user_id", nullable = false, length = 6)),
			@AttributeOverride(name = "lastLoginTime", column = @Column(name = "last_login_time", nullable = false, length = 19)) })
	private LoginAuditId id;

	@Column(name = "login_status", nullable = false, length = 1)
	private char loginStatus;

	public LoginAuditId getId() {
		return this.id;
	}

	public void setId(LoginAuditId id) {
		this.id = id;
	}

	public char getLoginStatus() {
		return this.loginStatus;
	}

	public void setLoginStatus(char loginStatus) {
		this.loginStatus = loginStatus;
	}

}
