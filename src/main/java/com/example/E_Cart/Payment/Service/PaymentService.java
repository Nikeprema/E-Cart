package com.example.E_Cart.Payment.Service;

import com.example.E_Cart.Cart.Service.CartService;
import com.example.E_Cart.Order.Service.OrderService;
import com.example.E_Cart.Order.model.Order;
import com.example.E_Cart.Payment.Model.Payment;
import com.example.E_Cart.Payment.Repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private OrderService orderService;
    @Autowired
    private CartService cartService;
    
    
    public String finshPayment(Long userId,Integer orderId,boolean fail)throws  Exception {
        Order order = orderService.findOrder(userId, orderId);

        Payment payments = Payment.builder()
                .paymentmethod("Passport")
                .transactionId(String.valueOf(Math.random()))
                .totalAmountWithGST(order.getTotalAmountWithGST()).user(order.getUser()).order(order).build();

        paymentRepository.save(payments);

        orderService.completeOrder(order);

        if(fail)
            throw new Exception("payment fail");

        cartService.deleteCart(userId);

        return "Payment Don";
    }
}
