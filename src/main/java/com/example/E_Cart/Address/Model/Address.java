package com.example.E_Cart.Address.Model;


import com.example.E_Cart.user.Model.User;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.*;



@NoArgsConstructor
@AllArgsConstructor
@Data
@Transactional
@Entity

public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String street;
    private String landmark;
    private int postal;


    @OneToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;



}


