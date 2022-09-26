package com.webHook.Entity;

public class PaymentMethod{
    private String reference;
    
    private String barcode_url;
    
    private String type;
    
	public String getReference() {
		return reference;
	}
	
	public void setReference(String reference) {
		this.reference = reference;
	}
	
	public String getBarcode_url() {
		return barcode_url;
	}
	
	public void setBarcode_url(String barcode_url) {
		this.barcode_url = barcode_url;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "PaymentMethod [reference=" + reference + ", barcode_url=" + barcode_url + ", type=" + type + "]";
	}
    
}