package com.webHook.Entity.Fiserv;

public class Processor {
    public String referenceNumber;
    public String authorizationCode;
    public String responseMessage;
    public String securityCodeResponse;
    public String associationResponseCode;
    public AvsResponse avsResponse;
    public String responseCode;
    public String network;
	@Override
	public String toString() {
		return "Processor [referenceNumber=" + referenceNumber + ", authorizationCode=" + authorizationCode
				+ ", responseMessage=" + responseMessage + ", securityCodeResponse=" + securityCodeResponse
				+ ", associationResponseCode=" + associationResponseCode + ", avsResponse=" + avsResponse
				+ ", responseCode=" + responseCode + ", network=" + network + "]";
	}
    
}
