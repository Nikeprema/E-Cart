package com.example.E_Cart.Product.Model;

import jakarta.persistence.*;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;






}
