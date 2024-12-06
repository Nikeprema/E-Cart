package com.example.E_Cart.Cart.Model;


import com.example.E_Cart.user.Model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Double totalPrice=0.0;
    private Double gst =0.0;
    private Double totalAmountWithGst=0.0;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;



}
