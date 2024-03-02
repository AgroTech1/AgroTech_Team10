package com.jsp.Agro.entity;

import java.util.List;

import com.jsp.Agro.enums.UserType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType .IDENTITY)
	private int id;
	private String firstName;
	private String lastName;
	private String gender;
	private int age;
	private long phone;
	@Column(unique = true)
	private String email;
	private String pwd;
	@Enumerated(EnumType.STRING)
	private UserType userType;
	@OneToOne(cascade = CascadeType.ALL)//Address once update we go for cascade
	private Address address;
	@OneToOne(cascade = CascadeType.ALL)
	private Image image;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Post> post;
}
