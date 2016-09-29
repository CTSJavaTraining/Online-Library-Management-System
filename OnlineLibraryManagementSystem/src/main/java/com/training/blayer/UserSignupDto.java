package com.training.blayer;

import java.util.List;

/**
 * this class is created to set user sign up details
 * 
 * @author 542224
 *
 */
public class UserSignupDto {

	private String userId;

	private String userName;

	private String role;

	private int age;

	private String gender;

	private String emailId;

	private int mobileNo;

	private int alternateContactNo;

	private String languages;

	private String preferredNotify;

	private List<AddressDetailsDto> addressDetails;

	private String password;

	private String confirmPassword;

	/**
	 * 
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * 
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * 
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * 
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * 
	 * @param role
	 *            the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * 
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * 
	 * @param age
	 *            the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * 
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * 
	 * @param gender
	 *            the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * 
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * 
	 * @param emailId
	 *            the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * 
	 * @return the mobileNo
	 */
	public int getMobileNo() {
		return mobileNo;
	}

	/**
	 * 
	 * @param mobileNo
	 *            the mobileNo to set
	 */
	public void setMobileNo(int mobileNo) {
		this.mobileNo = mobileNo;
	}

	/**
	 * 
	 * @return the alternateContactNo
	 */
	public int getAlternateContactNo() {
		return alternateContactNo;
	}

	/**
	 * 
	 * @param alternateContactNo
	 *            the alternateContactNo to set
	 */
	public void setAlternateContactNo(int alternateContactNo) {
		this.alternateContactNo = alternateContactNo;
	}

	/**
	 * 
	 * @return the languages
	 */
	public String getLanguages() {
		return languages;
	}

	/**
	 * 
	 * @param languages
	 *            the languages to set
	 */
	public void setLanguages(String languages) {
		this.languages = languages;
	}

	/**
	 * 
	 * @return the preferredNotify
	 */
	public String getPreferredNotify() {
		return preferredNotify;
	}

	/**
	 * 
	 * @param preferredNotify
	 *            the preferredNotify to set
	 */
	public void setPreferredNotify(String preferredNotify) {
		this.preferredNotify = preferredNotify;
	}

	/**
	 * 
	 * @return the addressDetails
	 */
	public List<AddressDetailsDto> getAddressDetails() {
		return addressDetails;
	}

	/**
	 * 
	 * @param addressDetails
	 *            the addressDetails to set
	 */
	public void setAddressDetails(List<AddressDetailsDto> addressDetails) {
		this.addressDetails = addressDetails;
	}

	/**
	 * 
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 
	 * @return confirmPassword
	 */
	public String getConfirmPassword() {
		return confirmPassword;
	}

	/**
	 * 
	 * @param confirmPassword
	 *            the confirmed password to set
	 */
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

}
