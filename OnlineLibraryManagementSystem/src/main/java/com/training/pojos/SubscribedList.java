package com.training.pojos;
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
 * SubscribedList generated by hbm2java
 */
@Entity
@Table(name = "subscribed_list", catalog = "onlinelibrary")
public class SubscribedList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SubscribedListId id;
	private LibraryItems libraryItems;
	private MemberDetails memberDetails;
	private Date startDate;
	private Date endDate;
	private String returnStatus;
	private Date CTime;
	private Date MTime;

	public SubscribedList() {
	}

	public SubscribedList(SubscribedListId id, LibraryItems libraryItems, MemberDetails memberDetails, Date startDate,
			Date endDate, String returnStatus, Date CTime, Date MTime) {
		this.id = id;
		this.libraryItems = libraryItems;
		this.memberDetails = memberDetails;
		this.startDate = startDate;
		this.endDate = endDate;
		this.returnStatus = returnStatus;
		this.CTime = CTime;
		this.MTime = MTime;
	}

	@EmbeddedId

	@AttributeOverrides({ @AttributeOverride(name = "memberId", column = @Column(name = "member_id", nullable = false)),
			@AttributeOverride(name = "itemId", column = @Column(name = "item_id", nullable = false, length = 8)) })
	public SubscribedListId getId() {
		return this.id;
	}

	public void setId(SubscribedListId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_id", nullable = false, insertable = false, updatable = false)
	public LibraryItems getLibraryItems() {
		return this.libraryItems;
	}

	public void setLibraryItems(LibraryItems libraryItems) {
		this.libraryItems = libraryItems;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id", nullable = false, insertable = false, updatable = false)
	public MemberDetails getMemberDetails() {
		return this.memberDetails;
	}

	public void setMemberDetails(MemberDetails memberDetails) {
		this.memberDetails = memberDetails;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "start_date", nullable = false, length = 10)
	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "end_date", nullable = false, length = 10)
	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Column(name = "return_status", nullable = false, length = 10)
	public String getReturnStatus() {
		return this.returnStatus;
	}

	public void setReturnStatus(String returnStatus) {
		this.returnStatus = returnStatus;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "c_time", nullable = false, length = 19)
	public Date getCTime() {
		return this.CTime;
	}

	public void setCTime(Date CTime) {
		this.CTime = CTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "m_time", nullable = false, length = 19)
	public Date getMTime() {
		return this.MTime;
	}

	public void setMTime(Date MTime) {
		this.MTime = MTime;
	}

}