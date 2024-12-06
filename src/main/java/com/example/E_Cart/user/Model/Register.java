package com.example.E_Cart.user.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Register {

    private String name;
    private String email;
    private String password;
    private String number;
}
