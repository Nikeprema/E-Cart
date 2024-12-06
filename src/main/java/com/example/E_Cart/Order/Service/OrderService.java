package com.example.E_Cart.Order.Service;



import com.example.E_Cart.Cart.Model.Cart;
import com.example.E_Cart.Order.Repository.OrderItemRepository;
import com.example.E_Cart.Order.Repository.OrderRepository;
import com.example.E_Cart.Order.model.EOrderStatus;
import com.example.E_Cart.Order.model.Order;
import com.example.E_Cart.Order.model.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderItemRepository orderItemRepository;

    public Order createOrder(Cart cart){
        Order order=Order.builder().status(String.valueOf(EOrderStatus.PENDING)).orderTotal(cart.getTotalPrice())
                .gstAmount(cart.getGst())
                .totalAmountWithGST(cart.getTotalAmountWithGst()).user(cart.getUser()).build();
         orderRepository.save(order);


         List<OrderItem> orderItems=new ArrayList<>();

         orderItemRepository.saveAll(orderItems);
         return order;

    }

    public  Order findOrder(Long userId,Integer id)throws Exception{
        Optional<Order> Orderisavailable=orderRepository.findByUserIdAndId(userId,id);

        if (Orderisavailable.isPresent()){
            return Orderisavailable.get();
        }else {
            throw new Exception("Order not found");
        }
    }

    public void completeOrder (Order order){
        order.setStatus(String.valueOf(EOrderStatus.COMPLETED));
        orderRepository.save(order);
    }
}
