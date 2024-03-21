package com.jsp.Agro.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.Agro.entity.Image;

public interface ImageRepo extends JpaRepository<Image, Integer> {
	Optional<Image> findByName(String name);
}
