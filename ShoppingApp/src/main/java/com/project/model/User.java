package com.project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class User {

    // Id should be Contact Number
    @Id
    @NotNull(message = "{User.id.invalid}")
    @NotBlank(message = "{User.id.invalid}")
    @NotEmpty(message = "{User.id.invalid}")
    @Size(min = 10, max = 12, message = "{User.id.invalid}")
    private String id;

    @NotNull(message = "{User.password.invalid}")
    @NotBlank(message = "{User.password.invalid}")
    @NotEmpty(message = "{User.password.invalid}")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[^\\w\\d\\s:])([^\\s]){6,12}$", message = "{Customer.password.invalid}")
    private String password;

    // Choose From Admin or Customer
    @NotNull(message = "{User.role.invalid}")
    @NotBlank(message = "{User.role.invalid}")
    @NotEmpty(message = "{User.role.invalid}")
    private String role;
}
