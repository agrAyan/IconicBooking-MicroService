package com.iconicsbooking.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class Workers {
	@Id
	@GeneratedValue(generator = "worker_gen",strategy=GenerationType.AUTO)
	@SequenceGenerator(name="worker_gen",sequenceName="worker_seq",initialValue=1,allocationSize = 1)
	private Integer workerId;
	@Column(length=20)
	private String workerName;
	@Column(length=20)
	private String contactNumber;
	@Column(length=20)
	@Enumerated(EnumType.STRING)
	private Availability workerStatus;
	
	private String jobType;
	
	
	@ManyToOne
	@JoinColumn(name="task_id")
	//@JsonIgnore
	Task taskService;

	
	
}
