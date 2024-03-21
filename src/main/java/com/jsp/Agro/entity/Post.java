package com.jsp.Agro.entity;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Component
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
private int likes;
private LocalDate date;
private String caption;
private String location;
private LocalTime time;
@OneToMany
private List<Comment>listComment;
@OneToOne(cascade = CascadeType.ALL)
private Image image;

}
