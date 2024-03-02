package com.jsp.Agro.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.Agro.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {
	 @Query("select a from User a where a.email=?1")
	User fetchbyEmail(String email);

}
