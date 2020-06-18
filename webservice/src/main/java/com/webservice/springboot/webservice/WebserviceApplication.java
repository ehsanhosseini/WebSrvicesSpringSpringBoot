package com.webservice.springboot.webservice;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@SpringBootApplication
public class WebserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebserviceApplication.class, args);
	}

	
	//Create a bean for Internationalization for US user
	@Bean
	public LocaleResolver localResolver() {
		AcceptHeaderLocaleResolver acceptHeaderLocalResolver = new AcceptHeaderLocaleResolver(); 
		acceptHeaderLocalResolver.setDefaultLocale(Locale.US);  // As default we use Local.US
		return acceptHeaderLocalResolver;  
	}
	
}
