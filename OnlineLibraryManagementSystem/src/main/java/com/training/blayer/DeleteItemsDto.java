package com.training.blayer;

/**
 * This class is used for converting into JSON object using FasterXML Jackson
 * for performing delete operation
 * 
 * @author 447482
 *
 */
public class DeleteItemsDto {

	private String itemName;
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
