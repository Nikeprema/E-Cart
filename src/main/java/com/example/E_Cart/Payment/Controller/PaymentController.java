package com.example.E_Cart.Payment.Controller;

import com.example.E_Cart.Config.UserContext;
import com.example.E_Cart.Payment.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public  String createOrder(@RequestParam Integer orderId,boolean fail)throws  Exception{

        Long userId= UserContext.getUserId();
        return paymentService.finshPayment(userId,orderId,fail);
    }
}
