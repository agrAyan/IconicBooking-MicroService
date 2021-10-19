package com.iconicsbooking.model;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Events {

	@Id
	@GeneratedValue(generator = "event_gen", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "event_gen", sequenceName = "event_seq", initialValue = 100, allocationSize = 1)
	private Integer eventId;
	private String eventName;
	private String eventProvider;
	
	private LocalDate startDate;
	private LocalDate endDate;
	@Enumerated(EnumType.STRING)
	private Priority priority;
	@Enumerated(EnumType.STRING)
	private Status status;
	private double price;
	@ManyToOne
	@JoinColumn(name = "company_id")
	//@JsonIgnore
	IconicBooking iconicBooking;

	@OneToMany
	@JoinColumn(name = "event_id")
	@JsonIgnore
	Set<Task> taskServiceList;
//bye
	public Events(String eventName, String eventProvider, LocalDate startDate, LocalDate endDate, Priority priority,
			Status status, double price) {
		super();
		this.eventName = eventName;
		this.eventProvider = eventProvider;
		this.startDate = startDate;
		this.endDate = endDate;
		this.priority = priority;
		this.status = status;
		this.price = price;
		// this.iconicBooking = iconicBooking;
	}

	@Override
	public String toString() {
		return "Events [eventName=" + eventName + ", eventProvider=" + eventProvider + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", priority=" + priority + ", status=" + status + ", price=" + price
				+ ", iconicBooking=" + iconicBooking + "]";
	}

}
