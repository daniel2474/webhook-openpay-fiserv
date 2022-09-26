package com.webHook.Entity;

public class Fee{
    private double amount;
    
    private double tax;
    
    private String currency;
    
	public double getAmount() {
		return amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public double getTax() {
		return tax;
	}
	
	public void setTax(double tax) {
		this.tax = tax;
	}
	
	public String getCurrency() {
		return currency;
	}
	
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	@Override
	public String toString() {
		return "Fee [amount=" + amount + ", tax=" + tax + ", currency=" + currency + "]";
	}
	 
}

