//package com.jsp.Agro;
//
//public class Useless {
//	@RestController
//	public class CommentController {
//		
//		@Autowired
//		private CommentService service;
//		
//		@PostMapping("/saveComment")
//		public ResponseEntity<ResponseStructure<Comment>> saveComment(@RequestParam int postid,@RequestParam int userid, @RequestParam String message) {
//			return service.saveComment(postid, userid, message);
//		}
//		
//		@DeleteMapping("/deleteComment")
//		public ResponseEntity<ResponseStructure<Comment>> deleteComment(@RequestParam int id){
//			return service.deleteComment(id);
//		}
//	}
//	package com.jsp.agro.dao;
//
//	import java.util.Optional;
//
//	import org.springframework.beans.factory.annotation.Autowired;
//	import org.springframework.stereotype.Repository;
//
//	import com.jsp.agro.entity.Comment;
//	import com.jsp.agro.repo.CommentRepo;
//
//	@Repository
//	public class CommentDao {
//
//		@Autowired
//		private  CommentRepo repo;
//		
//		public Comment saveComment(Comment comnt) {
//			 return repo.save(comnt);
//		}
//		
//		public Comment deleteComment(int id) {
//			Optional<Comment> db = repo.findById(id);
//			if(db.isPresent()) {
//				repo.deleteById(id);
//				return db.get();
//			}else {
//				return null;
//			}
//		}
//		
//		public Comment fetchById(int id) {
//			Optional<Comment> db = repo.findById(id);
//			return db.get();
//		}
//	}
//	package com.jsp.agro.entity;
//
//	import com.fasterxml.jackson.annotation.JsonIgnore;
//
//	import jakarta.persistence.CascadeType;
//	import jakarta.persistence.Entity;
//	import jakarta.persistence.GeneratedValue;
//	import jakarta.persistence.GenerationType;
//	import jakarta.persistence.Id;
//	import jakarta.persistence.ManyToOne;
//	import lombok.Data;
//
//	@Data
//	@Entity
//	public class Comment {
//		
//		@Id
//		@GeneratedValue(strategy = GenerationType.IDENTITY)
//		private int id;
//		private String comment;
//		
//		@ManyToOne(cascade = CascadeType.ALL)
//		@JsonIgnore
//		private User user;
//
//	}
//	package com.jsp.agro.service;
//
//	import java.util.ArrayList;
//	import java.util.List;
//
//	import org.springframework.beans.factory.annotation.Autowired;
//	import org.springframework.http.HttpStatus;
//	import org.springframework.http.ResponseEntity;
//	import org.springframework.stereotype.Service;
//
//	import com.jsp.agro.dao.CommentDao;
//	import com.jsp.agro.dao.PostDao;
//	import com.jsp.agro.dao.UserDao;
//	import com.jsp.agro.entity.Comment;
//	import com.jsp.agro.entity.Post;
//	import com.jsp.agro.entity.User;
//	import com.jsp.agro.exception.CommentNotFoundException;
//	import com.jsp.agro.exception.PostNotFoundException;
//	import com.jsp.agro.exception.UserNotFoundException;
//	import com.jsp.agro.util.ResponseStructure;
//
//	@Service
//	public class CommentService {
//
//		@Autowired
//		private CommentDao cdao;
//
//		@Autowired
//		private PostDao pdao;
//
//		@Autowired
//		private UserDao udao;
//
//		ResponseStructure<Comment> rs = new ResponseStructure<Comment>();
//
//		public ResponseEntity<ResponseStructure<Comment>> saveComment(int postid, int userid, String message) {
//			Post post = pdao.fetchById(postid);
//			if (post != null) {
//				User user = udao.fetchById(userid);
//				if (user != null) {
//					Comment c = new Comment();
//					c.setComment(message);
//					c.setUser(user);
//					cdao.saveComment(c);
//					List<Comment> clist = new ArrayList<Comment>();
//					clist.add(c);
//					clist.addAll(post.getComments());
//					post.setComments(clist);
//					pdao.updatePost(post);
//					rs.setMessage("Commented succesfully");
//					rs.setStatus(HttpStatus.OK.value());
//					rs.setData(c);
//					return new ResponseEntity<ResponseStructure<Comment>>(rs, HttpStatus.OK);
//				} else {
//					throw new UserNotFoundException();
//				}
//			} else
//				throw new PostNotFoundException();
//		}
//
//		public ResponseEntity<ResponseStructure<Comment>> deleteComment(int id){
//			Comment cdata = cdao.fetchById(id);
//			if(cdata!=null) {
//				cdao.deleteComment(id);
//				rs.setData(cdata);
//				rs.setMessage("Comment deleted");
//				rs.setStatus(HttpStatus.FOUND.value());
//				return new ResponseEntity<ResponseStructure<Comment>>(rs,HttpStatus.FOUND);
//			}
//			else
//				throw new CommentNotFoundException();
//		}
//
//
//
//	}
//
//}


@Service
public class EquipmentService {
	@Autowired
	private EquipmentDao equipmentDao;
	@Autowired
	private UserDAO userDao;
	
	ResponseStructure<Equipment> rs = new ResponseStructure<Equipment>();
	public ResponseEntity<ResponseStructure<Equipment>> uploadEquipment(int user_id,Equipment equipment) throws IOException{
		User db = userDao.findById(user_id);
		if(db!=null) {
			
			Equipment db1 =  new Equipment();
			db1.setCostperhour(equipment.getCostperhour());
			db1.setName(equipment.getName());
			db1.setQuantity(equipment.getQuantity());
			db1.setUser(db);
			rs.setMessage("equipment upload is successfully done...");
			rs.setData(equipmentDao.saveEquipment(db1));
			rs.setStatus(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Equipment>>(rs,HttpStatus.CREATED);
		}
		else {
			throw new UserAlreadyExistException("user doesn't exist");
		}
	}
