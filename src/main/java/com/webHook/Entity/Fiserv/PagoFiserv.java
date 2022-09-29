package com.webHook.Entity.Fiserv;

public class PagoFiserv {
    public String country;
    public String approvalCode;
    public String orderId;
    public String transactionStatus;
    public String clientRequestId;
    public String terminalId;
    public long transactionTime;
    public String transactionOrigin;
    public Processor processor;
    public String transactionType;
    public PaymentToken paymentToken;
    public String merchantId;
    public PaymentMethodDetails paymentMethodDetails;
    public ApprovedAmount approvedAmount;
    public String ipgTransactionId;
    public String apiTraceId;
	@Override
	public String toString() {
		return "PagoFiserv [country=" + country + ", approvalCode=" + approvalCode + ", orderId=" + orderId
				+ ", transactionStatus=" + transactionStatus + ", clientRequestId=" + clientRequestId + ", terminalId="
				+ terminalId + ", transactionTime=" + transactionTime + ", transactionOrigin=" + transactionOrigin
				+ ", processor=" + processor + ", transactionType=" + transactionType + ", paymentToken=" + paymentToken
				+ ", merchantId=" + merchantId + ", paymentMethodDetails=" + paymentMethodDetails + ", approvedAmount="
				+ approvedAmount + ", ipgTransactionId=" + ipgTransactionId + ", apiTraceId=" + apiTraceId + "]";
	}
    
}



