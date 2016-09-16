package com.training.entity;
// default package

// Generated Sep 13, 2016 5:13:51 PM by Hibernate Tools 5.1.0.Beta1

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * AddressDetails generated by hbm2java
 */
@Entity
@Table(name = "address_details")
public class AddressDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "userId", column = @Column(name = "user_id", nullable = false, length = 6)),
			@AttributeOverride(name = "doorno", column = @Column(name = "doorno", nullable = false)) })
	private AddressDetailsId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false, insertable = false, updatable = false)
	private UserContactDetails userContactDetails;

	@Column(name = "street_name", length = 50)
	private String streetName;

	@Column(name = "pin_code", nullable = false)
	private int pinCode;

	@Column(name = "city", nullable = false, length = 20)
	private String city;

	@Column(name = "state", nullable = false, length = 20)
	private String state;

	@Column(name = "country", nullable = false, length = 20)
	private String country;

	public AddressDetailsId getId() {
		return this.id;
	}

	public void setId(AddressDetailsId id) {
		this.id = id;
	}

	public UserContactDetails getUserContactDetails() {
		return this.userContactDetails;
	}

	public void setUserContactDetails(UserContactDetails userContactDetails) {
		this.userContactDetails = userContactDetails;
	}

	public String getStreetName() {
		return this.streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public int getPinCode() {
		return this.pinCode;
	}

	public void setPinCode(int pinCode) {
		this.pinCode = pinCode;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
