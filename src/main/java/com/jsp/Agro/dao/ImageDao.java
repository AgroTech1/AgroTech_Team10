package com.jsp.Agro.dao;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.Agro.Repo.ImageRepo;
import com.jsp.Agro.Repo.UserRepo;
import com.jsp.Agro.entity.Image;
import com.jsp.Agro.entity.User;
import com.jsp.Agro.exception.ImageNotFound;

@Repository
public class ImageDao {
   @Autowired
	private ImageRepo irepo;
	@Autowired
	private Image image;
	@Autowired
	private UserDao userdao;
	@Autowired
	private UserRepo userrepo;
	
	
	public Image uploadProfile(String description, MultipartFile file, String name) throws IOException{
		image.setDescription(description);
		image.setName(name);
		image.setData(file.getBytes());
		return irepo.save(image);

	}

	public Image saveImage(String description, MultipartFile file, String name)throws IOException {
			image.setDescription(description);
			image.setData(file.getBytes());
			image.setName(name);
		return irepo.save(image);
	}


	public Image fetchById(int id) {
		Optional<Image> db = irepo.findById(id);
		if (db.isPresent())
			return db.get();
		else
			throw new ImageNotFound("Image not found for id : " + id);
	}

	// @Transactional
	public Image deleteById(int id) {
		Optional<Image> db = irepo.findById(id);
		if (db.isPresent()) {
			Image imageDb = db.get();
			 User userdb = userrepo.fetchByImage(imageDb);
			if (userdb != null) {
				userdb.setImage(null);
				userdao.updateUser(userdb);
			}
			irepo.deleteById(id);
			return imageDb;
		} else {
			return null;
		}
	}

	public Image updateImage(int id, String description, String name, MultipartFile file)throws IOException {
		Optional<Image> db = irepo.findById(id);
		if (db.isPresent()) {
			Image imagedb = db.get();
			if (description != null) {
				imagedb.setDescription(description);
			}
			if (file != null) {
				imagedb.setData(imagedb.getData());
			}
			return irepo.save(imagedb);

		}
		return null;
	}
    public byte[]fetchByImage(int id){
	Optional<Image> db = irepo.findById(id);
	if(db.isPresent()) {
		return db.get().getData();
	}
	return null;
	
}

	





}

