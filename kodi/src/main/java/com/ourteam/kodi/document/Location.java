package com.ourteam.kodi.document;

import java.util.ArrayList;

import lombok.Data;

@Data
public class Location {
	
	public String type;
    public ArrayList<Double> coordinates;
    
	public Location() {
		super();	
	}

	@Override
	public String toString() {
		return "Location [type=" + type + ", coordinates=" + coordinates + "]";
	}
}
