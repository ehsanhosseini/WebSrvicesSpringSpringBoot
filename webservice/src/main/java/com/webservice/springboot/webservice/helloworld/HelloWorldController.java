package com.webservice.springboot.webservice.helloworld;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;

//Need to tell spring this is controller. It means it is handeling HTTP request

@RestController
public class HelloWorldController {
	
	
	@Autowired
	private MessageSource messageSource;

	// We want to map get request to this URI /hello-world
	// @RequestMapping(method = RequestMethod.GET, path = "/hello-world") // this
	// one is also work @GetMapping("/hello-world")
	@GetMapping("/hello-world")
	public String helloWorld() {
		return "Hello World.......";
	}

	// here we create a bean and return it back
	@GetMapping("/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello World Bean");
	}
	
	// use path variable
	@GetMapping("/hello-world-bean/path-variable/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello World, %s ", name));
	} 
	
	// ues it for internatiolization
	//we can get good moring in two different language(message.prperties and message_french.prperties) 
	// In post man we can pass value key as Accept_language and value us/French
	@GetMapping("/hello-world-internatioalization")
	public String helloWorldInternatinalization() {   // If Accept Language is not pickup then defualt locate is US so we put it false
		return messageSource.getMessage("good.morning.message",null,  LocaleContextHolder.getLocale());  // We get good.morning.messag fom messages.properties
	}
	
}
