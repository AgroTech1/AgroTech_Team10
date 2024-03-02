package com.jsp.Agro.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.jsp.Agro.Repo.AddressRepo;
import com.jsp.Agro.dao.AddressDao;
import com.jsp.Agro.dao.UserDao;
import com.jsp.Agro.entity.User;
import com.jsp.Agro.exception.UserNotFoundException;
import com.jsp.Agro.util.ResponseStructure;

@Service
public class UserService {
 @Autowired
	private UserDao dao;
 
	@Autowired
	private JavaMailSender mail;
	
	
	public ResponseEntity<ResponseStructure<User>> saveUserdetails(User user){
	
		ResponseStructure<User> m = new ResponseStructure<>();
		m.setData(dao.saveUser(user));
		m.setMessage("successfully register user details...");
		m.setStatus(HttpStatus.CREATED.value());
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setFrom("chittiprudhvi19@gmail.com");
		sm.setTo(user.getEmail());
		sm.setText("WELCOME TO AGRO Tech--->G10 "+user.getFirstName());
		sm.setSubject(user.getEmail()+" registration is successfully completed......");
		mail.send(sm);
		return new ResponseEntity<ResponseStructure<User>>(m,HttpStatus.CREATED);
	}
	
	
	public ResponseEntity<ResponseStructure<Integer>> sendOtp(String email){
		User db = dao.fetchByEmail(email);
		if(db!=null) {
		Random random = new Random();
		int value = random.nextInt();
		ResponseStructure<Integer> m = new ResponseStructure<>();
		m.setData(value);
		m.setMessage("otp sent successfully");
		m.setStatus(HttpStatus.FOUND.value());
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom("sakesivakumar7799@gmail.com");
		msg.setTo(email);
		msg.setSubject("otp verification");
		msg.setText("please enter otp: " + value);
		mail.send(msg);
		return new ResponseEntity<ResponseStructure<Integer>>(m,HttpStatus.FOUND);
		
		}else {
			return null;
		}
		
	}
 
 public ResponseEntity<ResponseStructure<User>>saveUser(@RequestBody User user){
	ResponseStructure<User> m =new ResponseStructure<User>();
	m.setData(dao.saveUser(user));
	m.setMessage("User saved successfully...!");
	m.setStatus(HttpStatus.CREATED.value());
	return new ResponseEntity<ResponseStructure<User>>(m,HttpStatus.CREATED);
 }
 
 //fetch
 public ResponseEntity<ResponseStructure<User>> fetchUser(int id) {
		User db = dao.fetchUser(id);
		if (db != null) {
			ResponseStructure<User> m = new ResponseStructure<User>();
			m.setData(db);
			m.setMessage("msg is successed...");
			m.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<User>>(m, HttpStatus.FOUND);
		} else {
			throw new UserNotFoundException("search:" + id);
		}
	}
//update
 public ResponseEntity<ResponseStructure<User>> updateUser(User user) {
		User db = dao.updateUser(user);
		if (db != null) {
			ResponseStructure<User> structure = new ResponseStructure<User>();
			structure.setData(db);
			structure.setMessage("user updated successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.FOUND);
		}

		throw new UserNotFoundException();
	}
//delete
 public ResponseEntity<ResponseStructure<User>> DeleteById(int id) {
	  User db = dao.DeleteById(id);
	if (db != null) {
		ResponseStructure<User> m = new ResponseStructure<User>();
		m.setData(db);
		m.setMessage("msg is successed...");
		m.setStatus(HttpStatus.FOUND.value());
		return new ResponseEntity<ResponseStructure<User>>(m, HttpStatus.FOUND);
	} else {
		throw new UserNotFoundException("search:" + id);
	}
}
 
// fetchAll 
 public ResponseEntity<ResponseStructure<List<User>>> fetchAll() {
		List<User> db = dao.FetchAll();
		if (db.size() != 0) {
			ResponseStructure<List<User>> structure1 = new ResponseStructure<List<User>>();
			structure1.setData(db);
			structure1.setMessage("user fetched successfully");
			structure1.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<List<User>>>(structure1, HttpStatus.FOUND);
		}
		throw new UserNotFoundException("search:all data");
	}
	

 


}