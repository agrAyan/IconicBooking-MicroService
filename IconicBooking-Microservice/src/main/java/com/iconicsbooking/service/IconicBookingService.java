package com.iconicsbooking.service;

import java.time.LocalDate;
import java.util.List;

import com.iconicsbooking.exception.CompanyNotFoundException;
import com.iconicsbooking.exception.EventsNotFoundException;
import com.iconicsbooking.model.Events;
import com.iconicsbooking.model.IconicBooking;

public interface IconicBookingService {
	IconicBooking addComapny(IconicBooking company);

	IconicBooking updateCompany(IconicBooking company);

	void deleteCompany(int companyId);

	List<IconicBooking> getAll();

	IconicBooking getById(int companyId);

	List<IconicBooking> getByCompanyName(String companyName) throws CompanyNotFoundException;

	List<IconicBooking> getByOwnerName(String ownerName) throws CompanyNotFoundException;

	List<IconicBooking> getByRating(double rating) throws CompanyNotFoundException;

	Events addEvent(Events events) ;

	List<Events> getAllEvents();

	Events getByEventId(int eventId);

	List<Events> getByEventProvider(String eventProvider) throws EventsNotFoundException;

	List<Events> getByEventName(String eventName) throws EventsNotFoundException;

	List<Events> getByStartDate(String startDate) throws EventsNotFoundException;

	List<Events> getByEndDate(String endDate) throws EventsNotFoundException;

	List<Events> getByStatus(String status)throws EventsNotFoundException;

	List<Events> getByPrice(double price) throws EventsNotFoundException;

	List<Events> getByPriority(String priority)throws EventsNotFoundException;

	// List<Events> getByStatus(Availability avail);
	int assignEvent(int companyId, int eventId) throws EventsNotFoundException;
	
	int freeEvent(int eventId) throws EventsNotFoundException;

}
