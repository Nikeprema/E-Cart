package com.example.E_Cart.Cart.Repository;


import com.example.E_Cart.Cart.Model.Cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CartRepository extends JpaRepository<Cart,Integer> {


    Optional<Cart> findByUserId(Long Id);


}
