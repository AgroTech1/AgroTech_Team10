package com.jsp.Agro.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.Agro.entity.Post;
import com.jsp.Agro.service.PostService;
import com.jsp.Agro.util.ResponseStructure;

@RestController
public class PostController {
	

		@Autowired
		private PostService service;
		@PostMapping("/savePost")
//		
		public ResponseEntity<ResponseStructure<Post>>savePost(@RequestParam int id,String name, String location ,@RequestParam MultipartFile file,String caption)throws IOException{
			return service.savePost(id, file, location, caption,name);
			
		}
		
		@GetMapping("/fetchPostById")
		public ResponseEntity<ResponseStructure<Post>> fetchPostById(@RequestParam int id) {
			
			return service.fetchPostById(id);
		}
		
//		@DeleteMapping("/deletePostById")
//		public ResponseEntity<ResponseStructure<Post>> deletePostById(@RequestParam int id) {
//			
//			return service.deletePostById(id);
//		}
	}





