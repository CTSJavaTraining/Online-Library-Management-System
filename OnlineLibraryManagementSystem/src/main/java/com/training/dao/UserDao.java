/**
 * 
 */
package com.training.dao;

import java.util.List;

import org.hibernate.Session;

import com.training.dto.UserSignupDto;
import com.training.entity.UserDetails;

/**
 * this interface holds abstract methods for class UserDaoImpl in dao.impl
 * package
 * 
 * @author 542224
 *
 */
public interface UserDao {
	/**
	 * this method is used to check whether user details is inserted
	 * successfully into the user details table
	 * 
	 * @param userSignupDto
	 * @return
	 */
	public boolean userSignUp(UserSignupDto userSignupDto);

	/**
	 * this method is used to generate new userId
	 * 
	 * @param userSignupDto
	 * @param lastUserId
	 */

	public void getNewUserID(UserSignupDto userSignupDto, List<String> lastUserId);

	/**
	 * this method is used to set user details including password and address
	 * details
	 * 
	 * @param userSignupDto
	 * @return
	 */

	public UserDetails insertUserDetails(UserSignupDto userSignupDto);

	/**
	 * this method is used to insert address details of user into
	 * address_details table
	 * 
	 * @param userSignupDto
	 * @param userDetails
	 */
	public void insertAddressValues(UserSignupDto userSignupDto, UserDetails userDetails);

	/**
	 * this method used to set login details into login_details table
	 * 
	 * @param userSignupDto
	 * @param userDetails
	 */
	public void updatePassword(UserSignupDto userSignupDto, UserDetails userDetails);

	/**
	 * this method is used to set new user details
	 * 
	 * @param userSignupDto
	 * @return
	 */
	public UserDetails setNewUserDetails(UserSignupDto userSignupDto);

	/**
	 * this method checks whether a user already exists or not when user tries
	 * to sign-up
	 * 
	 * @param username
	 * @return
	 */
	public boolean validateUser(String username);

	/**
	 * this method is used to check whether a userId exists or not when user
	 * tries to login
	 * 
	 * @param userId
	 * @return
	 */
	public boolean validateLoginUser(String userId);

	/**
	 * this method is used to validate the login details entered
	 * 
	 * @param userId
	 * @param password
	 * @return
	 */

	public boolean validateLogin(String userId, String password);

	/**
	 * this method is used to maintain login audit details for users
	 * 
	 * @param userId
	 * @param password
	 * @param session
	 * @param results
	 * @return
	 */
	public boolean updateLoginAudit(String userId, String password, Session session, String results);

}
