package com.iconicsbooking.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.iconicsbooking.exception.CompanyNotFoundException;
import com.iconicsbooking.exception.EventsNotFoundException;
import com.iconicsbooking.exception.IdNotFoundException;
import com.iconicsbooking.model.Events;
import com.iconicsbooking.model.IconicBooking;
import com.iconicsbooking.service.IconicBookingService;

@RestController
@RequestMapping("/company-api")
public class IconicsController {
	@Autowired
	IconicBookingService iBookingService;
	
	@PostMapping("/company")
	IconicBooking addCompany(@RequestBody IconicBooking iconicBooking)
	{
	return iBookingService.addComapny(iconicBooking);
	}
	
	@PutMapping("/company")
	IconicBooking updateCompany(@RequestBody IconicBooking iconicBooking)
	{
	return iBookingService.updateCompany(iconicBooking);
	}
	
	@DeleteMapping("/company/{companyId}")
	void deleteCompany(@PathVariable("companyId") int companyId)
	{
		iBookingService.deleteCompany(companyId);
	}
	@GetMapping("/company")
	List<IconicBooking> getAll()
	{
		return iBookingService.getAll();
	}
	
	
	
	@GetMapping("/company/companyId/{companyId}")
	ResponseEntity<IconicBooking> getById(@PathVariable("companyId") int companyId) throws IdNotFoundException
	{
		IconicBooking comapanyById = iBookingService.getById(companyId);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc","Get one event by Id");
		headers.add("info","Returning one event");
		ResponseEntity<IconicBooking> responseCompany = new ResponseEntity<IconicBooking>(comapanyById,headers,HttpStatus.OK);
		return responseCompany;
	}
	@GetMapping("/company/companyName/{companyName}")
	ResponseEntity<List<IconicBooking>> getByCompanyName(@PathVariable("companyName") String companyName)
	{
		List<IconicBooking> companyByname = iBookingService.getByCompanyName(companyName);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc","Get eventList By company name");
		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(companyByname);
	}
	@GetMapping("/company/rating/{rating}")
	ResponseEntity<List<IconicBooking>> getByRating(@PathVariable("rating") double rating)
	{
		List<IconicBooking> companyByRating = iBookingService.getByRating(rating);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc","Get eventList By company rating");
		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(companyByRating);
	}
	@GetMapping("/company/ownerName/{ownerName}")
	ResponseEntity<List<IconicBooking>> getByOwnerName(@PathVariable("ownerName") String ownerName) throws CompanyNotFoundException
	{
		List<IconicBooking> companyByOwnerName = iBookingService.getByOwnerName(ownerName);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc","Get eventList By company owner name");
		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(companyByOwnerName);
	}
	
	@JsonProperty
	@PostMapping("/company/events")
	public ResponseEntity<Events> addEvent(@RequestBody Events events) {
		System.out.println(events);
		Events eventAdded= iBookingService.addEvent(events);
		return ResponseEntity.status(HttpStatus.OK).body(eventAdded);
	}
	@GetMapping("/company/events")
	public ResponseEntity<List<Events>> getAllEvents() {
		List<Events> allEvent= iBookingService.getAllEvents();
		return ResponseEntity.status(HttpStatus.OK).body(allEvent);
	}
	@GetMapping("/company/events/eventId/{eventId}")
	ResponseEntity<Events> getByEventId(@PathVariable("eventId") int eventId) throws EventsNotFoundException{
	return ResponseEntity.status(HttpStatus.OK).body(iBookingService.getByEventId(eventId));
	}
	@GetMapping("/company/events/eventProvider/{eventProvider}")
	ResponseEntity<List<Events>> getByEventProvider(@PathVariable("eventProvider") String eventProvider){
		return ResponseEntity.status(HttpStatus.OK).body(iBookingService.getByEventProvider(eventProvider));
	}
	@GetMapping("/company/events/eventName/{eventName}")
  ResponseEntity< List<Events>> getByEventName(@PathVariable("eventName") String eventName){
	   return ResponseEntity.status(HttpStatus.OK).body(iBookingService.getByEventName(eventName));
   }
	@GetMapping("/company/events/startDate/{startDate}")
  ResponseEntity<List<Events>> getByStartDate(@PathVariable("startDate") String startDate){
		 return ResponseEntity.status(HttpStatus.OK).body(iBookingService.getByStartDate(startDate));
   }
	@GetMapping("/company/events/endDate/{endDate}")
    ResponseEntity<List<Events>> getByEndDate(@PathVariable("endDate") String endDate){
    	 return ResponseEntity.status(HttpStatus.OK).body(iBookingService.getByEndDate(endDate));
    }
	@GetMapping("/company/events/status/{status}")
    ResponseEntity<List<Events>> getByStatus(@PathVariable("status") String status){
    	 return ResponseEntity.status(HttpStatus.OK).body(iBookingService.getByStatus(status));
	}
	@GetMapping("/company/events/price/{price}")
    ResponseEntity<List<Events>>  getByPrice(@PathVariable("price") double price){
		 return ResponseEntity.status(HttpStatus.OK).body(iBookingService.getByPrice(price));
    }
	@GetMapping("/company/events/priority/{priority}")
    ResponseEntity<List<Events>> getByPriority(@PathVariable("priority") String priority){
    	 return ResponseEntity.status(HttpStatus.OK).body(iBookingService.getByPriority(priority));
    }
	@GetMapping("/company/events/assign/companyId/{companyId}/eventId/{eventId}")
	ResponseEntity<Integer> assignEvent(@PathVariable("companyId") int companyId, @PathVariable("eventId") int eventId) {
		return ResponseEntity.status(HttpStatus.OK).body(iBookingService.assignEvent(companyId, eventId));
	}
	
	@GetMapping("/company/events/freeEvent/eventId/{eventId}")
	ResponseEntity<Integer> freeEvent(@PathVariable("eventId") int eventId) {
		return ResponseEntity.status(HttpStatus.OK).body(iBookingService.freeEvent(eventId));
	}
	
	
}