package net.emt.springboot.exception;

import java.util.Date;

public class ErrorDetails {
	private Date timestap;
	private String message;
	private String details;
	
	public ErrorDetails(Date timestap, String message, String details) {
		super();
		this.timestap = timestap;
		this.message = message;
		this.details = details;
	}
	public Date getTimestap() {
		return timestap;
	}
	public void setTimestap(Date timestap) {
		this.timestap = timestap;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	
}
