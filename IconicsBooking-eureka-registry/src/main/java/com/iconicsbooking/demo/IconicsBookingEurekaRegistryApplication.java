package com.iconicsbooking.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class IconicsBookingEurekaRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(IconicsBookingEurekaRegistryApplication.class, args);
	}

}
