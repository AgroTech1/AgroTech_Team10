package com.jsp.Agro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.Agro.entity.Comment;
import com.jsp.Agro.service.CommentService;
import com.jsp.Agro.util.ResponseStructure;
@RestController
public class CommentController {
		@Autowired 
		private CommentService  service;
		@PostMapping("/saveComment")
		public ResponseEntity<ResponseStructure<Comment>> uploadComment(int userid,int postid,String message){
			return service.saveComment(userid, postid, message);
		}
		
//		@GetMapping("/DeleteComment")
//		public ResponseEntity<ResponseStructure<Comment>>deleteComment(@RequestParam int id){
//			return service.deleteComment(id);
//		}
		

	}


