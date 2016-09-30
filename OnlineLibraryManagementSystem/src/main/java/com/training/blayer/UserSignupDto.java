package com.training.blayer;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 * this class is created to set user sign up details
 * 
 * @author 542224
 *
 */
public class UserSignupDto {

	private String userId;

	@NotBlank(message = "username must not be blank!")
	@Size(min = 4, max = 15, message = "username must be atleast 4 characters long and max of 15 characters")
	private String userName;

	@NotBlank(message = "Role must not be blank!")
	private String role;

	@Min(value = 10, message = "age should be atleast be 10")
	private int age;

	@NotBlank(message = "Gender must not be blank!")
	private String gender;

	@NotBlank(message = "Email should not be null")
	@Email(message = "invalid email address. Example: abcdefgh@****.***")
	private String emailId;

	private int mobileNo;

	private int alternateContactNo;

	@NotBlank(message = "Please enter preferred languages")
	private String languages;

	@NotBlank(message = "Preferred notification should not be empty")
	@Size(min = 3, max = 6, message = "preferred notification should be atlead 3 characters in length and max of 6 characters")
	private String preferredNotify;

	private List<AddressDetailsDto> addressDetails;

	@NotBlank(message = "password must not be blank!")
	@Size(min = 4, max = 15, message = "password must be atleast 4 characters long and max of 15 characters")
	private String password;

	@NotBlank(message = "password must not be blank!")
	@Size(min = 4, max = 15, message = "password must be atleast 4 characters long and max of 15 characters")
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
