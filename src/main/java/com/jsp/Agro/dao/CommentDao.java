package com.jsp.Agro.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.Agro.Repo.CommentRepo;
import com.jsp.Agro.entity.Comment;
import com.jsp.Agro.entity.User;

@Repository
public class CommentDao {
	
		@Autowired
		private CommentRepo  repo;
		

		public Comment saveComment(String comment,User user) {
			Comment c = new Comment();
			c.setMsg(comment);
			c.setUser(user);
			c.setId(c.getId());
			return repo.save(c);
			
			
		}

		public Comment deleteComment(int id) {
			Optional<Comment> db = repo.findById(id);
			if (db.isPresent()) {
				repo.delete(db.get());
				
				return db.get();
			}
			else {
				return null;
			}
			
		}
		
		public Comment fetchCommentById(int id) {
			Optional<Comment> db = repo.findById(id);
			if(db.isPresent()) {
				return db.get();
			}
			return null;
			
		}

	}


