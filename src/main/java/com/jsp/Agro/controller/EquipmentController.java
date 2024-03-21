package com.jsp.Agro.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.Agro.entity.Equipment;
import com.jsp.Agro.service.EquipmentService;
import com.jsp.Agro.util.ResponseStructure;

@RestController
public class EquipmentController {
@Autowired
private EquipmentService equipmentservice;

public ResponseStructure<Equipment> rs=new ResponseStructure<Equipment>();
@PostMapping("/uploadequipment")
public ResponseEntity<ResponseStructure<Equipment>> SaveEquipment(@RequestParam int userid,@RequestBody Equipment equipment)throws IOException{
return equipmentservice.uploadEquipment(userid,equipment);
}
}