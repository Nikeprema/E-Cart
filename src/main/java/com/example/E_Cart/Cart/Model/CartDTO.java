package com.example.E_Cart.Cart.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {

    Cart cart;

    CartItem cartItem;
}
