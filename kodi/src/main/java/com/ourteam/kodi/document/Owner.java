package com.ourteam.kodi.document;

import lombok.Data;

@Data
public class Owner {
	
	public String id;
    public String phoneNumber;
    public String name;
    public String dpUrl;
    
	public Owner() {
		super();		
	}

	public Owner(String id, String phoneNumber, String name, String dpUrl) {
		this.id = id;
		this.phoneNumber = phoneNumber;
		this.name = name;
		this.dpUrl = dpUrl;
	}

	@Override
	public String toString() {
		return "Owner [id=" + id + ", phoneNumber=" + phoneNumber + ", name=" + name + ", dpUrl=" + dpUrl + "]";
	}	
}
