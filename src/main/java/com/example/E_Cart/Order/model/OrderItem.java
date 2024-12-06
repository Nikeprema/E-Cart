package com.example.E_Cart.Order.model;




import com.example.E_Cart.Product.Model.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder

public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer quantity;
    private double gstAmount;
    private double productPrice;
    private double itemTotal;
   @JsonIgnore
   @ManyToOne
   @JoinColumn(name = "product_id")
    private Product product;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "order_id",updatable = false,insertable = false)
    private Order order;



}
