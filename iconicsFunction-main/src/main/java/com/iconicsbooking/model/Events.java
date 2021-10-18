package com.iconicsbooking.model;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Events {
	@Id
	@GeneratedValue(generator = "event_gen",strategy=GenerationType.AUTO)
	@SequenceGenerator(name="event_gen",sequenceName="event_seq",initialValue=1,allocationSize = 1)
	private Integer eventId;
	@Column(length=20)
	private String eventName;
	@Column(length=20)
	private String eventProvider;
	private LocalDate startDate;
	private LocalDate endDate;
	@Enumerated(EnumType.STRING)
	private Availability availability;
	private double price;
	@Enumerated(EnumType.STRING)
	private Priority priority;
	@OneToMany
	@JoinColumn(name="event_id")
	@JsonIgnore
	private Set<Task> taskServiceList;
	
}
