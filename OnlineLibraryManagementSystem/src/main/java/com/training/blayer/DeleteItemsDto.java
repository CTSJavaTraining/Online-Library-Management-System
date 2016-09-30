package com.training.blayer;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

/**
 * This class is used for converting into JSON object using FasterXML Jackson
 * for performing delete operation
 * 
 * @author 447482
 *
 */
public class DeleteItemsDto {

	@NotBlank(message = "itemName must not be blank!")
	@Size(min = 2, max = 20, message = "itemName must be atleast 2 characters long and max of 20 characters")
	private String itemName;

	@NotBlank(message = "itemType must not be blank!")
	@Size(min = 2, max = 10, message = "itemType must be atleast 2 characters long and max of 10 characters")
	private String itemType;

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

}
