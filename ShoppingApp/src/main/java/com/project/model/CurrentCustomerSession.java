package com.project.model; /**
 *
 */


import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author sheetalBisht
 *
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrentCustomerSession {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer currentSessionId;


    @NotNull(message = "{CurrentCustomer.id.invalid}")
    private Integer customerId;

    @NotNull(message = "{CurrentCustomer.key.invalid}")
    @NotBlank(message = "{CurrentCustomer.key.invalid}")
    @NotEmpty(message = "{CurrentCustomer.key.invalid}")
    @Size(min = 6, max = 6, message = "{CurrentCustomer.key.invalid}")
    private String key;

    @NotNull(message = "{CurrentCustomer.date.invalid}")
    private LocalDateTime localDateTime;

}