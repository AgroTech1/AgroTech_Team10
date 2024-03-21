package com.jsp.Agro.entity;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Component
public class PaymentHistory {
@Id
 private int id;
 private String mode;
 private String PaymentTime;
 private double Amount;
 @ManyToOne
 private User user;
 
 

}
