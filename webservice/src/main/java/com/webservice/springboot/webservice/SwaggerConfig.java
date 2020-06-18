package com.webservice.springboot.webservice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2  // Enable Swagger
public class SwaggerConfig {
	
	// We copy these from ApiInfo.class and modify them. 
	// We can add title, description, and edit 
	public static final Contact DEFAULT_CONTACT = new 
			Contact("Ehsan", "www.localhost:8080", "ehsan.hosseini@gmail.com"); // modify this part
	  public static final ApiInfo DEFAULT_API_INFO = new 
			  ApiInfo("Api title", "Api Description", "1.0", "urn:tos",   // modify this part
	          DEFAULT_CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", 
	          new ArrayList<VendorExtension>());
	
	//private static final ApiInfo DEFAULT_API_INFO = null;
	  
	  
	  

	  
//	  "consumes": [
//	               "application/xml",
//	               "application/json"
//	           ],
//	           "produces": [
//	               "application/xml",
//	               "application/json"
//	           ],
	  // produce apllication/json and application/xml as output. Creat HashSet to add application/json and application/xml instead of "produces":  "*/*" in Swagger path
	  		private static final Set<String> DEFAULT_PRODUCES_CONSUMES = 
	  				new HashSet<String>(Arrays.asList("application/json","application/xml"));
	  			

	@Bean
	public Docket api() {
		// in ui I can type http://localhost:8080/v2/api-docs or   --> We can see all services we have created
		// to get all documentation 
		return new Docket(DocumentationType.SWAGGER_2)
			.apiInfo(DEFAULT_API_INFO)
			.produces(DEFAULT_PRODUCES_CONSUMES)
			.consumes(DEFAULT_PRODUCES_CONSUMES)
			
			;   
	} 
	
	
}
