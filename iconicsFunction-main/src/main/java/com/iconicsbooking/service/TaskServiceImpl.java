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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.iconicsbooking.exception.TaskNotFoundException;
import com.iconicsbooking.model.Availability;
import com.iconicsbooking.model.Events;
import com.iconicsbooking.model.Status;
import com.iconicsbooking.model.Task;
import com.iconicsbooking.model.Workers;
import com.iconicsbooking.repository.TaskServiceRepository;

@Service
@Transactional
public class TaskServiceImpl implements ITaskService{
	
	/*here we are injecting instance of ITaskRepository class
       into TaskServiceImpl so that we can use ITaskRepository class for accessing instance variables and methods */

	@Autowired
	TaskServiceRepository taskServiceRepo;
	
	//Autowiring the RestTemplate object that is used to connect with other microservices

	@Autowired
	RestTemplate restTemplate;
	
	String status=Status.IN_PROGRESS.toString();
	String nstatus=Status.COMPLETED.toString();

	
	public static final String BASEURL="http://localhost:8084/worker-api";

	public void setTaskServiceRepo(TaskServiceRepository taskServiceRepo) {
		this.taskServiceRepo = taskServiceRepo;
	}

	// this  Method is used for adding tasks
	@Override
	public Task addTask(Task taskService) {
		// TODO Auto-generated method stub
		return taskServiceRepo.save(taskService);
	}

	// this  Method is used for getting taskId
	@Override
	public Task getBytaskId(int taskId) {
		// TODO Auto-generated method stub
		return taskServiceRepo.findById(taskId).orElseThrow(() -> new TaskNotFoundException("invalid id"));
	}
	
       // this  Method is used for deleting tasks
	@Override
	public void deleteTask(int taskId) {
		 taskServiceRepo.deleteById(taskId);
		
	}

	//this Method is used for update task
	@Override
	public Task updateTask(Task task) {
		return taskServiceRepo.save(task);
		
	}

	//this Method is used for get all tasks
	@Override
	public List<Task> getAllTasks() {
		// TODO Auto-generated method stub
		return taskServiceRepo.findAll();
	}

	//this Method is used for get organiser in task
	@Override
	public List<Task> getByOrganiser(String organiserName) {
		// TODO Auto-generated method stub
		List<Task> taskByOrganiser= taskServiceRepo.findByOrganiser(organiserName);
		if(taskByOrganiser.isEmpty())
			throw new TaskNotFoundException("no organiser found");
		return taskByOrganiser;
	}

	//this Method is used for get taskname in task
	@Override
	public List<Task> getByTaskName(String taskName) {
		// TODO Auto-generated method stub
		List<Task> taskByTaskName= taskServiceRepo.findByTaskName(taskName);
		if(taskByTaskName.isEmpty())
			throw new TaskNotFoundException("no task by this name found");
		return taskByTaskName;
	}

	//this Method is used for get start Task date
	@Override
	public List<Task> getByTaskStartDate(LocalDate startDate) {
		List<Task> taskByStartDate= taskServiceRepo.findByStartDate(startDate);
		if(taskByStartDate.isEmpty())
			throw new TaskNotFoundException("no task by this name found");
		return taskByStartDate;
	}

	//this Method is used for get end Task date
	@Override
	public List<Task> getByTaskEndDate(LocalDate endDate) {
		List<Task> taskByEndDate= taskServiceRepo.findByEndDate(endDate);
		if(taskByEndDate.isEmpty())
			throw new TaskNotFoundException("no task by this name found");
		return taskByEndDate;
	}

	//this Method is used for get rating
	@Override
	public List<Task> getByRating(double rating) {
		List<Task> taskByRating= taskServiceRepo.findByRating(rating);
		if(taskByRating.isEmpty())
			throw new TaskNotFoundException("no task by this name found");
		return taskByRating;
	}

	//this Method is used for get status
	@Override
	public List<Task> getByStatus(Status status) {
		List<Task> taskByStatus= taskServiceRepo.findByStatus(status);
		if(taskByStatus.isEmpty())
			throw new TaskNotFoundException("no task by this name found");
		return taskByStatus;
	}
	
