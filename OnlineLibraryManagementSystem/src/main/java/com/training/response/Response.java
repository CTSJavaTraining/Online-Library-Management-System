package com.training.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * @author
 * @param <T>
 */
@JsonInclude(value = Include.NON_EMPTY)
public class Response<T> {

	Status status;
	T data;
	Pagination pageDetails;
	String message;
	Warning warning;
	ErrorBean error;

	/**
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	public T getData() {
		return data;
	}

	public Pagination getPageDetails() {
		return pageDetails;
	}

	public String getMessage() {
		return message;
	}

	public Warning getWarning() {
		return warning;
	}

	public ErrorBean getError() {
		return error;
	}

	public void setData(T data) {
		this.data = data;
	}

	public void setPageDetails(Pagination pageDetails) {
		this.pageDetails = pageDetails;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setWarning(Warning warning) {
		this.warning = warning;
	}

	public void setError(ErrorBean error) {
		this.error = error;
	}

	public void setFailure(ErrorBean error) {
		this.error = error;
	}

	public void setSuccess(T data) {
		this.data = data;
	}

}