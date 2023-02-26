/**
 *
 */
package com.project.model;

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

    @NotNull(message = "{Customer.name.invalid}")
    @NotBlank(message = "{Customer.name.invalid}")
    @NotEmpty(message = "{Customer.name.invalid}")
    @Size(min = 3, max = 15, message = "{Customer.name.invalid}")
    private String firstName;

    @NotNull(message = "{Customer.name.invalid}")
    @NotBlank(message = "{Customer.name.invalid}")
    @NotEmpty(message = "{Customer.name.invalid}")
    @Size(min = 3, max = 15, message = "{Customer.name.invalid}")
    private String lastName;

    @NotNull(message = "{Customer.contact.invalid}")
    @NotBlank(message = "{Customer.contact.invalid}")
    @NotEmpty(message = "{Customer.contact.invalid}")
    @Size(min = 10, max = 12, message = "{Customer.contact.invalid}")
    private String mobileNumber;

    @NotNull(message = "{Customer.password.invalid}")
    @NotBlank(message = "{Customer.password.invalid}")
    @NotEmpty(message = "{Customer.password.invalid}")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[^\\w\\d\\s:])([^\\s]){6,12}$", message = "{Customer.password.invalid}")
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
	@JoinTable(name = "c_address", joinColumns = @JoinColumn(name="customer_id", referencedColumnName = "customerId"))
	private Address address;

    @NotNull(message = "{Customer.email.invalid}")
    @NotBlank(message = "{Customer.email.invalid}")
    @NotEmpty(message = "{Customer.email.invalid}")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", flags = Pattern.Flag.CASE_INSENSITIVE, message = "{Customer.email.invalid}")
    private String email;

  
}