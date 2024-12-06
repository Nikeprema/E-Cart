package com.example.E_Cart.Product.Service;

import com.example.E_Cart.Product.Model.Product;
import com.example.E_Cart.Product.Repository.CategoryRepository;
import com.example.E_Cart.Product.Repository.ProductRepository;
import com.example.E_Cart.user.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




import java.util.List;
import java.util.Optional;


@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    private UserRepository userRepository;

    public Product createProduct(Product product){
        return productRepository.save(product);
    }


    public List<Product> getAllCategory(){
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id){
        return productRepository.findById(id);
    }

    public Product updateProduct(Long productId, Product productDetails) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
         return productRepository.save(product); }


    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }
}
