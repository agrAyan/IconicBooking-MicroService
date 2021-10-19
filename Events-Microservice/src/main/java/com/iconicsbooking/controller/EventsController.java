package com.iconicsbooking.controller;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.iconicsbooking.model.Events;
import com.iconicsbooking.model.Status;
import com.iconicsbooking.model.Task;
import com.iconicsbooking.service.IEventService;

@RestController
@RequestMapping("/events-api")   //it maps HTTP requests to handler methods of MVC and REST controllers
public class EventsController {
	@Autowired
	IEventService eventService;
	
	@JsonProperty
	//methods handle the HTTP POST requests matched with given URI expression
	@PostMapping("/events")     
	Events addEvent(@RequestBody Events event)
	{
		System.out.println(event);
	return eventService.addEvent(event);
	}
	//methods handle the HTTP DELETE requests onto specific handler methods
	@DeleteMapping("/events")    
	void deleteEvent(int eventId)
	{
		eventService.deleteEvent(eventId);
	}
	  //methods handle the HTTP GET requests onto specific handler methods
	@GetMapping("/events")    
	List<Events> getAll()
	{
		return eventService.getAll();
	}
	//methods handle the HTTP PUT requests onto specific handler methods
	@PutMapping("/events")     
	Events updateEvent(@RequestBody Events event)
	{
		//System.out.println(event.getIconicBooking().getCompanyId());
	return eventService.updateEvent(event);
	}
	 //Getting Watch Details by using Id one by one
	@GetMapping("/events/eventId/{eventId}")  
	ResponseEntity<Events> getById(@PathVariable("eventId") int eventId)
	{
		Events event = eventService.getById(eventId);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc","Get one event by Id");
		headers.add("info","Returning one event");
		ResponseEntity<Events> responseEvent = new ResponseEntity<Events>(event,headers,HttpStatus.OK);
		return responseEvent;
	}
        /*@PathVariable annotation is used on a method argument to bind it to the value of a URI template variable
	ResponseEntity is meant to represent the entire HTTP response.you can control anything that goes into it: status code,headers and body */
	@GetMapping("/events/eventName/{eventName}")
	ResponseEntity<List<Events>> getByEventName(@PathVariable("eventName") String eventName)
	{
		List<Events> eventsByname = eventService.getByEventName(eventName);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc","Get eventList By event name");
		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(eventsByname);
	}
	//calling event details based on event provider	
       //ResponseEntity represents the whole HTTP response: status code, headers, and body
	@GetMapping("/events/eventProvider/{eventProvider}")
	ResponseEntity<List<Events>> getByEventProvider(@PathVariable("eventProvider") String eventProvider)
	{
		List<Events> eventsByProvider = eventService.getByEventProvider(eventProvider);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc","Get eventList By event provider");
		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(eventsByProvider);
	}
	//calling event details based on priority
	@GetMapping("/events/priority/{priority}")
	ResponseEntity<List<Events>> getByEventPriority(@PathVariable("priority") String priority)
	{
		List<Events> eventsByPriority = eventService.getByPriority(priority);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc","Get eventList By event priority");
		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(eventsByPriority);
	}
	//Getting event details based on status
	@GetMapping("/events/status/{status}")
	ResponseEntity<List<Events>> getByEventStatus(@PathVariable("status") String status)
	{
		List<Events> eventsByStatus = eventService.getByStatus(status);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc","Get eventList By event status");
		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(eventsByStatus);
	}
	//calling event details based on price
	@GetMapping("/events/price/{price}")
	ResponseEntity<List<Events>> getByEventPrice(@PathVariable("price") double price)
	{
		List<Events> eventsByPrice = eventService.getByPrice(price);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc","Get eventList By event Price");
		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(eventsByPrice);
	
	}
	//calling event details based on start date
	@GetMapping("/events/startDate/{startDate}")
	ResponseEntity<List<Events>> getByStartDate(@PathVariable("startDate") String startDate){
		LocalDate startDateCon= LocalDate.parse(startDate);
		List<Events> eventsByStartDate = eventService.getByStartDate(startDateCon);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc","Get eventList By event status");
		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(eventsByStartDate);
	}
	//Calling event details based on end date
	@GetMapping("/events/endDate/{endDate}")
    ResponseEntity<List<Events>> getByEndDate(@PathVariable("endDate") String endDate){
    	LocalDate endDateCon= LocalDate.parse(endDate);
		List<Events> eventsByEndDate = eventService.getByEndDate(endDateCon);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc","Get eventList By event status");
		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(eventsByEndDate);
    }

