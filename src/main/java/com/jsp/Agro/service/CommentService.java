package com.jsp.Agro.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.Agro.dao.CommentDao;
import com.jsp.Agro.dao.PostDao;
import com.jsp.Agro.dao.UserDao;
import com.jsp.Agro.entity.Comment;
import com.jsp.Agro.entity.Post;
import com.jsp.Agro.entity.User;
import com.jsp.Agro.exception.UserNotFoundException;
import com.jsp.Agro.util.ResponseStructure;

@Service
public class CommentService {
		@Autowired
		private PostDao dao;
		@Autowired
		private UserDao udao;
		@Autowired
		private CommentDao cdao;
		
		public ResponseEntity<ResponseStructure<Comment>> saveComment(int userid,int postid,String  message){
			Post postDb = dao.fetchPostById(postid);
			if(postDb!=null) {
				User userDb = udao.fetchUser(userid);
				if(userDb!=null) {
				Comment commentDb = cdao.saveComment(message, userDb);
				List<Comment> list=new ArrayList<Comment>();
				list.add(commentDb);
				list.addAll(postDb.getListComment());          
				postDb.setListComment(list);
				System.out.println("service");
				dao.updatePost(postDb);
				System.out.println("after service");
				ResponseStructure<Comment> r=new ResponseStructure<Comment>();
				r.setData(commentDb);
				r.setMessage("comment saved successfully....!");
				r.setStatus(HttpStatus.CONTINUE.value());
				return new ResponseEntity<ResponseStructure<Comment>>(r,HttpStatus.CONTINUE);
				}else
					throw new UserNotFoundException("user not found for id : "+userid);
			
			}else
				throw new UserNotFoundException("post not found for id: "+postid);
		}

		
//		public ResponseEntity<ResponseStructure<Comment>> deleteComment(int id){
//			Comment commentDb = cdao.fetchCommentById(id);
//			if(commentDb!=null) {
//				List<Post> post=dao.fetchPostAll();
//				for(Post p:post) {
//					System.out.println("inside for each");
//					List<Comment> comment=p.getComment();
//					if(comment.contains(commentDb)) {
//						comment.remove(post);
//						dao.updatePost(p);
//						System.out.println("before delete");
//						cdao.deleteComment(id);
//						System.out.println("after delete");
//					}
//					}
//				ResponseStructure<Comment> r=new ResponseStructure<Comment>();
//				r.setData(commentDb);
//				r.setMessage("comment deleted successfully....!");
//				r.setStatus(HttpStatus.CONTINUE.value());
//				return new ResponseEntity<ResponseStructure<Comment>>(r,HttpStatus.CONTINUE);
//			}
//			else {
//				throw new  CommentNotFoundException("comment not found for id : "+id);
//			}
//				
//		}
	}


