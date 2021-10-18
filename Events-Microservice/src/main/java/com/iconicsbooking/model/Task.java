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

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Task {

	@Id
	@GeneratedValue(generator = "task_gen", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "task_gen", sequenceName = "task_seq", initialValue = 100, allocationSize = 1)
	private Integer taskId;
	private String taskName;
	private String organiser;
	private double rating;
	private LocalDate startDate;
	private LocalDate endDate;
	private int duration;
	@Enumerated(EnumType.STRING)
    private Status status;
	
	@OneToMany
	@JoinColumn(name ="task_id")
	@JsonIgnore
	private Set<Workers> workersList;
    @ManyToOne
    @JoinColumn(name = "event_id")
    Events events;
}
