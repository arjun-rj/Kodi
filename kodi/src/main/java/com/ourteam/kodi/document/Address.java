package com.ourteam.kodi.document;

import lombok.Data;

@Data
public class Address {
	public Location location;
    public String city;
    public String state;
    public String pincode;
    public String phoneNumber;
    
	public Address() {
		super();		
	}
	@Override
	public String toString() {
		return "Address [location=" + location + ", city=" + city + ", state=" + state + ", pincode=" + pincode
				+ ", phoneNumber=" + phoneNumber + "]";
	}      
}
