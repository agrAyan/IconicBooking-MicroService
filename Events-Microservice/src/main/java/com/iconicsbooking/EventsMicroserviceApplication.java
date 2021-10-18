package com.iconicsbooking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.iconicsbooking.model.Events;
import com.iconicsbooking.model.IconicBooking;
import com.iconicsbooking.model.Status;
import com.iconicsbooking.service.IEventService;

@SpringBootApplication
@EnableEurekaClient
public class EventsMicroserviceApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(EventsMicroserviceApplication.class, args);
	}
	@Autowired
	IEventService eventService;
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		//eventService.assignEvent(102, 2);
		//eventService.changeStatus(Status.IN_PROGESS.toString(), 2);
//		IconicBooking iconicBooking= new IconicBooking();
//		iconicBooking.setCompanyId(100);
//		Events events= eventService.getById(1);
//		events.setStatus(Status.COMPLETED);
//		
//		events.setIconicBooking(iconicBooking);
//		
//		Events eventUpadte= eventService.updateEvent(events);
//		System.out.println(eventUpadte);
	}
}
