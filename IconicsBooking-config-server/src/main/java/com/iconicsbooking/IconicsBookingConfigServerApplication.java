package com.iconicsbooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class IconicsBookingConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(IconicsBookingConfigServerApplication.class, args);
	}

}
