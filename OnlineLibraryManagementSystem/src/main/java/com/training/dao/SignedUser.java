package com.training.dao;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.training.entity.LikedList;
import com.training.entity.RatingTable;

/**
 * this class handles the signed user functionalities such as keeping track of
 * likes and ratings and also creating and updating the details of the users
 * 
 * @author 447383
 *
 */
public class SignedUser extends AnonymousUser {

	/**
	 * 
	 */
	public void rateItems(){
		
	}
	
	/**
	 * 
	 */
	public void likeItems(){
		
	}
	
	/**
	 * 
	 * @return
	 */
	public List<LikedList> displayLikedDetails(){
		List<LikedList> listResult = Collections.emptyList();
		return listResult;
	}
	
	public List<RatingTable> displayRatingDetails(){
		List<RatingTable> listResult = Collections.emptyList();
		return listResult;
	}
}
