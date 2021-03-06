package com.ourteam.kodi.document;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Hen {
	
		@Id
	    public String _id;
	    public String nickName;
	    public String breed;
	    public ArrayList<String> pics;
	    public double price;
	    public String voiceMessage;
	    public double height;
	    public double weight;
	    public double age;
	    public String status;
	    public ArrayList<String> achievements;
	    public Date createdAt;
	    public Date modifiedAt;
	    public double views;
	    public Address address;
	    public Owner owner;	    
	    
	public Hen() {
		super();
	}

	@Override
	public String toString() {
		return "Hen [_id=" + _id + ", nickName=" + nickName + ", breed=" + breed + ", pics=" + pics + ", price=" + price
				+ ", voiceMessage=" + voiceMessage + ", height=" + height + ", weight=" + weight + ", age=" + age
				+ ", status=" + status + ", achievements=" + achievements + ", createdAt=" + createdAt + ", modifiedAt="
				+ modifiedAt + ", views=" + views + ", address=" + address + ", owner=" + owner + "]";
	}	
}
