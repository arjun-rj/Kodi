package com.ourteam.kodi.exception;

import lombok.Data;

@Data
public class RootException {

	int errorCode;
	String message;
	
	public RootException() {
		super();		
	}	
}
