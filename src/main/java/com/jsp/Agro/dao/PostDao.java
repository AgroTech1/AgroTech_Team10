package com.jsp.Agro.dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.Agro.Repo.PostRepo;
import com.jsp.Agro.entity.Image;
import com.jsp.Agro.entity.Post;

@Repository
public class PostDao {
	@Autowired
	private PostRepo repo;
	@Autowired
	private Post post;

	public Post savePost(Post post) {

		return repo.save(post);
	}
	public Post savePost(Image image,String location,String Caption) {
		post.setCaption(Caption);
		post.setDate(LocalDate.now());
		post.setTime(LocalTime.now());
		post.setLocation(location);
		post.setImage(image);
		return repo.save(post);
		
	}

	public Post fetchPostById(int id) {
		Optional<Post> postObject = repo.findById(id);

		if(postObject.isPresent()) {
			return postObject.get();
		}else {
			return null;
		}
	}

	public void deletePostById(int id) {
		Optional<Post> postDb = repo.findById(id);
		if(postDb.isPresent()) {
			
			repo.deleteById(id);
		}
	}
	
	public Post updatePost(Post p) {
		Optional<Post> db = repo.findById(p.getId());
		Post post=db.get();
		if(db.isPresent()) {
			return repo.save(post);
		}
		return null;
		
	}
	public List<Post>fetchPostAll(){
		return repo.findAll();
	}



	
}


