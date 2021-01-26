package com.hackerrank.eshopping.product.dashboard.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.hackerrank.eshopping.product.dashboard.model.Product;
import com.hackerrank.eshopping.product.dashboard.service.ProductService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/products")
public class ProductsController {

    @Autowired
    private ProductService productService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addProduct(@RequestBody Product prod) {
        return productService.addProduct(prod);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateProduct(@PathVariable("id") Long id,
                                        @RequestBody JSONObject prod) {
        return productService.updateProduct(id, prod);
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getProdById(@PathVariable("id") Long id) {
        return productService.getProdById(id);
    }

    @GetMapping(params = "category")
    public ResponseEntity getProductsByCategory(@RequestParam String category) {
        return productService.getProductsByCategory(category);
    }

    @GetMapping(params = {"category", "availability"})
    public ResponseEntity getProdsByCatAndAvailability(@RequestParam(value = "category") String category,
                                                       @RequestParam(value = "availability") int availability) {
        return productService.getProdsByCatAndAvailability(category, availability);
    }

    @GetMapping
    public Iterable<Product> getAllProducts() { return productService.getAllProducts();}

}
