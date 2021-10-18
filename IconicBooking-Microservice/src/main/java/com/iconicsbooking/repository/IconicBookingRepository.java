package com.iconicsbooking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import com.iconicsbooking.model.IconicBooking;
@Repository
public interface IconicBookingRepository extends JpaRepository<IconicBooking, Integer>{
	List<IconicBooking> findByCompanyName(String companyName);
	List<IconicBooking> findByOwnerName(String ownerName);
	List<IconicBooking> findByRatingLessThan(double rating);
}
