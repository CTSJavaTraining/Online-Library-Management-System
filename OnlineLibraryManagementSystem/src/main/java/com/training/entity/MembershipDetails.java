 	package com.training.entity;
// default package

// Generated Sep 13, 2016 5:13:51 PM by Hibernate Tools 5.1.0.Beta1

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * MembershipDetails class is an entity model of membership_details table in
 * database
 */
@Entity
@Table(name = "membership_details")
public class MembershipDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "member_type_id", nullable = false)
	private int memberTypeId;

	@Column(name = "membership_type", nullable = false, unique = true, length = 6)
	private String membershipType;

	@Column(name = "max_price_limit", nullable = false)
	private int maxPriceLimit;

	@Column(name = "validity_days", nullable = false)
	private int validityDays;

	@Column(name = "membership_cost", nullable = false)
	private int membershipCost;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "c_time", nullable = false, length = 19)
	private Date createdTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "m_time", nullable = false, length = 19)
	private Date modifiedTime;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "membershipDetails")
	private Set<MemberDetails> memberDetailses;

	public int getMaxPriceLimit() {
		return this.maxPriceLimit;
	}

	public void setMaxPriceLimit(int maxPriceLimit) {
		this.maxPriceLimit = maxPriceLimit;
	}

	public int getValidityDays() {
		return this.validityDays;
	}

	public void setValidityDays(int validityDays) {
		this.validityDays = validityDays;
	}

	public int getMembershipCost() {
		return this.membershipCost;
	}

	public void setMembershipCost(int membershipCost) {
		this.membershipCost = membershipCost;
	}

	/**
	 * 
	 * @return the createdTime
	 */
	public Date getcreatedTime() {
		return this.createdTime;
	}

	/**
	 * the createdTime to be set
	 * 
	 * @param createdTime
	 */
	public void setcreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	/**
	 * 
	 * @return the modifiedTime
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

	public Set<MemberDetails> getMemberDetailses() {
		return this.memberDetailses;
	}

	public void setMemberDetailses(Set<MemberDetails> memberDetailses) {
		this.memberDetailses = memberDetailses;
	}

}
