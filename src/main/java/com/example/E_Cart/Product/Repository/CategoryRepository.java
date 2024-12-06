package com.example.E_Cart.Product.Repository;



import com.example.E_Cart.Product.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
}
