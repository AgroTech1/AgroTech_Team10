package com.jsp.Agro.entity;

import java.sql.Time;

import java.time.LocalDateTime;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@Entity
@Data
@NoArgsConstructor
public class Rental {
	@Id
	private int id;
	private LocalDateTime startTime;
	private LocalDateTime EndTime;
	@ManyToOne
	private Equipment Equipment;
	@OneToOne
	private PaymentHistory paymentHistory;                             
	
	

}
