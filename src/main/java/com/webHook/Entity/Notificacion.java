package com.webHook.Entity;

import java.util.Date;

public class Notificacion {
	
	private String verification_code;
	private Date event_date;
	private String type;
	private Transaction transaction;
	public Date getEvent_date() {
		return event_date;
	}
	public void setEvent_date(Date event_date) {
		this.event_date = event_date;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Transaction getTransaction() {
		return transaction;
	}
	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
	@Override
	public String toString() {
		return "Notificacion [event_date=" + event_date + ", type=" + type + ", transaction=" + transaction + "]";
	}
	public String getVerification_code() {
		return verification_code;
	}
	public void setVerification_code(String verification_code) {
		this.verification_code = verification_code;
	}
	
	
}
 
 
