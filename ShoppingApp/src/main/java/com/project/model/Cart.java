package com.project.model;

import java.util.ArrayList;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Data
@NoArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer cartId;

    @OneToOne(cascade = CascadeType.ALL)
    private Customer customer;

//    @ElementCollection
//    private List<ProductDTO> products = new ArrayList<>();

}