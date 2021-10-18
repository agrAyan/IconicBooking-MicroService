package com.iconicsbooking.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import com.iconicsbooking.model.Availability;
import com.iconicsbooking.model.Status;
import com.iconicsbooking.model.Task;
import com.iconicsbooking.model.Workers;

public interface ITaskService {
	
	Task addTask(Task taskService);
	Task getBytaskId(int taskId);
	void deleteTask(int taskId);
	Task updateTask(Task taskService);
	
	List<Task>getAllTasks();
	List<Task> getByOrganiser(String organiserName);
	List<Task>getByTaskName(String taskName);
	
	List<Task>getByTaskStartDate(LocalDate startDate);
	List<Task>getByTaskEndDate(LocalDate endDate);
	List<Task>getByRating(double rating);
	List<Task>getByStatus(Status status);
	List<Task> getByDuration(int duration);
	
	
	
	//	workers ()
	Workers getByWorkerId(int id);
	List<Workers> getByWorkerStatus( String status);
	List<Workers> getAllWorkers();
	//String deleteWorker(int workerId);
	//List<Workers> getByWorkerStatus(Availability status);
	List<Workers> getByJobType(String jobType);
	String assignTaskToResource(int taskId, int workerId);
//	String changeStatus(int workerId);
	String freeResource(int workerId);


	}


