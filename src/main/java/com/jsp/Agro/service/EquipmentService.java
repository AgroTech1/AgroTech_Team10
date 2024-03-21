package com.jsp.Agro.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.Agro.dao.EquipmentDao;
import com.jsp.Agro.dao.UserDao;
import com.jsp.Agro.entity.Equipment;
import com.jsp.Agro.entity.User;
import com.jsp.Agro.util.ResponseStructure;
@Service
public class EquipmentService {
	@Autowired
	private EquipmentDao equipmentdao;
	@Autowired
	private UserDao userdao;

	public ResponseEntity<ResponseStructure<Equipment>> uploadEquipment(int userid, Equipment equipment) throws IOException {
		User db =userdao.
		}
	}		

}
