package com.example.E_Cart.user.controller;


import com.example.E_Cart.user.Model.LoginDTO;
import com.example.E_Cart.user.Model.Register;
import com.example.E_Cart.user.Model.User;
import com.example.E_Cart.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;





@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
    public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/test")
    public String testApi(){
        return "Work";
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Register register){
        System.out.println(register);
        return ResponseEntity.ok().body(userService.register(register));
    }

    @PostMapping("/login")
    public ResponseEntity<?>login(@RequestBody LoginDTO loginDTO){
        return userService.auhtenticate(loginDTO);
    }




    }


