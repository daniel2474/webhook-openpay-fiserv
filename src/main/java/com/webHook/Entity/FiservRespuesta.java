package com.webHook.Entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

 @Entity
 @Table(name = "configuracion_fiserv")
public class FiservRespuesta {
	@Id //Define la llave primaria.
    @GeneratedValue(generator = "UUID") //Se estableció un tipo de variable UUID 
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false) //Permite establecer el nombre de la columna de la tabla con la que el atributo debe de mapear.
    private UUID id;

    @Column(name = "hosteddataid") 
    private String hosteddataid;

    @Column(name = "txndate_processed")
    private String txndate_processed;
    
    @Column(name = "ccbin")
    private String ccbin;
    
    @Column(name = "timezone")    
    private String timezone;
    
    @Column(name = "processor_network_information")    
    private String processor_network_information;

    @Column(name = "oid")  
    private String oid;

    @Column(name = "cccountry")  
    private String cccountry;

    @Column(name = "expmonth")  
    private String expmonth;
    
    @Column(name = "hash_algorithm")  
    private String hash_algorithm;

    @Column(name = "endpoint_transaction_id")  
    private String endpointTransactionId;

    @Column(name = "currency")  
    private String currency;

    @Column(name = "processor_response_code")  
    private String processor_response_code;

    @Column(name = "chargetotal")  
    private String chargetotal;

    @Column(name = "terminal_id")  
    private String terminal_id;

    @Column(name = "association_response_code")  
    private String associationResponseCode;

    @Column(name = "approval_code")
    private String approval_code;

    @Column(name = "id_cliente")
    private String id_cliente;

    @Column(name = "expyear")
    private String expyear;

    @Column(name = "ov")
    private String ov;

    @Column(name = "response_hash")
    private String response_hash;

    @Column(name = "scheme_transaction_id")
    private String schemeTransactionId;

    @Column(name = "tdate")
    private String tdate;

    @Column(name = "installments_interest")
    private String installments_interest;

    @Column(name = "bname")
    private String bname;

    @Column(name = "ccbrand")
    private String ccbrand;

    @Column(name = "refnumber")
    private String refnumber;

    @Column(name = "txntype")
    private String txntype;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "txndatetime")
    private String txndatetime;

    @Column(name = "cardnumber")
    private String cardnumber;

    @Column(name = "ipg_transaction_id")
    private String ipgTransactionId;

    @Column(name = "status")
    private String status;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getHosteddataid() {
		return hosteddataid;
	}

	public void setHosteddataid(String hosteddataid) {
		this.hosteddataid = hosteddataid;
	}

	public String getTxndate_processed() {
		return txndate_processed;
	}

	public void setTxndate_processed(String txndate_processed) {
		this.txndate_processed = txndate_processed;
	}

	public String getCcbin() {
		return ccbin;
	}

	public void setCcbin(String ccbin) {
		this.ccbin = ccbin;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public String getProcessor_network_information() {
		return processor_network_information;
	}

	public void setProcessor_network_information(String processor_network_information) {
		this.processor_network_information = processor_network_information;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getCccountry() {
		return cccountry;
	}

	public void setCccountry(String cccountry) {
		this.cccountry = cccountry;
	}

	public String getExpmonth() {
		return expmonth;
	}

	public void setExpmonth(String expmonth) {
		this.expmonth = expmonth;
	}

	public String getHash_algorithm() {
		return hash_algorithm;
	}

	public void setHash_algorithm(String hash_algorithm) {
		this.hash_algorithm = hash_algorithm;
	}

	public String getEndpointTransactionId() {
		return endpointTransactionId;
	}

	public void setEndpointTransactionId(String endpointTransactionId) {
		this.endpointTransactionId = endpointTransactionId;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getProcessor_response_code() {
		return processor_response_code;
	}

	public void setProcessor_response_code(String processor_response_code) {
		this.processor_response_code = processor_response_code;
	}

	public String getChargetotal() {
		return chargetotal;
	}

	public void setChargetotal(String chargetotal) {
		this.chargetotal = chargetotal;
	}

	public String getTerminal_id() {
		return terminal_id;
	}

	public void setTerminal_id(String terminal_id) {
		this.terminal_id = terminal_id;
	}

	public String getAssociationResponseCode() {
		return associationResponseCode;
	}

	public void setAssociationResponseCode(String associationResponseCode) {
		this.associationResponseCode = associationResponseCode;
	}

	public String getApproval_code() {
		return approval_code;
	}

	public void setApproval_code(String approval_code) {
		this.approval_code = approval_code;
	}

	public String getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(String id_cliente) {
		this.id_cliente = id_cliente;
	}

	public String getExpyear() {
		return expyear;
	}

	public void setExpyear(String expyear) {
		this.expyear = expyear;
	}

	public String getOv() {
		return ov;
	}

	public void setOv(String ov) {
		this.ov = ov;
	}

	public String getResponse_hash() {
		return response_hash;
	}

	public void setResponse_hash(String response_hash) {
		this.response_hash = response_hash;
	}

	public String getSchemeTransactionId() {
		return schemeTransactionId;
	}

	public void setSchemeTransactionId(String schemeTransactionId) {
		this.schemeTransactionId = schemeTransactionId;
	}

	public String getTdate() {
		return tdate;
	}

	public void setTdate(String tdate) {
		this.tdate = tdate;
	}

	public String getInstallments_interest() {
		return installments_interest;
	}

	public void setInstallments_interest(String installments_interest) {
		this.installments_interest = installments_interest;
	}

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	public String getCcbrand() {
		return ccbrand;
	}

	public void setCcbrand(String ccbrand) {
		this.ccbrand = ccbrand;
	}

	public String getRefnumber() {
		return refnumber;
	}

	public void setRefnumber(String refnumber) {
		this.refnumber = refnumber;
	}

	public String getTxntype() {
		return txntype;
	}

	public void setTxntype(String txntype) {
		this.txntype = txntype;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getTxndatetime() {
		return txndatetime;
	}

	public void setTxndatetime(String txndatetime) {
		this.txndatetime = txndatetime;
	}

	public String getCardnumber() {
		return cardnumber;
	}

	public void setCardnumber(String cardnumber) {
		this.cardnumber = cardnumber;
	} 

	public String getIpgTransactionId() {
		return ipgTransactionId;
	}

	public void setIpgTransactionId(String ipgTransactionId) {
		this.ipgTransactionId = ipgTransactionId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "FiservRespuesta [id=" + id + ", hosteddataid=" + hosteddataid + ", txndate_processed="
				+ txndate_processed + ", ccbin=" + ccbin + ", timezone=" + timezone + ", processor_network_information="
				+ processor_network_information + ", oid=" + oid + ", cccountry=" + cccountry + ", expmonth=" + expmonth
				+ ", hash_algorithm=" + hash_algorithm + ", endpointTransactionId=" + endpointTransactionId
				+ ", currency=" + currency + ", processor_response_code=" + processor_response_code + ", chargetotal="
				+ chargetotal + ", terminal_id=" + terminal_id + ", associationResponseCode=" + associationResponseCode
				+ ", approval_code=" + approval_code + ", id_cliente=" + id_cliente + ", expyear=" + expyear + ", ov="
				+ ov + ", response_hash=" + response_hash + ", schemeTransactionId=" + schemeTransactionId + ", tdate="
				+ tdate + ", installments_interest=" + installments_interest + ", bname=" + bname + ", ccbrand="
				+ ccbrand + ", refnumber=" + refnumber + ", txntype=" + txntype + ", paymentMethod=" + paymentMethod
				+ ", txndatetime=" + txndatetime + ", cardnumber=" + cardnumber + ", ipgTransactionId="
				+ ipgTransactionId + ", status=" + status + "]";
	}
    
    
}
