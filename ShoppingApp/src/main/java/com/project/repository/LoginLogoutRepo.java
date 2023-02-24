package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.model.User;

public interface LoginLogoutRepo extends   JpaRepository<User, Integer> {

}
