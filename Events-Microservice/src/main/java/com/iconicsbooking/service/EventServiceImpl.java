package com.iconicsbooking.service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.iconicsbooking.exception.EventsNotFoundException;
import com.iconicsbooking.exception.IdNotFoundException;
import com.iconicsbooking.model.Events;
import com.iconicsbooking.model.Priority;
import com.iconicsbooking.model.Status;
import com.iconicsbooking.model.Task;
import com.iconicsbooking.repository.IEventRepository;

@Service
@Transactional
public class EventServiceImpl implements IEventService{
	
	
   final String changedStatusToProgess= Status.IN_PROGRESS.toString();
	final String nstatus=Status.NOT_STARTED.toString();
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	IEventRepository eventRepository;
	public static final String BASEURL = "http://localhost:8083/task-api";
	public void setEventRepository(IEventRepository eventRepository) {
		this.eventRepository = eventRepository;
	}

	@Override
	public Events addEvent(Events events) {
		return eventRepository.save(events);
	}

	@Override
	public Events updateEvent(Events events) {
		return eventRepository.save(events);		
	}

	@Override
	public void deleteEvent(int eventId) {
		eventRepository.deleteById(eventId);		
	}

	@Override
	public List<Events> getAll() {
		return eventRepository.findAll();
	}

	@Override
	public Events getById(int serviceId) throws IdNotFoundException{
		return eventRepository.findById(serviceId).orElseThrow(() -> new IdNotFoundException("invalid id"));
		//return eventRepository.getById(serviceId);
	}


	@Override
	public List<Events> getByEventName(String eventName) throws EventsNotFoundException{
		List<Events> eventList = eventRepository.findByEventName(eventName);
		if(eventList.isEmpty())
		 throw new EventsNotFoundException("Event name not available");		
		return eventList;
	}

	@Override
	public List<Events> getByStartDate(LocalDate startDate) {
		List<Events> eventList = eventRepository.findByStartDate(startDate);
		if(eventList.isEmpty())
		 throw new EventsNotFoundException("On this date  not available");		
		return eventList;
	}

	@Override
	public List<Events> getByEndDate(LocalDate endDate) {
		List<Events> eventList = eventRepository.findByEndDate(endDate);
		if(eventList.isEmpty())
		 throw new EventsNotFoundException("On this date  not available");		
		return eventList;
	}

	@Override
	public List<Events> getByStatus(String status) {
		Status statusValue= Status.valueOf(status);
		List<Events> status1 = eventRepository.findByStatus(statusValue);
		if(status1.isEmpty())
			 throw new EventsNotFoundException("Status Not available");		
			return status1; 
	}

	@Override
	public List<Events> getByPrice(double price) {
		List<Events> eventList = eventRepository.findByPriceLessThan(price);
		if(eventList.isEmpty())
		 throw new EventsNotFoundException("In this price event not available");		
		return eventList;
	}

	@Override
	public List<Events> getByEventProvider(String eventProvider) {
		List<Events> eventList = eventRepository.findByEventProvider(eventProvider);
		if(eventList.isEmpty())
		 throw new EventsNotFoundException("Event Provider not available");		
		return eventList;	}

	@Override
	public List<Events> getByPriority(String priority) {
Priority priorityValue = Priority.valueOf(priority);
		
		List<Events> priority1 = eventRepository.findByPriority(priorityValue);
		if(priority1.isEmpty())
		 throw new EventsNotFoundException("priority not available");		
		return priority1;
	}





	
	
	
	
	
	@Override
	public Task getBytaskId(int taskId) {
		String url = BASEURL +"/tasks/taskId/" +taskId;
		ResponseEntity<Task> response = restTemplate.getForEntity(url,Task.class);
		System.out.println(response.getStatusCodeValue()+ " "+response);
		return response.getBody();
		
	}

	@Override
	public List<Task> getAllTasks() {
		String url = BASEURL + "/tasks";
		ResponseEntity<List> response = restTemplate.getForEntity(url, List.class);
		return response.getBody();
	}

	@Override
	public List<Task> getByOrganiser(String organiserName) {
		String url = BASEURL + "/tasks/organiser/" + organiserName;
		ResponseEntity<List> response = restTemplate.getForEntity(url, List.class);
		
		System.out.println(response.getStatusCodeValue() + "....");
		return response.getBody();
	}

