package com.training.entity;
// default package

// Generated Sep 13, 2016 5:13:51 PM by Hibernate Tools 5.1.0.Beta1

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * WishList class is an entity model of wish_list table in database
 */
@Entity
@Table(name = "wish_list")
public class WishList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId

	@AttributeOverrides({ @AttributeOverride(name = "memberId", column = @Column(name = "member_id", nullable = false)),
			@AttributeOverride(name = "itemId", column = @Column(name = "item_id", nullable = false, length = 8)) })
	private WishListId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_id", nullable = false, insertable = false, updatable = false)
	private LibraryItems libraryItems;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id", nullable = false, insertable = false, updatable = false)
	private MemberDetails memberDetails;

	@Column(name = "wish_status", nullable = false)
	private int wishStatus;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "c_time", nullable = false, length = 19)
	private Date createdTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "m_time", nullable = false, length = 19)
	private Date modifiedTime;

	public WishListId getId() {
		return this.id;
	}

	public void setId(WishListId id) {
		this.id = id;
	}

	public LibraryItems getLibraryItems() {
		return this.libraryItems;
	}

	public void setLibraryItems(LibraryItems libraryItems) {
		this.libraryItems = libraryItems;
	}

	public MemberDetails getMemberDetails() {
		return this.memberDetails;
	}

	public void setMemberDetails(MemberDetails memberDetails) {
		this.memberDetails = memberDetails;
	}

	public int getWishStatus() {
		return this.wishStatus;
	}

	public void setWishStatus(int wishStatus) {
		this.wishStatus = wishStatus;
	}

	/**
	 * 
	 * @return the createdTime
	 */
	public Date getcreatedTime() {
		return this.createdTime;
	}

	/**
	 * the created time to be set
	 * 
	 * @param createdTime
	 */
	public void setcreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	/**
	 * 
	 * @return the modifiedTime
	 */
	public Date getmodifiedTime() {
		return this.modifiedTime;
	}

	/**
	 * the modifiedTime to be set
	 * 
	 * @param modifiedTime
	 */
	public void setmodifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

}
