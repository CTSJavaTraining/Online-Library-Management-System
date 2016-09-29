package com.training.entity;
// default package

// Generated Sep 13, 2016 5:13:51 PM by Hibernate Tools 5.1.0.Beta1

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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
 * UserDetails class is an entity model of user_details table in database
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

	@Column(name = "email_id", unique = true, nullable = false, length = 35)
	private String emailId;

	@Column(name = "mobile_no", unique = true, nullable = false)
	private int mobileNo;

	@Column(name = "alternate_contact_no")
	private Integer alternateContactNo;

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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userDetails")
	private List<AddressDetails> addressDetails;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userDetails")
	private LoginDetails loginDetails;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userDetails")
	private List<LikedList> likedLists;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userDetails")
	private List<RatingTable> ratingTables;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userDetails")
	private List<MemberDetails> memberDetailses;

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName.trim();
	}

	public void setUserName(String userName) {
		this.userName = userName.trim().toLowerCase();
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

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public int getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(int mobileNo) {
		this.mobileNo = mobileNo;
	}

	public Integer getAlternateContactNo() {
		return alternateContactNo;
	}

	public void setAlternateContactNo(Integer alternateContactNo) {
		this.alternateContactNo = alternateContactNo;
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

	/**
	 * 
	 * @return To get the record created time
	 */
	public Date getcreatedTime() {
		return this.createdTime;
	}

	/**
	 * To insert record created time into DB
	 * 
	 * @param createdTime
	 */
	public void setcreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	/**
	 * 
	 * @return To get the modified time
	 */
	public Date getmodifiedTime() {
		return this.modifiedTime;
	}

	/**
	 * the modifiedTime to be set
	 * 
	 * @param modifiedTime
	 */
	public void setmodifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public List<AddressDetails> getAddressDetails() {
		return addressDetails;
	}

	public void setAddressDetails(List<AddressDetails> addressDetails) {
		this.addressDetails = addressDetails;
	}

	public List<LikedList> getLikedLists() {
		return this.likedLists;
	}

	public void setLikedLists(List<LikedList> likedLists) {
		this.likedLists = likedLists;
	}

	public List<RatingTable> getRatingTables() {
		return this.ratingTables;
	}

	public void setRatingTables(List<RatingTable> ratingTables) {
		this.ratingTables = ratingTables;
	}

	public LoginDetails getLoginDetails() {
		return this.loginDetails;
	}

	public void setLoginDetails(LoginDetails loginDetails) {
		this.loginDetails = loginDetails;
	}

	public List<MemberDetails> getMemberDetailses() {
		return this.memberDetailses;
	}

	public void setMemberDetailses(List<MemberDetails> memberDetailses) {
		this.memberDetailses = memberDetailses;
	}

}
