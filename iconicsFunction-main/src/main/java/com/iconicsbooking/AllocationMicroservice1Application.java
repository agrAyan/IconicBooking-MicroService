package com.iconicsbooking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.iconicsbooking.model.Events;
import com.iconicsbooking.model.Status;
import com.iconicsbooking.model.Task;
import com.iconicsbooking.service.ITaskService;

@SpringBootApplication
@EnableEurekaClient
public class AllocationMicroservice1Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(AllocationMicroservice1Application.class, args);
	}

	@Autowired
	ITaskService taskService;

	@Override
	public void run(String... args) throws Exception {
		//LocalDate startDate = LocalDate.of(2021, 10, 6);

//		LocalDate endDate = LocalDate.of(2021, 10, 7);
//		Status status = Status.IN_PROGRESS;

//	should be decorator,lighting	
//		Task task = new Task("Decoration", "Ayan", 4.5, startDate, endDate, 5, status);
//			Task t = taskService.addTask(task);
		//System.out.println(t);
//
////		System.out.println(taskService.getAll());
//		taskService.getAll().forEach(System.out::println);
		//System.out.println(taskService.getByOrganiser("Ayan"));

//    	taskService.getByEndDate(endDate).forEach(System.out::println);
	
		
//		Events event= new Events();
//		event.setEventId(100);
//		Task task= taskService.getBytaskId(100);
//		task.setEvents(event);
//		task.setStatus(Status.IN_PROGRESS);
//		Task taskU= taskService.updateTask(task);
//		System.out.println(taskU);
	}
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

}
