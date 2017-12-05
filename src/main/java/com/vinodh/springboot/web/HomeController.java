package com.vinodh.springboot.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * Home Controller for mapping default application level.
 * 
 * @author Vinodh Kumar Thimmisetty
 *
 */
@RestController
public class HomeController {

	@GetMapping("/")
	public String home() {
		return "Hello!! Welcome to Spring Boot Application";
	}
}
