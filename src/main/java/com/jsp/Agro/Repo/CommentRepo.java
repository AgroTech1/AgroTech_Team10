package com.jsp.Agro.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.Agro.entity.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
