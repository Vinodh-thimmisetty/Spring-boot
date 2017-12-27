package com.vinodh.springboot.test.web;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinodh.springboot.entity.AppUser;
import com.vinodh.springboot.service.AppUserService;
import com.vinodh.springboot.web.AppUserController;

@RunWith(MockitoJUnitRunner.class)
public class AppUserControllerMockitoTest {

	@InjectMocks
	AppUserController appUserController;

	@Mock
	AppUserService appUserService;

	MockMvc mockMvc;
	ObjectMapper objectMapper;

	@Before
	public void setup() {

		mockMvc = MockMvcBuilders.standaloneSetup(appUserController).build();
	}

	@Test
	public void findAllUsers() throws Exception {
		objectMapper = new ObjectMapper();
		AppUser appUser = AppUser.builder().appUserId(100l).userName("Vinodh").userPhone("1212")
				.userEmail("vinodh5052@gmail.com").build();
		Mockito.when(appUserService.getUsers())
				.thenReturn(Stream.of(appUser).collect(Collectors.toList()));
		List<AppUser> appUsers = objectMapper
				.readValue(
						mockMvc.perform(MockMvcRequestBuilders.get("/user/findAllUsers"))
								.andExpect(MockMvcResultMatchers.status().isOk()).andReturn()
								.getResponse().getContentAsString(),
						new TypeReference<List<AppUser>>() {
						});

		Assert.assertEquals(1, appUsers.size());

		mockMvc.perform(MockMvcRequestBuilders.get("/user/findAllUsers"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].userName").value("Vinodh"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].userEmail")
						.value("vinodh5052@gmail.com"));

	}

}
