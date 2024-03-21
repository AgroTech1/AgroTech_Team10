package com.jsp.Agro.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.Agro.Repo.EquipmentRepo;
import com.jsp.Agro.Repo.UserRepo;
import com.jsp.Agro.entity.Equipment;

@Repository
public class EquipmentDao {
	@Autowired
   private EquipmentRepo equipmentrepo;
	@Autowired
    private UserRepo urepo;
	public Equipment saveEquipment(Equipment equipment) {
		return equipmentrepo.save(equipment);
		
	}
   
	

}
