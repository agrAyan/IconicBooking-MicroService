package com.iconicsbooking.service;

import java.time.LocalDate;
import java.util.List;

import com.iconicsbooking.model.Availability;
import com.iconicsbooking.model.Events;
import com.iconicsbooking.model.Priority;
import com.iconicsbooking.model.Status;
import com.iconicsbooking.model.Task;

public interface IEventService {
	Events addEvent(Events events);

	Events updateEvent(Events events);

	void deleteEvent(int eventId);

	List<Events> getAll();

	Events getById(int eventId);

	List<Events> getByEventProvider(String  eventProvider);
    List<Events> getByEventName(String eventName);
    List<Events> getByStartDate(LocalDate startDate);
    List<Events> getByEndDate(LocalDate endDate);
    List<Events> getByStatus(String status);
    List<Events> getByPrice(double price);
    List<Events> getByPriority(String priority);
    Task getBytaskId(int taskId);
	
	Task addTask(Task task);
	List<Task>getAllTasks();
	List<Task> getByOrganiser(String organiserName);
	List<Task>getByTaskName(String taskName);
	
	List<Task>getByTaskStartDate(LocalDate startDate);
	List<Task>getByTaskEndDate(LocalDate endDate);
	List<Task>getByRating(double rating);
	List<Task>getByStatus(Status status);
	List<Task> getByDuration(int duration);
	String assignTask(int eventId,int taskId);
	String releaseTask( int taskId);
    
    
    
    
    
    
    
    
}
