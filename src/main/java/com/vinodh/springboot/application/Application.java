package com.vinodh.springboot.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class,
		HibernateJpaAutoConfiguration.class })
public class Application extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder springApplication) {
		return springApplication.sources(Application.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args); 
	}
}
