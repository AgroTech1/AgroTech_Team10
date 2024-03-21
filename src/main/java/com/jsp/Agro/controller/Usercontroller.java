 package com.jsp.Agro.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.Agro.entity.User;
import com.jsp.Agro.service.UserService;
import com.jsp.Agro.util.ResponseStructure;

@RestController
public class Usercontroller{
	@Autowired
	private UserService service;
	
 @PostMapping("/Agro")//>>>>save And mail&register<<<<<<<<<
	public ResponseEntity<ResponseStructure<User>>saveUser(@RequestBody User user){
		return service.saveUser(user);
 }
 
 
//>>>>>>>>>>>>fetch<<<<<<<<<<<<,,
 @GetMapping("/Agro")
	public ResponseEntity<ResponseStructure<User>>fetchStudent(@RequestParam int id){
		return service.fetchUser(id);
	}
 
 
//>>>>>>>>>>>>update<<<<<<<<<<<<<<
 @PutMapping("/Agro")
	public ResponseEntity<ResponseStructure<User>> updateUser(@RequestBody User user) {
		return service.updateUser(user);
	}
 
//>>>>>>>>>>>>>delete<<<<<<<<<<<<<
 @DeleteMapping("/Agro")
	public ResponseEntity<ResponseStructure<User>>DeleteById(@RequestParam int id){
		return service.DeleteById(id);
	}
//>>>>>>>>>>>>>>> fetchAll <<<<<<<<<<<<<<<<,
 @GetMapping("/fetchAll")
	public ResponseEntity<ResponseStructure<List<User>>> fetchAll() {
		return service.fetchAll();
	}
 //>>>>>>fetch by email<<<<<<<<<<
 @GetMapping("/fetchbyemail")
	public ResponseEntity<ResponseStructure<User>>fetchbyEmail(@RequestParam String email){
		return service.fetchbyemail(email);
	 
 }
//>>>>>Generating otp <<<<<<
 @GetMapping("/otp")
	public ResponseEntity<ResponseStructure<Integer>> sendOtp(@RequestParam String email){
		return service.sendOtp(email);
	}
 
 
 public String imageSave(@RequestParam MultipartFile file,@RequestParam String description) throws IOException {
	return service.imageSave(file, description);
	 
 }
	
}