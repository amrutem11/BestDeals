package com.project.repository;



        import java.util.Optional;

        import com.project.model.Customer;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.stereotype.Repository;



/**
 * @author sheetalbisht
 *
 */

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {

    public Optional<Customer> findByMobileNumber(String mobileNumber);

}