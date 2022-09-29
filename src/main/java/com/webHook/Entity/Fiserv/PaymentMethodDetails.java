package com.webHook.Entity.Fiserv;

public class PaymentMethodDetails {

    public PaymentCard paymentCard;
    public String paymentMethodType;
	@Override
	public String toString() {
		return "PaymentMethodDetails [paymentCard=" + paymentCard + ", paymentMethodType=" + paymentMethodType + "]";
	}
    
}
