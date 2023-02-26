package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.model.Session;

import java.util.Optional;


public interface Sessionrepo  extends JpaRepository<Session, Integer>{
	
	public Session findByuuid(String uuid);

}
