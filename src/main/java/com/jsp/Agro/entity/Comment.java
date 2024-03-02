package com.jsp.Agro.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Comment {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String comment;
	@OneToOne
	private User user;
}
