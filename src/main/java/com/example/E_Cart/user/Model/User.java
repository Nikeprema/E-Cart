package com.example.E_Cart.user.Model;


import com.example.E_Cart.Address.Model.Address;

import com.example.E_Cart.Order.model.Order;
import com.example.E_Cart.Payment.Model.Payment;
import com.example.E_Cart.Product.Model.Product;
import jakarta.persistence.*;
import lombok.*;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@AllArgsConstructor

@Data
@Entity
@Builder
public class User implements UserDetails {

    public User(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String userName;
    private String email;
    private String password;



    @Enumerated(EnumType.STRING)
    private ERole role;


    @OneToMany(mappedBy = "user")
    private List<Address> addresses;


    @OneToMany(mappedBy = "user")
    private List<Product>products;

    @OneToMany(mappedBy = "user")
    private List<Order>orders;


    @OneToMany
    private List<Payment> payments=new ArrayList<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername(){
        return email;
    }


}


