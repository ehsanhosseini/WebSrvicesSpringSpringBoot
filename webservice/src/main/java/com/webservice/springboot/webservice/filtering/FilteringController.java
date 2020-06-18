package com.webservice.springboot.webservice.filtering;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {
	
	// These 2 methods below used for static filtering so I commented them not to modify. 
//	@GetMapping("/filtering")
//	public SomeBean retieriveSomeBean() {
//		
//		return new SomeBean("value1","value2","value3");
//	}
//
//	
//	@GetMapping("/filtering-list")
//	public List<SomeBean> retieriveListSomeBeans() {
//		
//		return Arrays.asList(new SomeBean("value12","value22","value32"),
//							new  SomeBean("value1","value2","value3"));
//	}
	
	
	
	// Here we use Dynamic filtering 
	//Only map filed1 and field2. when I just want to send field1 and field2
	@GetMapping("/filtering")
	public MappingJacksonValue retieriveSomeBean() {
		 
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field2");  
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);    // here we create local variable for filters. We need to send field1 and field2
		// SomeBeanFilter we use this in SomeBean class as @JsonFilter("SomeBeanFilter") 
		
		SomeBean someBean = new SomeBean("value1","value2","value3");
		MappingJacksonValue mapping = new MappingJacksonValue(someBean);    //Creating mapping for somebean
		mapping.setFilters(filters);                 //Here we configure the filter
		return mapping;
	}

	
	//return filed2 and field3
	@GetMapping("/filtering-list")
	public MappingJacksonValue retieriveListSomeBeans() {
		
		List<SomeBean> list = Arrays.asList(new SomeBean("value12","value22","value32"),
							new  SomeBean("value1","value2","value3"));
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2","field3");  
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);    
		
		MappingJacksonValue mapping = new MappingJacksonValue(list);    
		mapping.setFilters(filters);  
		
		
		return mapping;
	}
}








