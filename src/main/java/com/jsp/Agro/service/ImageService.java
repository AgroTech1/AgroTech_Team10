package com.jsp.Agro.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.Agro.dao.ImageDao;
import com.jsp.Agro.dao.UserDao;
import com.jsp.Agro.entity.Image;
import com.jsp.Agro.entity.User;
import com.jsp.Agro.exception.ImageNotFound;
import com.jsp.Agro.exception.UserNotFoundException;
import com.jsp.Agro.util.ResponseStructure;

@Service
public class ImageService {
	      @Autowired
			private ImageDao dao;
			@Autowired
			private UserDao dao1;
			
			
			public ResponseEntity<ResponseStructure<Image>> uploadProfile(int id,String description ,String name, MultipartFile file) throws IOException{
				User userDb = dao1.fetchUser(id);
				if(userDb!=null) {
		            Image db = dao.uploadProfile(description,file,name);
					userDb.setImage(db);
					dao1.updateUser(userDb);
					ResponseStructure<Image> r=new ResponseStructure<Image>();
					r.setMessage(" upload img successfully......");
					r.setData(db);
					r.setStatus(HttpStatus.ACCEPTED.value());
					return new ResponseEntity<ResponseStructure<Image>>(r,HttpStatus.ACCEPTED);
				}else {
					throw new UserNotFoundException("user not found for id: "+id);
				}
				
			}

			
			
			
			public ResponseEntity<ResponseStructure<Image>>fetchById(int id){
				Image imageDb = dao.fetchById(id);
				if(imageDb!=null) {
					ResponseStructure<Image> r=new ResponseStructure<Image>();
					r.setMessage("fetch image Successfully.....");
					r.setData(imageDb);
					r.setStatus(HttpStatus.FOUND.value());
					return new ResponseEntity<ResponseStructure<Image>>(r,HttpStatus.FOUND);
				}else {
					throw new ImageNotFound("Image not found for id : "+id);
				}
			}
			
			public ResponseEntity<ResponseStructure<Image>> deleteById(int id){
				Image imageDb = dao.deleteById(id);
				if(imageDb!=null) {
					ResponseStructure<Image> r=new ResponseStructure<Image>();
					r.setMessage("delete image Successfully.....");
					r.setData(imageDb);
					r.setStatus(HttpStatus.FOUND.value());
					return new ResponseEntity<ResponseStructure<Image>>(r,HttpStatus.FOUND);
				}else {
					throw new ImageNotFound("Image not found for id : "+id);
				}
			}
			
			
			public ResponseEntity<ResponseStructure<Image>> updateImage(int id,String description,String name, MultipartFile file) throws IOException{
				Image imageDb=dao.updateImage(id, description, name,file);
				if(imageDb!=null) {
					 ResponseStructure<Image> r=new ResponseStructure<Image>();
					 r.setData(imageDb);
					 r.setMessage("update image successfully......!");
					 r.setStatus(HttpStatus.ACCEPTED.value());
					 return new ResponseEntity<ResponseStructure<Image>>(r,HttpStatus.ACCEPTED);
				}else
					throw new ImageNotFound("Image not found for id : "+id);
				
			}


//			public ResponseEntity<byte[]> getImage( int id){
//				   byte[] imageBytes=dao.fetchById(id).getImage();
//				 org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
//				 headers.setContentType(org.springframework.http.MediaType.IMAGE_JPEG);
		//
//				return  new ResponseEntity<byte[]>(imageBytes,headers,HttpStatus.OK);
//			   }
			
			public ResponseEntity<ResponseStructure<Image>> saveImage(String description, MultipartFile file) throws IOException {
				Image imageDb=dao.saveImage(description, file, description);
				ResponseStructure<Image> r=new ResponseStructure<Image>();
				 r.setData(imageDb);
				 r.setMessage("saved image successfully......!");
				 r.setStatus(HttpStatus.ACCEPTED.value());
				 return new ResponseEntity<ResponseStructure<Image>>(r,HttpStatus.ACCEPTED);
			}


			
		


		

	}
