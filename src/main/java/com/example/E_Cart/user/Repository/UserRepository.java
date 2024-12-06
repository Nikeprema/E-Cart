package com.example.E_Cart.user.Repository;




import com.example.E_Cart.user.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User>findByEmail(String email);
}
