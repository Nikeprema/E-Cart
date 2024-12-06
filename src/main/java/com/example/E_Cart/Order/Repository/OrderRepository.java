package com.example.E_Cart.Order.Repository;




import com.example.E_Cart.Order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    Optional<Order> findByUserIdAndId(Long userId, Integer id);

    }
