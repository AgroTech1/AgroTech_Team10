package com.jsp.Agro.service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.Agro.dao.ImageDao;
import com.jsp.Agro.dao.PostDao;
import com.jsp.Agro.dao.UserDao;
import com.jsp.Agro.entity.Image;
import com.jsp.Agro.entity.Post;
import com.jsp.Agro.entity.User;
import com.jsp.Agro.exception.PostNotFound;
import com.jsp.Agro.exception.UserNotFoundException;
import com.jsp.Agro.util.ResponseStructure;

@Service
public class PostService {

	@Autowired
  private ImageDao  dao;
	@Autowired
	private UserDao crud;
	@Autowired
	private PostDao crud1;
	
	public ResponseEntity<ResponseStructure<Post>>savePost( int id,MultipartFile  file,String location,String name,String Caption)throws IOException{
		User userdb =crud.fetchUser(id);
		if(userdb!=null) {
			Image i = new Image();
			i.setName(file.getOriginalFilename());
			i.setData(file.getBytes());
			i.setName(name);
			
//			dao.saveImage(Caption, file, Caption);
//			i.setImage(file.getBytes());
//			i.setName(file.getOriginalFilename());
			Post post1 = new Post();
			post1.setImage(i);
			post1.setDate(LocalDate.now());
			post1.setTime(LocalTime.now());
			post1.setCaption(Caption);

			
			
			Post postDb = crud1.savePost(i, location, Caption);
			List<Post> postList=new ArrayList<Post>();
			postList.add(postDb);
			postList.addAll(userdb.getPost());
			userdb.setPost(postList);
			
			User updatedUserDbObject = crud.updateUser(userdb);
			
			ResponseStructure<Post> postResponseStructor=new ResponseStructure<Post>();
			
			postResponseStructor.setData(postDb);
			postResponseStructor.setMessage("User Given post is saved Successfully");
			postResponseStructor.setStatus(HttpStatus.OK.value());
			
			return new ResponseEntity<>(postResponseStructor,HttpStatus.OK);
		}else {
			throw new UserNotFoundException("User is not found with the given Id"+id);
		}
	}

	public ResponseEntity<ResponseStructure<Post>> fetchPostById(int id) {
Post postDbObject = crud1.fetchPostById(id);
		
		if(postDbObject!=null) {
			ResponseStructure<Post> postResponseStructor=new ResponseStructure<Post>();
			
			postResponseStructor.setData(postDbObject);
			postResponseStructor.setMessage("This is the Post You have in the database with the given id:  "+id);
			postResponseStructor.setStatus(HttpStatus.OK.value());
			
			return new ResponseEntity<>(postResponseStructor,HttpStatus.OK);
		}else {
			//throw new PostNotFound(); 
			
					}
		return null;
	}

	public ResponseEntity<ResponseStructure<Post>> deletePostById(int id) {
Post postDbObject = crud1.fetchPostById(id);
		
		if(postDbObject!=null) {
			
			List<User> users = crud.FetchAll();
			for(User userObj:users) {
				List<Post> userPosts = userObj.getPost();
				if(userPosts.contains(postDbObject)) {
					userPosts.remove(postDbObject);
					crud.updateUser(userObj);
					
					crud1.deletePostById(id);
				}
			}
			ResponseStructure<Post> postResponseStructor=new ResponseStructure<Post>();
			
			postResponseStructor.setData(postDbObject);
			postResponseStructor.setMessage("Post is Deleted Success fully");
			postResponseStructor.setStatus(HttpStatus.OK.value());
			
			return new ResponseEntity<>(postResponseStructor,HttpStatus.FOUND);
		}else {
			throw new PostNotFound ();
		}
	}

	
	
	
}


