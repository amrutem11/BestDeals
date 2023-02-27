/**
 *
 */
package com.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;


@Entity

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;

    
    @NotEmpty(message = "{Customer.name.invalid}")
    @Size(min = 3, max = 15, message = "{Customer.name.invalid}")
    private String firstName;

    
    @NotEmpty(message = "{Customer.name.invalid}")
    @Size(min = 3, max = 15, message = "{Customer.name.invalid}")
    private String lastName;

    
    @NotEmpty(message = "{Customer.password.invalid}")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[^\\w\\d\\s:])([^\\s]){6,12}$", message = "{Customer.password.invalid}")
    private String password;
    
    
    @NotEmpty(message = "{Customer.contact.invalid}")
    @Size(min = 10, max = 10, message = "{Customer.contact.invalid}")
    private String mobileNumber;

    

//    @OneToOne(cascade = CascadeType.ALL)
//    @JsonIgnore
//	@JoinTable(name = "c_address", joinColumns = @JoinColumn(name="customer_id", referencedColumnName = "customerId"))
//	private Address address;

    
    @NotEmpty(message = "{Customer.email.invalid}")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", flags = Pattern.Flag.CASE_INSENSITIVE, message = "{Customer.email.invalid}")
    private String email;

  
}