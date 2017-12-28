package com.vinodh.springboot;

import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.vinodh.springboot.entity.Address;
import com.vinodh.springboot.entity.AppUser;
import com.vinodh.springboot.entity.OrderInvoice;
import com.vinodh.springboot.entity.Orders;
import com.vinodh.springboot.entity.Products;
import com.vinodh.springboot.repository.AppUserRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.vinodh.springboot.repository")
public class Application extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder springApplication) {
		return springApplication.sources(Application.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	// Command Line Interfaces for pre-loading of any Data before original Spring
	// Application started. Can be used Multiple Command liners.

	@Bean
	@Order(Ordered.HIGHEST_PRECEDENCE)
	public CommandLineRunner commandLineRunner(AppUserRepository appUserRepository) {
		return x -> appUserRepository.save(AppUser.builder().appUserId(100l).userName("Vinodh")
				.userEmail("vinodh5052@gmail.com").userPhone("1234567890")
				.address(Address.builder().county("USA").street("Newyork").postcode("51611").build())
				.orders(Orders.builder().appUserId(100l).orderId(1000l).orderDesc("Sample Order Desc")
						.orderDt(new Date()).totPrice(100.23)
						.invoice(OrderInvoice.builder().orderId(1000l).amountDue(11.12).orderRaisedDt(new Date())
								.build())
						.productsList(Stream.of(Products.builder().prodId(10000l).price("12").prodName("test").build())
								.collect(Collectors.toList()))
						.build())
				// .products(Products.builder().prodId(10000l).appUserId(100l).build())
				.build());
	}

	@Bean
	@Order(Ordered.LOWEST_PRECEDENCE)
	public CommandLineRunner commandLineRunner2(AppUserRepository appUserRepository) {
		return x -> {
			for (int i = 1; i <= 50; i++) {
				appUserRepository.save(AppUser.builder().appUserId(100l + i).userName("Vinodh" + i)

						.userEmail(i + "vinodh5052@gmail.com").userPhone("1234567890" + i).address(Address.builder()
								.county("India" + i).street("Shols" + i).postcode("516101" + i).build())
						.build());
			}

		};
	}

}
