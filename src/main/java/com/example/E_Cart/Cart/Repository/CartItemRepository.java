package com.example.E_Cart.Cart.Repository;
import com.example.E_Cart.Cart.Model.CartItem;
import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Integer> {


    Optional<CartItem> findByCartIdAndProductId(Integer id, Integer productid);

    List<CartItem> findByCartId(Integer integer);

    @Modifying
    @Transactional
    @Query(value = "delete from cart_items where id = :cartItemId", nativeQuery = true)
    void deleteOrderItemById(@Param("cartItemId") Integer cartItemId);
}