	//////////////////////////Task micro service /////////////////////////////////////
	
	//Calling all task details in event micro service
	@GetMapping("/tasks")
	List<Task>getAllTasks()
	{
		return eventService.getAllTasks();
	}
	//Calling task details 
	@PostMapping("/tasks")
	ResponseEntity<Task> addTask(@RequestBody Task task)
	{
		return ResponseEntity.status(HttpStatus.OK).body(eventService.addTask(task));
	}
	//Calling task details based on task Id in event controller
	@GetMapping("/tasks/taskId/{taskId}")
	ResponseEntity<Task> taskById(@PathVariable("taskId")int taskId)
	{
		return ResponseEntity.status(HttpStatus.OK).body(eventService.getBytaskId(taskId));
	}
	
	//Calling task details based on organiser name in event controller
	@GetMapping("/tasks/organiserName/{organiserName}")
	ResponseEntity<List<Task>>  getByOrganiser(@PathVariable("organiserName") String organiserName)
	{
		List<Task> task = eventService.getByOrganiser(organiserName);
		HttpHeaders headers = new HttpHeaders();
		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(task);
		
	}
	//Calling task details based on task name in event controller
	@GetMapping("/tasks/taskName/{taskName}")
	ResponseEntity<List<Task>>  getByTaskName(@PathVariable("taskName")  String taskName)
	{
		List<Task> task = eventService.getByTaskName(taskName);
		HttpHeaders headers = new HttpHeaders();
		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(task);
	}
	//Calling task details based on start date in event controller
	@GetMapping("/tasks/taskStartDate/{taskStartDate}")
	ResponseEntity<List<Task>> getByTaskStartDate(@PathVariable("taskStartDate")  String startDate)
	{
		LocalDate startdate = LocalDate.parse(startDate);
		List<Task> task = eventService.getByTaskStartDate(startdate);
		HttpHeaders headers = new HttpHeaders();
		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(task);
	}
	
	//Calling task details based on end date in event controller
	@GetMapping("/tasks/taskEndDate/{taskEndDate}")
	ResponseEntity<List<Task>> getByTaskEndStartDate(@PathVariable("taskEndDate")  String taskEndDate)
	{
		LocalDate enddate = LocalDate.parse(taskEndDate);
		List<Task> task = eventService.getByTaskEndDate(enddate);
		HttpHeaders headers = new HttpHeaders();
		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(task);
	}
	
	//Calling task details based on rating in event controller
	@GetMapping("/tasks/rating/{rating}")
	ResponseEntity<List<Task>> getByRating(@PathVariable("rating") double rating)
   {
		List<Task> task = eventService.getByRating(rating);
		HttpHeaders headers = new HttpHeaders();
		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(task);
   }
	
	//Calling task details based on status in event controller
	@GetMapping("/tasks/status/{status}")
	ResponseEntity<List<Task>> getByRating(@PathVariable("status") Status status)
   {
		List<Task> task = eventService.getByStatus(status);
		HttpHeaders headers = new HttpHeaders();
		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(task);
   }
	
	//calling task details based on duration in event controller
	@GetMapping("/tasks/duration/{duration}")
	ResponseEntity<List<Task>> getByDuration(@PathVariable("duration") int duration)
   {
		List<Task> task = eventService.getByDuration(duration);
		HttpHeaders headers = new HttpHeaders();
		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(task);
   }
	
	//calling assign task method in event controller
	@GetMapping("/tasks/assginTask/eventId/{eventId}/taskId/{taskId}")
	ResponseEntity<String> assignTask(@PathVariable("eventId") int eventId, @PathVariable("taskId") int taskId)
   {
		String value = eventService.assignTask(eventId, taskId);
		HttpHeaders headers = new HttpHeaders();
		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(value);
   }
	//calling remove method in event controller
	@GetMapping("/tasks/removeTask/taskId/{taskId}")
	ResponseEntity<String> removeTask(@PathVariable("taskId") int taskId)
   {
		String value = eventService.releaseTask(taskId);
		HttpHeaders headers = new HttpHeaders();
		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(value);
   }
	
	
}
