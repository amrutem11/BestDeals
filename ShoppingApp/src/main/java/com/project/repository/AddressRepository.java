package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.model.Address;

public interface AddressRepository extends JpaRepository<Address, Integer>{

}
