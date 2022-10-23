package com.ourteam.kodi.document;

import java.util.ArrayList;
import java.util.Date;

import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class Hen {
	public ObjectId _id;
	public String nickName;
	public String breed;
	public ArrayList<String> pics;
	public double price;
	public String voiceMessageUrl;
	public double height;
	public double weight;
	public double age;
	public String status;
	public ArrayList<String> achievements;
	public Date uploadedAt;
	public Date lastModifiedAt;
	public double views;
	public Address address;
	public Owner owner;

	public Hen() {
		super();
	}

	@Override
	public String toString() {
		return "Hen{" +
				"_id=" + _id +
				", nickName='" + nickName + '\'' +
				", breed='" + breed + '\'' +
				", pics=" + pics +
				", price=" + price +
				", voiceMessageUrl='" + voiceMessageUrl + '\'' +
				", height=" + height +
				", weight=" + weight +
				", age=" + age +
				", status='" + status + '\'' +
				", achievements=" + achievements +
				", uploadedAt=" + uploadedAt +
				", lastModifiedAt=" + lastModifiedAt +
				", views=" + views +
				", address=" + address +
				", owner=" + owner +
				'}';
	}
}
