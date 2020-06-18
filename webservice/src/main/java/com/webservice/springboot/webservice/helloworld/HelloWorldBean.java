package com.webservice.springboot.webservice.helloworld;

public class HelloWorldBean {

	String message;
	
	HelloWorldBean(String message){
		this.message = message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	

	public String getMessage() {
		return message;
	}
	
	@Override
	public String toString() {
		return String.format("HelloWorldBean [message=%s]", message);
	}
	
	
	
}
