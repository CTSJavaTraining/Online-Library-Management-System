package com.training.entity;
// default package

// Generated Sep 13, 2016 5:13:51 PM by Hibernate Tools 5.1.0.Beta1

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * UserDetails generated by hbm2java
 */
@Entity
@Table(name = "user_details")
public class UserDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "user_id", unique = true, nullable = false, length = 6)
	private String userId;

	@Column(name = "user_name", nullable = false, length = 20)
	private String userName;

	@Column(name = "role", nullable = false, length = 1)
	private String role;

	@Column(name = "age", nullable = false)
	private int age;

	@Column(name = "gender", nullable = false, length = 1)
	private String gender;

	@Column(name = "languages", nullable = false, length = 100)
	private String languages;

	@Column(name = "preferred_notify", nullable = false, length = 5)
	private String preferredNotify;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "c_time", nullable = false, length = 19)
	private Date createdTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "m_time", nullable = false, length = 19)
	private Date modifiedTime;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "userDetails")
	private List<UserContactDetails> userContactDetails;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userDetails")
	private Set<UserDetails> likedLists = new HashSet<>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userDetails")
	private Set<UserDetails> ratingTables = new HashSet<>();

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "userDetails")
	private List<LoginDetails> loginDetails;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userDetails")
	private Set<UserDetails> memberDetailses = new HashSet<>();

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getLanguages() {
		return this.languages;
	}

	public void setLanguages(String languages) {
		this.languages = languages;
	}

	public String getPreferredNotify() {
		return this.preferredNotify;
	}

	public void setPreferredNotify(String preferredNotify) {
		this.preferredNotify = preferredNotify;
	}

	public Date getcreatedTime() {
		return this.createdTime;
	}

	public void setcreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getmodifiedTime() {
		return this.modifiedTime;
	}

	public void setmodifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public List<UserContactDetails> getUserContactDetails() {
		return this.userContactDetails;
	}

	public void setUserContactDetails(List<UserContactDetails> userContactDetails) {
		this.userContactDetails = userContactDetails;
	}

	public Set<?> getLikedLists() {
		return this.likedLists;
	}

	@SuppressWarnings("unchecked")
	public void setLikedLists(Set<?> likedLists) {
		this.likedLists = (Set<UserDetails>) likedLists;
	}

	public Set<?> getRatingTables() {
		return this.ratingTables;
	}

	@SuppressWarnings("unchecked")
	public void setRatingTables(Set<?> ratingTables) {
		this.ratingTables = (Set<UserDetails>) ratingTables;
	}

	public List<LoginDetails> getLoginDetails() {
		return this.loginDetails;
	}

	public void setLoginDetails(List<LoginDetails> loginDetails) {
		this.loginDetails = loginDetails;
	}

	public Set<?> getMemberDetailses() {
		return this.memberDetailses;
	}

	@SuppressWarnings("unchecked")
	public void setMemberDetailses(Set<?> memberDetailses) {
		this.memberDetailses = (Set<UserDetails>) memberDetailses;
	}

}
