package com.vinodh.springboot.test.integration;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.vinodh.springboot.entity.AppUser;
import com.vinodh.springboot.service.AppUserService;
import com.vinodh.springboot.web.AppUserController;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = AppUserController.class)
public class AppUserControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	AppUserService appUserService;

	@Before
	public void setup() {

	}

	@Test
	public void findAllUsers() throws Exception {
		AppUser appUser = AppUser.builder().appUserId(100l).userName("Vinodh").userPhone("1212")
				.userEmail("vinodh5052@gmail.com").build();
		Mockito.when(appUserService.getUsers())
				.thenReturn(Stream.of(appUser).collect(Collectors.toList()));
		mockMvc.perform(MockMvcRequestBuilders.get("/user/findAllUsers"))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}
}