	@Override
	public List<Task> getByTaskName(String taskName) {
		String url = BASEURL + "/tasks/taskName/" + taskName;
		ResponseEntity<List> response = restTemplate.getForEntity(url, List.class);
		
		System.out.println(response.getStatusCodeValue() + "....");
		return response.getBody();
	}

	@Override
	public List<Task> getByTaskStartDate(LocalDate taskstartDate) {
		String url = BASEURL + "/tasks/startDate/" + taskstartDate;
		ResponseEntity<List> response = restTemplate.getForEntity(url, List.class);
		
		System.out.println(response.getStatusCodeValue() + "....");
		return response.getBody();
	}

	@Override
	public List<Task> getByTaskEndDate(LocalDate taskendDate) {
			String url = BASEURL + "/tasks/endDate/" + taskendDate;
			ResponseEntity<List> response = restTemplate.getForEntity(url, List.class);
			
			System.out.println(response.getStatusCodeValue() + "....");
			return response.getBody();
	}

	@Override
	public List<Task> getByRating(double rating) {
		String url = BASEURL + "/tasks/rating/" + rating;
		ResponseEntity<List> response = restTemplate.getForEntity(url, List.class);
		System.out.println(response.getStatusCodeValue() + "....");
		return response.getBody();
	}

	@Override
	public List<Task> getByStatus(Status status) {
		String url = BASEURL + "/tasks/status/" + status;
		ResponseEntity<List> response = restTemplate.getForEntity(url, List.class);
		System.out.println(response.getStatusCodeValue() + "....");
		return response.getBody();
	}

	@Override
	public List<Task> getByDuration(int duration) {
		String url = BASEURL + "/tasks/duration/" + duration;
		ResponseEntity<List> response = restTemplate.getForEntity(url, List.class);
		System.out.println(response.getStatusCodeValue() + "....");
		return response.getBody();
	}

	@Override
	public String assignTask(int eventId, int taskId) {

		
		
		
		
		Events events = new Events();
		events.setEventId(eventId);
		events.setEventName("Asmsndms");
		System.out.println(events.getEventId());
		String urlForTaskById= BASEURL+"/tasks/taskId/"+taskId;
		Task taskById= restTemplate.getForEntity(urlForTaskById, Task.class).getBody();
		System.out.println("-------------------------------------");
		System.out.println("getByTaskId"+ taskById);
		System.out.println("-------------------------------------");
		taskById.setStatus(Status.IN_PROGRESS);
		taskById.setEvents(events);
		System.out.println("-------------------------------------");
		System.out.println("after updating "+taskById.getEvents().getEventId());
		//System.out.println(eventById.getIconicBooking().getCompanyId());
		String url= BASEURL+"/tasks";
		
		
		HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Task> requestEntity = new HttpEntity<>(taskById, requestHeaders);
        ResponseEntity<Task> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                requestEntity,
                Task.class
        );
		
		
//		System.out.println(responseEntity.getStatusCodeValue());
//		System.out.println(responseEntity.getBody());
		
		return "assigned task";
		
	}

	@Override
	public String releaseTask(int taskId) {

		
		String urlForTaskById= BASEURL+"/tasks/taskId/"+taskId;
		Task taskById= restTemplate.getForEntity(urlForTaskById, Task.class).getBody();
		System.out.println("-------------------------------------");
		System.out.println("getByTaskId"+ taskById);
		System.out.println("-------------------------------------");
		taskById.setStatus(Status.NOT_STARTED);
		taskById.setEvents(null);
		System.out.println("-------------------------------------");
		//System.out.println("after updating "+taskById.getEvents().getEventId());
		//System.out.println(eventById.getIconicBooking().getCompanyId());
		String url= BASEURL+"/tasks";
		
		
		HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Task> requestEntity = new HttpEntity<>(taskById, requestHeaders);
        ResponseEntity<Task> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                requestEntity,
                Task.class
        );
        
        return "event is assigned to be free";
	}		

	
	



	@Override
	public Task addTask(Task task) {
		HttpHeaders httpHeader= new HttpHeaders();
		httpHeader.setContentType(MediaType.APPLICATION_JSON);
		httpHeader.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity httpEntity= new HttpEntity(task, httpHeader);
		String url= BASEURL+"/tasks";
		ResponseEntity<Task> taskAdded= restTemplate.exchange(url, HttpMethod.POST,httpEntity, Task.class);
		return taskAdded.getBody();
	}
}
