package com.jsp.Agro.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import com.jsp.Agro.Repo.UserRepo;
import com.jsp.Agro.entity.User;

@Repository
public class UserDao{
	@Autowired
	private UserRepo repo;
	
	//save
	public User saveUser(User user) {
		return repo.save(user);
	}
	
	//fetch
	public User fetchUser(int id) {
	Optional<User>db=repo.findById(id);
	if(db.isPresent()){
		return db.get();
	}else
		return null;
		
	}
	// update
		public User updateUser(User user) {
			// TODO Auto-generated method stub
			Optional<User> db = repo.findById(user.getId());
			User m = db.get();
			if (db.isPresent())
				if (user.getFirstName() == null) {
					user.setFirstName(m.getFirstName());
				}
			if (user.getEmail() == null) {
				user.setEmail(m.getEmail());
			}
			if (user.getLastName() == null) {
				user.setLastName(m.getLastName());
			}
			if (user.getPwd() == null) {
				user.setPwd(m.getPwd());
			}
			if (user.getPhone() != 0) {
				user.setPhone(m.getPhone());
			}
			return repo.save(user);
		}
// delete
		public User DeleteById(int id) {
			Optional<User> db = repo.findById(id);
			if (db.isEmpty())
				return null;
			else {
				repo.deleteById(id);
				return db.get();

			}
		}
//fetchAll
		public List <User> FetchAll() {
			return repo.findAll();
			
		}
		public User fetchByEmail(String email) {
			User db = repo.fetchbyEmail(email);
			if(db!=null) {
				return db;
			}else {
				return null;
			}
		}

	}
	



