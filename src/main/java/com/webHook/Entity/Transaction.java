package com.webHook.Entity;

import java.util.Date;

public class Transaction{
    private String error_message;
    private int amount;
    private boolean conciliated;
    private String operation_type;
    private String method;
    private Fee fee;
    private Date operation_date;
    private String description;
    private Date creation_date;
    private String transaction_type;
    private String authorization;
    private String currency;
    private String id;
    private String order_id;
    private PaymentMethod payment_method;
    private String status;
    private Customer customer;
	public String getError_message() {
		return error_message;
	}
	public void setError_message(String error_message) {
		this.error_message = error_message;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public boolean isConciliated() {
		return conciliated;
	}
	public void setConciliated(boolean conciliated) {
		this.conciliated = conciliated;
	}
	public String getOperation_type() {
		return operation_type;
	}
	public void setOperation_type(String operation_type) {
		this.operation_type = operation_type;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public Fee getFee() {
		return fee;
	}
	public void setFee(Fee fee) {
		this.fee = fee;
	}
	public Date getOperation_date() {
		return operation_date;
	}
	public void setOperation_date(Date operation_date) {
		this.operation_date = operation_date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}
	public String getTransaction_type() {
		return transaction_type;
	}
	public void setTransaction_type(String transaction_type) {
		this.transaction_type = transaction_type;
	}
	public String getAuthorization() {
		return authorization;
	}
	public void setAuthorization(String authorization) {
		this.authorization = authorization;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public PaymentMethod getPayment_method() {
		return payment_method;
	}
	public void setPayment_method(PaymentMethod payment_method) {
		this.payment_method = payment_method;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	@Override
	public String toString() {
		return "Transaction [error_message=" + error_message + ", amount=" + amount + ", conciliated=" + conciliated
				+ ", operation_type=" + operation_type + ", method=" + method + ", fee=" + fee + ", operation_date="
				+ operation_date + ", description=" + description + ", creation_date=" + creation_date
				+ ", transaction_type=" + transaction_type + ", authorization=" + authorization + ", currency="
				+ currency + ", id=" + id + ", order_id=" + order_id + ", payment_method=" + payment_method
				+ ", status=" + status + ", customer=" + customer + "]";
	}
    
    
}