package com.vinodh.springboot.test.web;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.vinodh.springboot.Application;

@RunWith(SpringRunner.class)
// search for @springBootApplication and start the ROOT application context
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = Application.class)
@DirtiesContext // to clear the caching
@AutoConfigureMockMvc
public class HomeControllerTest {

	@LocalServerPort
	private int port;

	@Autowired
	MockMvc mockMvc;

	@Autowired
	TestRestTemplate testRestTemplate;

	@Before
	public void setup() {

	}

	@Test
	public void testSpringContext() {
		Assert.assertNotNull(mockMvc);
	}

	@Test
	public void homePage() {
		Assert.assertEquals("Hello!! Welcome to Spring Boot Application",
				this.testRestTemplate.getForObject("http://localhost:" + port + "/", String.class));
	}

	@After
	public void cleanup() {

	}
}
