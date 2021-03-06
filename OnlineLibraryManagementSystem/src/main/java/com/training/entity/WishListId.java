package com.training.entity;
// default package

// Generated Sep 13, 2016 5:13:51 PM by Hibernate Tools 5.1.0.Beta1

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * WishListId
 */
@Embeddable
public class WishListId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "member_id", nullable = false)
	private int memberId;

	@Column(name = "item_id", nullable = false, length = 8)
	private String itemId;

	public int getMemberId() {
		return this.memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getItemId() {
		return this.itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other)
			return true;
		if (other == null)
			return false;
		if (!(other instanceof WishListId))
			return false;
		WishListId castOther = (WishListId) other;
		
		return (this.getMemberId() == castOther.getMemberId())
				&& ((this.getItemId() == castOther.getItemId()) || (this.getItemId() != null
						&& castOther.getItemId() != null && this.getItemId().equals(castOther.getItemId())));
	}

	@Override
	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getMemberId();
		result = 37 * result + (getItemId() == null ? 0 : this.getItemId().hashCode());
		return result;
	}

}
