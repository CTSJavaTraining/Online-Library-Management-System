package com.training.blayer;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * this class is created to set item format and price details
 * 
 * @author 447482
 *
 */
public class ViewItemsDto {

	@NotBlank(message = "itemName must not be blank!")
	private String itemName;

	@NotBlank(message = "price must not be blank!")
	@Min(value = 10, message = "Minimum price should be atleast 10")
	private int price;

	@DateTimeFormat(pattern="YYYY")
	private int year;

	@NotBlank(message = "itemType must not be blank!")
	@Size(min = 2, max = 10, message = "itemType must be atleast 2 characters long and max of 10 characters")
	private String itemType;

	/**
	 * @return the itemName
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * @param itemName
	 *            the itemName to set
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * @param year
	 *            the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * @return the itemType
	 */
	public String getItemType() {
		return itemType;
	}

	/**
	 * @param itemType
	 *            the itemType to set
	 */
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

}
