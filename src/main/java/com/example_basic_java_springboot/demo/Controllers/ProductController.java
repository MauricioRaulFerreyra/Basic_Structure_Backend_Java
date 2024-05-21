package com.example_basic_java_springboot.demo.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example_basic_java_springboot.demo.Entities.Product;
import com.example_basic_java_springboot.demo.Repositories.IProductRepository;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductRepository productRepository;

    @GetMapping
    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Product getByIdProduct(@PathVariable Long id){
        return productRepository.findById(id)
        .orElseThrow(()-> new RuntimeException("Product not found"));
    }

    @PostMapping
    public Product saveProduct(@RequestBody Product product){
        return productRepository.save(product);
    }

    @PutMapping
    public Product updateProduct(@PathVariable Long id, @RequestBody Product detailProduct){
        Product product = productRepository.findById(id)
        .orElseThrow(()-> new RuntimeException("Product not found"));
        product.setName(detailProduct.getName());
        product.setPrice(detailProduct.getPrice());

        return productRepository.save(product);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id){
        Product product = productRepository.findById(id)
        .orElseThrow(()-> new RuntimeException("Porduct not found"));

        productRepository.delete(product);

        return "Product deleted";
    }

}
