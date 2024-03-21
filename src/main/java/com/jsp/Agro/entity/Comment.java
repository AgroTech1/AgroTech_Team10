package com.jsp.Agro.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Comment {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String msg;
	@ManyToOne(cascade=CascadeType.ALL)
	@JsonIgnore
	private User user;
}



























