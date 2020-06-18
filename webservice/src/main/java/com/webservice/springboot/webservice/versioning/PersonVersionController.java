package com.webservice.springboot.webservice.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersionController {
	
	//URI Versioning 
	@GetMapping("v1/person")
	public PersonV1 personV1() {
		return new PersonV1("This is PersonV1");
	}
	
	@GetMapping("v2/person")
	public PersonV2 personV2() {
		return new PersonV2(new Name("Ehsan", "Hosseini"));
	}
	
	//Request Parametr Versioning
	//Another way of versioning is request parameter, using value and parameter
	@GetMapping(value="/person/param", params="version=1")
	public PersonV1 paramV1() {
		return new PersonV1("This is PersonV1");
	}
	
	@GetMapping(value="/person/param", params="version=2")
	public PersonV2 paramV2() {
		return new PersonV2(new Name("Ehsan", "Hosseini"));
	}
	
	// Header(Custom) versioning
	//Another way of versioning is header versioning, using parameter in request header
	// To check in postman. go to header and key= X-API-VERSION and value=1
		@GetMapping(value="/person/header", headers="X-API-VERSION=1")
		public PersonV1 headerV1() {
			return new PersonV1("This is PersonV1");
		}
		
		@GetMapping(value="/person/header", headers="X-API-VERSION=2")
		public PersonV2 headerV2() {
			return new PersonV2(new Name("Ehsan", "Hosseini"));
		}
	
		
		//Media type versioning(a.k.a "content Negotioation" or "accept header")S
		//Accept header versioning or Mind type versioning. 
		//In Postman header, key=Accept, Value=application/ehsan.company.app-v1+json
		@GetMapping(value="/person/produces", produces="application/ehsan.company.app-v1+json")
		public PersonV1 producesV1() {
			return new PersonV1("This is PersonV1");
		}
		
		@GetMapping(value="/person/produces", produces="application/ehsan.company.app-v2+json")
		public PersonV2 producesV2() {
			return new PersonV2(new Name("Ehsan", "Hosseini"));
		}
	
	
	
	
	
	
	
	

}