	//this Method is used for get duration
	@Override
	public List<Task> getByDuration(int duration) {
		List<Task> taskByDuration= taskServiceRepo.findByDuration(duration);
		if(taskByDuration.isEmpty())
			throw new TaskNotFoundException("no task by this name found");
		return taskByDuration;
	}
	
	
	//this Method is used for get worker status
@Override
	public List<Workers> getByWorkerStatus(String status){
	 	String url= BASEURL+"/worker/status/"+status;	
	 	ResponseEntity<List> response=restTemplate.getForEntity(url, List.class);
		HttpHeaders headers= response.getHeaders();
		List<String> header= headers.get("desc");
		System.out.println(response.getStatusCodeValue()+"    "+header);
		return response.getBody();
 	}

	//this Method is used for get All workers
	
	@Override
	public List<Workers> getAllWorkers(){
	 	String url= BASEURL+"/worker";	
	 	ResponseEntity<List> response=restTemplate.getForEntity(url, List.class);
		HttpHeaders headers= response.getHeaders();
		List<String> header= headers.get("desc");
		System.out.println(response.getStatusCodeValue()+"    "+header);
		return response.getBody();
 	}

	//this Method is used for get worker id
	@Override
	public Workers getByWorkerId(int id) {
		String url= BASEURL+"/worker/workerId/"+id;	
	 	ResponseEntity<Workers> response=restTemplate.getForEntity(url, Workers.class);
		HttpHeaders headers= response.getHeaders();
		List<String> header= headers.get("desc");
		System.out.println(response.getStatusCodeValue()+"    "+header);
		return response.getBody();
	}
	

	
//this Method is used for get worker jobe Type
	@Override
	public List<Workers> getByJobType(String jobType) {
		String url= BASEURL+"/worker/jobType/"+jobType;	
	 	ResponseEntity<List> response=restTemplate.getForEntity(url, List.class);
		HttpHeaders headers= response.getHeaders();
		List<String> header= headers.get("desc");
		System.out.println(response.getStatusCodeValue()+"    "+header);
		return response.getBody();
	}
	
	
	//this method is used for assign the task to workers

	@Override
	public String assignTaskToResource(int taskId, int workerId) {

		
		Task task = new Task();
		task.setTaskId(taskId);
		System.out.println(task.getTaskId());
		String urlForWorkerById= BASEURL+"/worker/workerId/"+workerId;
		Workers workerById= restTemplate.getForEntity(urlForWorkerById, Workers.class).getBody();
		System.out.println(workerById);
		workerById.setWorkerStatus(Availability.NOT_AVAILABLE);
		workerById.setTaskService(task);
		//System.out.println(eventById.getIconicBooking().getCompanyId());
		String url= BASEURL+"/worker";
		
		
		HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Workers> requestEntity = new HttpEntity<>(workerById, requestHeaders);
        ResponseEntity<Events> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                requestEntity,
                Events.class
        );
		
		
		System.out.println(responseEntity.getStatusCodeValue());
		System.out.println(responseEntity.getBody());
		
		return "assigned successfully";
	}

	

	@Override
	public String freeResource(int workerId) {

		
		String urlForWorkerById= BASEURL+"/worker/workerId/"+workerId;
		Workers workerById= restTemplate.getForEntity(urlForWorkerById, Workers.class).getBody();
		
		workerById.setWorkerStatus(Availability.AVAILABLE);
		workerById.setTaskService(null);
		//System.out.println(eventById.getIconicBooking().getCompanyId());
		String url= BASEURL+"/worker";
		
		
		HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Workers> requestEntity = new HttpEntity<>(workerById, requestHeaders);
        ResponseEntity<Events> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                requestEntity,
                Events.class
        );
		
		
		System.out.println(responseEntity.getStatusCodeValue());
		System.out.println(responseEntity.getBody());
		
		return "resources is allocated to free";
		
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
