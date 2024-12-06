package com.example.E_Cart.Cart.Service;

import com.example.E_Cart.Cart.Model.Cart;
import com.example.E_Cart.Cart.Model.CartDTO;
import com.example.E_Cart.Cart.Model.CartItem;
import com.example.E_Cart.Cart.Repository.CartItemRepository;
import com.example.E_Cart.Cart.Repository.CartRepository;
import com.example.E_Cart.Config.UserContext;
import com.example.E_Cart.Order.Service.OrderService;
import com.example.E_Cart.Order.model.Order;
import com.example.E_Cart.Product.Model.Product;
import com.example.E_Cart.Product.Repository.ProductRepository;
import com.example.E_Cart.user.Model.User;
import com.example.E_Cart.user.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;








import java.util.List;
import java.util.Optional;


@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CartItemRepository cartItemRepository;
    @Autowired
    OrderService orderService;

    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    @Transactional
    public Cart createCart(CartDTO cartDTO) throws Exception {

        Long id = UserContext.getUserId();
        User user = userRepository.findById(id).orElseThrow(() -> new Exception("User does not Exist"));

        Cart cart = cartRepository.findByUserId(id).orElse(new Cart());

        cart.setUser(user);
        Product product = productRepository.findById(Long.valueOf(cartDTO.getCartItem().getTempProductId())).orElseThrow(() -> new Exception("Product does not exists"));

        int quantity = cartDTO.getCartItem().getQuantity();
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);


        cartItem.setQuantity(quantity);
        cartItem.setProductPrice(product.getPrice());


        return  cartRepository.save(cart);
    }


   public Cart calculateTotal(CartItem cartItem,Cart cart,Product product){

        double itemtotal =cartItem.getQuantity()*product.getPrice();
        double gstAmount=itemtotal*(product.getGst()/100);
        cartItem.setItemtotal((int) itemtotal);
        cartItem.setGst(gstAmount);

        cartItemRepository.save(cartItem);

        List<CartItem> cartItemList =cartItemRepository.findByCartId(cart.getId());

        double finalTotal =cartItemList.stream().mapToDouble(item->item.getItemtotal()).sum();
        double gstTotal = cartItemList.stream().mapToDouble(item->item.getGst()).sum();
        double finalTotalWithGst =finalTotal+ gstTotal;

        cart.setTotalPrice(finalTotal);
        cart.setGst(gstTotal);
        cart.setTotalAmountWithGst(finalTotalWithGst);

        return  cart;
    }


    public  Cart getCartById(Integer id)throws  Exception{
        Optional<Cart> cart=cartRepository.findById(id);
        return cart.orElseThrow(()->new RuntimeException("cart not found"));
    }



public  Cart updateCart(Integer cartId, CartItem cartItem)throws  Exception{
        Cart cart=getCartById(cartId);

        CartItem existingItem =cartItemRepository.findByCartIdAndProductId(cart.getId(),cartItem.getTempProductId()).orElseThrow(()->new  Exception("cart item not found"));

        Product product=productRepository.findById(Long.valueOf(cartItem.getTempProductId())).orElseThrow(()->new Exception("product not found"));

        existingItem.setQuantity(cartItem.getQuantity());
        existingItem.setProductPrice( product.getPrice());

        calculateTotal(existingItem,cart,product);

        return  cartRepository.save(cart);


}

@Transactional
public void deleteCartItem(Integer cartItemId)throws  Exception{
        CartItem cartItem=cartItemRepository.findById(cartItemId).orElseThrow(()->new  Exception("cart item not found"));

        Cart cart=cartRepository.findById(cartItem.getCart().getId()).orElseThrow(()->new Exception("cart notfound"));

        cartItemRepository.deleteOrderItemById(cartItemId);

    System.out.println("cart item delete");

    List<CartItem>existitems=cartItemRepository.findByCartId(cart.getId());


    double finaltotal=existitems.stream().mapToDouble(item->item.getItemtotal()).sum();
    double gstTotal  =existitems.stream().mapToDouble(item->item.getGst()).sum();
    double finalToalWithGst=finaltotal+gstTotal;

    cart.setTotalPrice(finaltotal);
    cart.setGst(gstTotal);
    cart.setTotalAmountWithGst(finalToalWithGst);
    cartRepository.save(cart);



    }

    public  void createOrder(Long userId)throws Exception{
        Cart cart=cartRepository.findByUserId(userId).orElseThrow(()->new Exception("user does not exists"));

        Order order=orderService.createOrder(cart);

        cart.setId(order.getId());

        cartRepository.save(cart);

        System.out.println("order complete");






    }








   public void deleteCart(Long userid)throws  Exception{

        Cart cart =cartRepository.findByUserId(userid).orElseThrow(()->new Exception("cart not found"));
        cartRepository.delete(cart);
   }


}
