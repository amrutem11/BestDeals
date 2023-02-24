package com.project.repository;

import java.util.Optional;

import com.project.model.CurrentCustomerSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



/**
 * @author sheetalBisht
 *
 */
@Repository
public interface CurrentCustomerSessionRepo extends JpaRepository<CurrentCustomerSession, Integer> {

    public Optional<CurrentCustomerSession> findByKey(String key);

    public Optional<CurrentCustomerSession> findByCustomerId(Integer customerId);
}