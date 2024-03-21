package com.jsp.Agro.service;

import java.io.IOException;
import java.util.List;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.Agro.Repo.ImageRepo;
import com.jsp.Agro.dao.UserDao;
import com.jsp.Agro.entity.Image;
import com.jsp.Agro.entity.User;
import com.jsp.Agro.exception.EmailNotFoundException;
import com.jsp.Agro.exception.UserNotFoundException;
import com.jsp.Agro.util.ResponseStructure;

@Service
public class UserService {
 @Autowired
	private UserDao dao;
 
	@Autowired
	private JavaMailSender mail;
	
	@Autowired
	private ImageRepo repo;
	
	
     //>>>>>>>>>For saving<<<<<<<<<<<<<<<<<<<<<<
	//>>>>>>>>>>>>>MailSending<<<<<<<<<<<<<<<<<<<<<
	
	public ResponseEntity<ResponseStructure<User>> saveUser (User user){
		ResponseStructure<User> m =new ResponseStructure<User>();
		m.setMessage("message Send Successfully!❤️❤️😂🤷‍♂️🤷‍♂️");
		m.setStatus(HttpStatus.CREATED.value());
		m.setData(dao.saveUser(user));
		
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setFrom("prudhvichitti378@gmail.com");
		sm.setTo(user.getEmail());
		sm.setSubject(user.getEmail()+"user register is successfully completed!🙇‍♂️🙇‍♂️🙇‍♂️🙇‍♂️🙇‍♂️🙇‍♂");
		mail.send(sm);
	  return new ResponseEntity<ResponseStructure<User>>(m,HttpStatus.CREATED);
		
	}
	
//	public ResponseEntity<ResponseStructure<User>>saveUser(User user){
//		ResponseStructure<User> m = new ResponseStructure<User>();
//		m.setMessage("save successfully....😂😂😂!!!");
//		m.setStatus(HttpStatus.CREATED.value());
//		m.setData(dao.saveUser(user));
//		
//		SimpleMailMessage sm = new SimpleMailMessage();
//		sm.setFrom("prudhvichitti378@gmail.com");
//		sm.setTo(user.getEmail());
//		sm.setText("Welcome To AgroTech😂😂😂😂😂❤️❤️.....!!!!");
//		sm.setSubject(user.getEmail()+"user Register is successfully completed");
//		mail.send(sm);
//		return new ResponseEntity<ResponseStructure<User>>(m,HttpStatus.CREATED);
		
//	}

	
//	>>>>>>>>>>>>>>>>>>>>>>>>>Generate OTP<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	
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
		msg.setFrom("prudhvichitti378@gmail.com");
		msg.setTo(email); 
		msg.setSubject("otp verification");
		msg.setText("please enter otp: " + value);
		mail.send(msg);
		return new ResponseEntity<ResponseStructure<Integer>>(m,HttpStatus.FOUND);
		
		}else {
			return null;
		}
		
	}
	
	//>>>>>>>>>>>>>>Log in<<<<<<<<<<<<<<<<<<
	public ResponseEntity<ResponseStructure<User>>loginUser(String email,String pwd){
		User db =dao.fetchByEmail(email);
		if(db !=null) {
			if(db.getPwd().equals(pwd)) {
				ResponseStructure<User> m1 =new ResponseStructure<User>();
				m1.setData(db);
				m1.setMessage("successfully login..........");
				m1.setStatus(HttpStatus.FOUND.value());
				return new ResponseEntity<ResponseStructure<User>>(m1,HttpStatus.FOUND);
			}else {
				System.out.println("password incorrect");
			}
		}else {
			System.out.println("Email Incorrect");
			throw new EmailNotFoundException("email not found");
		}
		return null;
	}
	
//>>>>>>>>>>>>>>>>>>>>fetch<<<<<<<<<<<<<<<<<<<<<<<<
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
 
//>>>>>>>>>>>>>>>>>>>>>>>update<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
 public ResponseEntity<ResponseStructure<User>> updateUser(User user) {
		User db = dao.updateUser(user);
		if (db != null) {
			ResponseStructure<User> structure = new ResponseStructure<User>();
			structure.setData(db);
			structure.setMessage("user updated successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.FOUND);
		}else {

		throw new UserNotFoundException("search:"+user.getId());
	}
 }
//>>>>>>>>>>>>>>>>>>>>>>>>>>.delete<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
 public ResponseEntity<ResponseStructure<User>> DeleteById(int id) {
	  User db = dao.DeleteById(id);
	if (db != null) {
		ResponseStructure<User> m = new ResponseStructure<User>();
		m.setData(db);
		m.setMessage("msg is successed...");
		m.setStatus(HttpStatus.FOUND.value());
		return new ResponseEntity<ResponseStructure<User>>(m, HttpStatus.FOUND);
	} else {
		throw new UserNotFoundException("search:" +id);
	}
}
 
//>>>>>>>>>>>>>>>>>>> fetchAll <<<<<<<<<<<<<<<<<<<<<<<<<
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
 
 //>>>>>fetch by email<<<<<<
 public ResponseEntity<ResponseStructure<User>>fetchbyemail(String email){
	 User db=dao.fetchByEmail(email);
	 if(db !=null) {
		 ResponseStructure<User> m = new ResponseStructure<User>();
		 m.setData(db);
		 m.setMessage("msg is successed");
		 m.setStatus(HttpStatus.FOUND.value());
		 return new ResponseEntity<ResponseStructure<User>>(m,HttpStatus.FOUND);
	 }else {
	throw new UserNotFoundException("search:");
	 
 }
}
// >>>>> Image saving in database purpose<<<<<<<<<<<<<<
 public String imageSave(MultipartFile file,String description)throws IOException {
	 Image  pic =new Image();
	 pic.setDescription(description);
	 pic.setData(file.getBytes());
	 repo.save(pic);
	return "Image saved successfully";
 }
 }
