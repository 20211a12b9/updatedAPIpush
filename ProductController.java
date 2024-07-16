package com.vms.medxbid.controllers;

import java.util.List;

import com.vms.medxbid.models.SubCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vms.medxbid.models.Product;
import com.vms.medxbid.models.ProductCategory;
import com.vms.medxbid.services.ProductService;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
public class ProductController {
   
    @Autowired
    private ProductService productService;



    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }


    @PostMapping("/productSearch")
    public List<Product> searchAllProducts(@RequestBody String keyword) {

        System.out.println("Keyword: " + keyword);
        return productService.searchAllProducts(keyword);
    }

    @GetMapping("/productcategories")
    public List<ProductCategory> getProductCategories() {
       
    
        return productService.getAllProductCategories();
    }
    @GetMapping("/subcategories")
    public List<SubCategory> getSubCategories() {


        return productService.getAllSubCategories();
    }

    @GetMapping("/nproductsforcategory/{categoryId}")
    public List<Product> getNProductsForCategory(@PathVariable Long categoryId) {
        return productService.getNProductsForCategory(categoryId);
    }

    @GetMapping("/nproductsDesc/{productId}")
    public String getProductsDescription(@PathVariable Long productId) {
        return productService.getProductDesc(productId);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    @GetMapping("/products/{categoryId}")
    public List<Product> getAllProductsForCategory(@PathVariable Long categoryId) {
        return productService.getAllProductsForCategory(categoryId);
    }
    
}
