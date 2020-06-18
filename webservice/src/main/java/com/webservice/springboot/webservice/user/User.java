package com.webservice.springboot.webservice.user;

import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel(description="All details about the user") // we use this for Swagger documentation
public class User {

	private Integer id;
	
	@Size(min=2, message = "Name Must have atleast 2 Charecter")
	@ApiModelProperty(notes="Birth date should be in the past") // Use if for Swagger documetation
	private String name;
	
	@Past
	@ApiModelProperty(notes="Birth date should be in the past") // Use if for Swagger documetation
	private Date birthDate;
	
	
	// We overrided the default no argument constractor generally provided by Java but we need to created back 
	// Now it wont be any more default becasue we are providing implementation for it. 
	protected User() {
		
	}
	
	public User(Integer id, String name, Date birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}
	
	

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}


	@Override
	public String toString() {
		return String.format("User [id=%s, name=%s, birthDate=%s]", id, name, birthDate);
	}
	
	
	
	
}
