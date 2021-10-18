package com.iconicsbooking.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
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
	
	private String workerName;
	@Id
	@GeneratedValue(generator = "worker_gen", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "worker_gen", sequenceName = "worker_seq", initialValue = 100, allocationSize = 1)
	private Integer workerId;
	private String contactNumber;
	private String workerStatus;
	private String jobType;
	@ManyToOne
	@JoinColumn(name = "task_id")
	Task taskService;
}
