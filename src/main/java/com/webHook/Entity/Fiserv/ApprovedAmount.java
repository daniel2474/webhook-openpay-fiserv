package com.webHook.Entity.Fiserv;

public class ApprovedAmount{
    public float total;
    public Components components;
    public String currency;
	@Override
	public String toString() {
		return "ApprovedAmount [total=" + total + ", components=" + components + ", currency=" + currency + "]";
	}
    
}
