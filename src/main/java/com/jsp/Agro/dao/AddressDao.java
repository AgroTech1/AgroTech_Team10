package com.jsp.Agro.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.Agro.Repo.AddressRepo;
import com.jsp.Agro.entity.Address;
@Repository
public class AddressDao {
	@Autowired
	private AddressRepo repo;
	
	public Address saveAddress(Address address) {
		return repo.save(address);
		
	}

}
