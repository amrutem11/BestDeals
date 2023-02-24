package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.model.Customer;

public interface LoginLogoutRepo extends   JpaRepository<Customer, Integer> {

}
