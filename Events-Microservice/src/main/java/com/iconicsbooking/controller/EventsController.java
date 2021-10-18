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
@RequestMapping("/events-api")
public class EventsController {
	@Autowired
	IEventService eventService;
	
	@JsonProperty
	@PostMapping("/events")
	Events addEvent(@RequestBody Events event)
	{
		System.out.println(event);
	return eventService.addEvent(event);
	}
	@DeleteMapping("/events")
	void deleteEvent(int eventId)
	{
		eventService.deleteEvent(eventId);
	}
	@GetMapping("/events")
	List<Events> getAll()
	{
		return eventService.getAll();
	}
	@PutMapping("/events")
	Events updateEvent(@RequestBody Events event)
	{
		//System.out.println(event.getIconicBooking().getCompanyId());
	return eventService.updateEvent(event);
	}
	
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
	@GetMapping("/events/eventName/{eventName}")
	ResponseEntity<List<Events>> getByEventName(@PathVariable("eventName") String eventName)
	{
		List<Events> eventsByname = eventService.getByEventName(eventName);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc","Get eventList By event name");
		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(eventsByname);
	}
	@GetMapping("/events/eventProvider/{eventProvider}")
	ResponseEntity<List<Events>> getByEventProvider(@PathVariable("eventProvider") String eventProvider)
	{
		List<Events> eventsByProvider = eventService.getByEventProvider(eventProvider);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc","Get eventList By event provider");
		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(eventsByProvider);
	}
	@GetMapping("/events/priority/{priority}")
	ResponseEntity<List<Events>> getByEventPriority(@PathVariable("priority") String priority)
	{
		List<Events> eventsByPriority = eventService.getByPriority(priority);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc","Get eventList By event priority");
		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(eventsByPriority);
	}
	@GetMapping("/events/status/{status}")
	ResponseEntity<List<Events>> getByEventStatus(@PathVariable("status") String status)
	{
		List<Events> eventsByStatus = eventService.getByStatus(status);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc","Get eventList By event status");
		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(eventsByStatus);
	}
	@GetMapping("/events/price/{price}")
	ResponseEntity<List<Events>> getByEventPrice(@PathVariable("price") double price)
	{
		List<Events> eventsByPrice = eventService.getByPrice(price);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc","Get eventList By event Price");
		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(eventsByPrice);
	
	}
	
	@GetMapping("/events/startDate/{startDate}")
	ResponseEntity<List<Events>> getByStartDate(@PathVariable("startDate") String startDate){
		LocalDate startDateCon= LocalDate.parse(startDate);
		List<Events> eventsByStartDate = eventService.getByStartDate(startDateCon);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc","Get eventList By event status");
		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(eventsByStartDate);
	}
	@GetMapping("/events/endDate/{endDate}")
    ResponseEntity<List<Events>> getByEndDate(@PathVariable("endDate") String endDate){
    	LocalDate endDateCon= LocalDate.parse(endDate);
		List<Events> eventsByEndDate = eventService.getByEndDate(endDateCon);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc","Get eventList By event status");
		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(eventsByEndDate);
    }

	//////////////////////////task
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@GetMapping("/tasks")
	List<Task>getAllTasks()
	{
		return eventService.getAllTasks();
	}
	
	@PostMapping("/tasks")
	ResponseEntity<Task> addTask(@RequestBody Task task)
	{
		return ResponseEntity.status(HttpStatus.OK).body(eventService.addTask(task));
	}
	
	@GetMapping("/tasks/taskId/{taskId}")
	ResponseEntity<Task> taskById(@PathVariable("taskId")int taskId)
	{
		return ResponseEntity.status(HttpStatus.OK).body(eventService.getBytaskId(taskId));
	}
	
	
	@GetMapping("/tasks/organiserName/{organiserName}")
	ResponseEntity<List<Task>>  getByOrganiser(@PathVariable("organiserName") String organiserName)
	{
		List<Task> task = eventService.getByOrganiser(organiserName);
		HttpHeaders headers = new HttpHeaders();
		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(task);
		
	}
	@GetMapping("/tasks/taskName/{taskName}")
	ResponseEntity<List<Task>>  getByTaskName(@PathVariable("taskName")  String taskName)
	{
		List<Task> task = eventService.getByTaskName(taskName);
		HttpHeaders headers = new HttpHeaders();
		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(task);
	}
	@GetMapping("/tasks/taskStartDate/{taskStartDate}")
	ResponseEntity<List<Task>> getByTaskStartDate(@PathVariable("taskStartDate")  String startDate)
	{
		LocalDate startdate = LocalDate.parse(startDate);
		List<Task> task = eventService.getByTaskStartDate(startdate);
		HttpHeaders headers = new HttpHeaders();
		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(task);
	}
	@GetMapping("/tasks/taskEndDate/{taskEndDate}")
	ResponseEntity<List<Task>> getByTaskEndStartDate(@PathVariable("taskEndDate")  String taskEndDate)
	{
		LocalDate enddate = LocalDate.parse(taskEndDate);
		List<Task> task = eventService.getByTaskEndDate(enddate);
		HttpHeaders headers = new HttpHeaders();
		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(task);
	}
	@GetMapping("/tasks/rating/{rating}")
	ResponseEntity<List<Task>> getByRating(@PathVariable("rating") double rating)
   {
		List<Task> task = eventService.getByRating(rating);
		HttpHeaders headers = new HttpHeaders();
		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(task);
   }
	@GetMapping("/tasks/status/{status}")
	ResponseEntity<List<Task>> getByRating(@PathVariable("status") Status status)
   {
		List<Task> task = eventService.getByStatus(status);
		HttpHeaders headers = new HttpHeaders();
		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(task);
   }
	@GetMapping("/tasks/duration/{duration}")
	ResponseEntity<List<Task>> getByDuration(@PathVariable("duration") int duration)
   {
		List<Task> task = eventService.getByDuration(duration);
		HttpHeaders headers = new HttpHeaders();
		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(task);
   }
	
	@GetMapping("/tasks/assginTask/eventId/{eventId}/taskId/{taskId}")
	ResponseEntity<String> assignTask(@PathVariable("eventId") int eventId, @PathVariable("taskId") int taskId)
   {
		String value = eventService.assignTask(eventId, taskId);
		HttpHeaders headers = new HttpHeaders();
		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(value);
   }
	
	@GetMapping("/tasks/removeTask/taskId/{taskId}")
	ResponseEntity<String> removeTask(@PathVariable("taskId") int taskId)
   {
		String value = eventService.releaseTask(taskId);
		HttpHeaders headers = new HttpHeaders();
		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(value);
   }
	
	
	
	
	
	
}