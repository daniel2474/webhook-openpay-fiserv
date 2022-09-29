package com.webHook.Entity.Fiserv;

public class PaymentCard {

    public ExpiryDate expiryDate;
    public String last4;
    public String bin;
    public String brand;
	@Override
	public String toString() {
		return "PaymentCard [expiryDate=" + expiryDate + ", last4=" + last4 + ", bin=" + bin + ", brand=" + brand + "]";
	}
    
}
