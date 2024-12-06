package com.example.E_Cart.Cart.Controller;

import com.example.E_Cart.Cart.Model.Cart;
import com.example.E_Cart.Cart.Model.CartDTO;
import com.example.E_Cart.Cart.Model.CartItem;
import com.example.E_Cart.Cart.Service.CartService;
import com.example.E_Cart.Config.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;
     @PostMapping
    public String createcart(@RequestBody CartDTO cartDTO)throws  Exception{


        try {
            cartService.createCart(cartDTO);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "Don";
    }

    @GetMapping("/{id}")
    public Cart getCart(@PathVariable Integer id)throws Exception {
        return cartService.getCartById(id);
    }
    @PutMapping("/{id}")
    public  Cart updateCart(@PathVariable Integer cartId, @RequestBody CartItem cartItem)throws Exception{
        return cartService.updateCart(cartId,cartItem);
    }

    @DeleteMapping("/{id}")
    public  void  deleteCartItem(@PathVariable Integer cartItemId)throws  Exception{
        cartService.deleteCartItem(cartItemId);
    }

    @PostMapping("/checkout")
    public String createOrder() throws Exception {
        Long userId = UserContext.getUserId();
        cartService.createOrder(userId);
        return "success";


    }









}
