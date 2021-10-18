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
	@GeneratedValue(generator = "task_gen",strategy=GenerationType.AUTO)
	@SequenceGenerator(name="task_gen",sequenceName="task_seq",initialValue=1,allocationSize = 1)
	private Integer taskId;
	@Column(length=20)
	private String taskName;
	@Column(length=20)
	private String organiser;
	private double rating;
	private LocalDate startDate;
	private LocalDate endDate;
	private int duration;
	@Enumerated(EnumType.STRING)
	private Status status;
	@ManyToOne
	@JoinColumn(name="event_id")
	Events events;
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="task_id")
	@JsonIgnore
    private Set<Workers> workersList;
	
	
	
//	public Task(String taskName, String organiser, double rating, LocalDate startDate, LocalDate endDate,
//			int duration, Status status) {
//		super();
//		this.taskName = taskName;
//		this.organiser = organiser;
//		this.rating = rating;
//		this.startDate = startDate;
//		this.endDate = endDate;
//		this.duration = duration;
//		this.status = status;
//	}
	@Override
	public String toString() {
		return "TaskService [taskName=" + taskName + ", organiser=" + organiser + ", rating=" + rating + ", startDate="
				+ startDate + ", endDate=" + endDate + ", duration=" + duration + ", status=" + status + "]";
	}
	

	
	
	
	

}
