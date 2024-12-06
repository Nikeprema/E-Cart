package com.example.E_Cart.Product.Controller;


import com.example.E_Cart.Product.Model.Product;
import com.example.E_Cart.Product.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping
    public List<Product> getAllProduct(){
       return productService.getAllCategory();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
        Optional<Product> product=productService.getProductById(id);
        return product.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }
    @PostMapping
    public Product createProduct(@RequestBody Product product)  {
        return productService.createProduct(product); }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product productDetails)
    { return productService.updateProduct(id, productDetails); }


    @DeleteMapping("/{id}")
    public ResponseEntity<String>deleteProduct(@PathVariable Long id){
        productService.deleteProduct((id));
        return ResponseEntity.ok("deleted");
    }

}
