package com.vinodh.springboot.test.repositrory;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Matchers.eq;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.vinodh.springboot.domain.AdminDTO;
import com.vinodh.springboot.entity.Admin;
import com.vinodh.springboot.repository.AdminRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AdminDetailsTest {

	@Autowired
	AdminRepository testRepository;

	@Test
	public void should_find_no_admins_if_admin_username_is_invalid() {
		AdminDTO adminDTO = AdminDTO.builder().adminId(111).adminUserName("vinodh5052").password("asdas")
				.adminFirstName("Vinodh Kumar").adminEMail("asdas").adminLastName("asdsad").adminRole("USER")
				.applicationName("asd").build();
		Admin admin = new Admin(adminDTO);
		testRepository.save(admin);
		Assert.assertThat(testRepository.getAdminByName("vinodh5052").getAdminFirstName(), is(eq("Vinodh Kumar")));
	}

}
