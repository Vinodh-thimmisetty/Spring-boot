package com.vinodh.springboot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.vinodh.springboot.entity.AppUser;
import com.vinodh.springboot.repository.AppUserRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.vinodh.springboot.repository")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	// Command Line Interfaces for pre-loading of any Data before original Spring
	// Application started. Can be used Multiple Command liners.

	@Bean
	@Order(Ordered.HIGHEST_PRECEDENCE)
	public CommandLineRunner commandLineRunner(AppUserRepository appUserRepository) {
		return x -> appUserRepository.save(AppUser.builder().appUserId(100l).userName("Vinodh")
				.userEmail("vinodh5052@gmail.com").userPhone("1234567890").build());

	}

	@Bean
	@Order(Ordered.LOWEST_PRECEDENCE)
	public CommandLineRunner commandLineRunner2(AppUserRepository appUserRepository) {
		return x -> {
			for (int i = 1; i <= 50; i++) {
				appUserRepository.save(AppUser.builder().appUserId(100l + i).userName("Vinodh" + i)

						.userEmail(i + "vinodh5052@gmail.com").userPhone("1234567890" + i).build());
			}

		};
	}

}
