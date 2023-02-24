/**
 *
 */
package com.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author sheetalbisht
 *
 */

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
//@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer customerId;

    @NotNull(message = "{Customer.name.invalid}")
    @NotBlank(message = "{Customer.name.invalid}")
    @NotEmpty(message = "{Customer.name.invalid}")
    private String firstName;

    @NotNull(message = "{Customer.name.invalid}")
    @NotBlank(message = "{Customer.name.invalid}")
    @NotEmpty(message = "{Customer.name.invalid}")
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
    private Address address;

    @NotNull(message = "{Customer.email.invalid}")
    @NotBlank(message = "{Customer.email.invalid}")
    @NotEmpty(message = "{Customer.email.invalid}")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", flags = Pattern.Flag.CASE_INSENSITIVE, message = "{Customer.email.invalid}")
    private String email;

    @OneToMany(cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Order> listOfOrders = new ArrayList<>();

    /**
     * @return the listOfOrders
     */
    @JsonIgnore
    public List<Order> getListOfOrders() {
        return listOfOrders;
    }

    /**
     * @param listOfOrders the listOfOrders to set
     */
    @JsonIgnore
    public void setListOfOrders(List<Order> listOfOrders) {
        this.listOfOrders = listOfOrders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Customer customer = (Customer) o;
        return customerId != null && Objects.equals(customerId, customer.customerId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}