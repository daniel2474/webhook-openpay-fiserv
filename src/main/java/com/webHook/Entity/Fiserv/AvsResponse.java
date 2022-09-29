package com.webHook.Entity.Fiserv;

public class AvsResponse {

    public String streetMatch;
    public String postalCodeMatch;
	@Override
	public String toString() {
		return "AvsResponse [streetMatch=" + streetMatch + ", postalCodeMatch=" + postalCodeMatch + "]";
	}
    
}
