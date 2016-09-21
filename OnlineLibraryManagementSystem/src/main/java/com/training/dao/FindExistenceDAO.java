/**
 * 
 */
package com.training.dao;

/**
 * this class is used to check the availability of a given record
 * 
 * @author 447383
 *
 */
@FunctionalInterface
public interface FindExistenceDAO {

	/**
	 * 
	 * @param userId
	 * @param itemId
	 * @param tableName
	 * @return
	 */
	public boolean findExistance(String userId, String itemId, String tableName);
}
