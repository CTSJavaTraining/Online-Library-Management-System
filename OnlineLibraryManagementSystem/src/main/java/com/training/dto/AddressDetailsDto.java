package com.training.dto;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Dto class created to set the address details of a user
 * 
 * @author 542224
 *
 */
public class AddressDetailsDto {

	@NotBlank(message = "doorNo must not be blank!")
	@Min(value = 1, message = "doorNo not valid!")
	private int doorNo;

	private String streetName;

	@NotBlank(message = "pinCode must not be blank!")
	@Min(value = 1000, message = "pinCode is not valid!")
	private int pinCode;

	@NotBlank(message = "city must not be blank!")
	private String city;

	@NotBlank(message = "state must not be blank!")
	private String state;

	@NotBlank(message = "country must not be blank!")
	private String country;

	public int getDoorNo() {
		return doorNo;
	}

	public void setDoorNo(int doorNo) {
		this.doorNo = doorNo;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public int getPinCode() {
		return pinCode;
	}

	public void setPinCode(int pinCode) {
		this.pinCode = pinCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
