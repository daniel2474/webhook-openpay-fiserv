package com.webHook.Entity;

import java.util.Date;

public class Customer{
    private String address;
    private String name;
    private String last_name;
    private String phone_number;
    private String external_id;
    private Date creation_date;
    private String email;
    private String clabe;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public String getExternal_id() {
		return external_id;
	}
	public void setExternal_id(String external_id) {
		this.external_id = external_id;
	}
	public Date getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getClabe() {
		return clabe;
	}
	public void setClabe(String clabe) {
		this.clabe = clabe;
	}
	@Override
	public String toString() {
		return "Customer [address=" + address + ", name=" + name + ", last_name=" + last_name + ", phone_number="
				+ phone_number + ", external_id=" + external_id + ", creation_date=" + creation_date + ", email="
				+ email + ", clabe=" + clabe + "]";
	}
    
    
}
