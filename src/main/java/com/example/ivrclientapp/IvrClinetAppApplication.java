package com.example.ivrclientapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class IvrClinetAppApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(IvrClinetAppApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	    return application.sources(IvrClinetAppApplication.class);
	}
}
