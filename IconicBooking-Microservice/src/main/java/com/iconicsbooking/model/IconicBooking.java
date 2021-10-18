package com.iconicsbooking.model;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@ToString
@Entity
public class IconicBooking {
	@Id
	@GeneratedValue(generator = "iconic_gen", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "iconic_gen", sequenceName = "iconic_seq", initialValue = 100, allocationSize = 1)
	private Integer companyId;
	private String companyName;
	private String ownerName;
	private double rating;
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "company_id")
	@JsonIgnore
	Set<Events> event;
	public IconicBooking(String companyName, String ownerName, double rating) {
		super();
		this.companyName = companyName;
		this.ownerName = ownerName;
		this.rating = rating;
		//this.event = event;
	}
	
    
}
