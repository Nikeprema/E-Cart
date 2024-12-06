package com.example.E_Cart.user.service;




import com.example.E_Cart.Config.JwtUtils;
import com.example.E_Cart.user.Model.ERole;
import com.example.E_Cart.user.Model.LoginDTO;
import com.example.E_Cart.user.Model.Register;
import com.example.E_Cart.user.Model.User;
import com.example.E_Cart.user.Repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;





import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private UserRepository userRepository;


    private  final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;

    public String register(Register register){
        var  user=User.builder()
                        .userName(register.getName())
                        .email(register.getEmail())
                        .password(passwordEncoder.encode(register.getPassword()))
                        .role(ERole.USER).build();
             userRepository.save(user);
        return "User Added";
    }


    public ResponseEntity<?>auhtenticate(LoginDTO login){
        try {
            Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getEmail(),login.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

        }catch (BadCredentialsException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }

        Optional<User> userDetails=userRepository.findByEmail(login.getEmail());
        if (userDetails.isPresent()){
            String token=jwtUtils.generateToken(userDetails.get());
            return ResponseEntity.ok(token);
        }else {
            return ResponseEntity.noContent().build();
        }
    }


}


