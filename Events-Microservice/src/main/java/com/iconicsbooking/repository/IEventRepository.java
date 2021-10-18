package com.iconicsbooking.repository;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.iconicsbooking.model.Availability;
import com.iconicsbooking.model.Events;
import com.iconicsbooking.model.Priority;
import com.iconicsbooking.model.Status;

@Repository
public interface IEventRepository extends JpaRepository<Events, Integer>{

	List<Events> findByStatus(Availability avail);
	List<Events> findByEventProvider(String eventProvider);
    List<Events> findByEventName(String eventName);
    List<Events> findByStartDate(LocalDate startDate);
    List<Events> findByEndDate(LocalDate endDate);
    List<Events> findByStatus(Status status);
    List<Events> findByPriceLessThan(double price);
    List<Events> findByPriority(Priority priority);
    
}
