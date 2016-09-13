package com.training.pojos;
// default package
// Generated Sep 13, 2016 5:13:51 PM by Hibernate Tools 5.1.0.Beta1

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ItemFormatId generated by hbm2java
 */
@Embeddable
public class ItemFormatId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String itemId;
	private String itemType;

	public ItemFormatId() {
	}

	public ItemFormatId(String itemId, String itemType) {
		this.itemId = itemId;
		this.itemType = itemType;
	}

	@Column(name = "item_id", nullable = false, length = 8)
	public String getItemId() {
		return this.itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	@Column(name = "item_type", nullable = false, length = 6)
	public String getItemType() {
		return this.itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ItemFormatId))
			return false;
		ItemFormatId castOther = (ItemFormatId) other;

		return ((this.getItemId() == castOther.getItemId()) || (this.getItemId() != null
				&& castOther.getItemId() != null && this.getItemId().equals(castOther.getItemId())))
				&& ((this.getItemType() == castOther.getItemType()) || (this.getItemType() != null
						&& castOther.getItemType() != null && this.getItemType().equals(castOther.getItemType())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getItemId() == null ? 0 : this.getItemId().hashCode());
		result = 37 * result + (getItemType() == null ? 0 : this.getItemType().hashCode());
		return result;
	}

}
