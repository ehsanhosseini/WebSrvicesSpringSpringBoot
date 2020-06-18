package com.webservice.springboot.webservice.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonFilter("SomeBeanFilter")  // use is for dynamic filtering for field1 and field2

//We can use this also for filtering. This is a Static filtering  
//sonIgnoreProperties(value= {"field1", "field2"})
public class SomeBean {
	
	private String field1;
	private String field2;
	
	//This is a static filtering. Imagin this is password and dont want to show it as response. this is better to used than @JsonIgnoreProperties
	//@JsonIgnore
	private String field3;
	
	
	public SomeBean(String field1, String field2, String field3) {
		super();
		this.field1 = field1;
		this.field2 = field2;
		this.field3 = field3;
	}


	public String getField1() {
		return field1;
	}


	public void setField1(String field1) {
		this.field1 = field1;
	}


	public String getField2() {
		return field2;
	}


	public void setField2(String field2) {
		this.field2 = field2;
	}


	public String getField3() {
		return field3;
	}


	public void setField3(String field3) {
		this.field3 = field3;
	}

	
}
