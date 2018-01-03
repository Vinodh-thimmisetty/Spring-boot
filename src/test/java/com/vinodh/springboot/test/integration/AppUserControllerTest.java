package com.vinodh.springboot.test.integration;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.vinodh.springboot.domain.CustomerDTO;
import com.vinodh.springboot.entity.AppUser;
import com.vinodh.springboot.service.AppUserService;
import com.vinodh.springboot.web.AppUserController;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = AppUserController.class)
public class AppUserControllerTest {

	@InjectMocks
	private AppUserController appUserController;

	@MockBean
	private AppUserService appUserService;

	@Autowired
	private MockMvc mockMvc;

	@Before
	public void setup() {

	}

	@Test
	public void findAllUsers() throws Exception {
		CustomerDTO customerDTO = CustomerDTO.builder().customerId(100l).userName("Vinodh").userPhone("1212")
				.userEmail("vinodh5052@gmail.com").build();
		Mockito.when(appUserService.getUsers())
				.thenReturn(Stream.of(new AppUser(customerDTO)).collect(Collectors.toList()));

		mockMvc.perform(MockMvcRequestBuilders.get("/user/findAllUsers"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].userName").value("Vinodh"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].userEmail").value("vinodh5052@gmail.com"))
				.andDo(MockMvcResultHandlers.print());

	}

}
