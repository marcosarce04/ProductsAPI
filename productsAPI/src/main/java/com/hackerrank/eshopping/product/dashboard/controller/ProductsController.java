package com.hackerrank.eshopping.product.dashboard.controller;

import com.hackerrank.eshopping.product.dashboard.model.Product;
import com.hackerrank.eshopping.product.dashboard.service.ProductService;
import org.json.simple.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/products")
public class ProductsController {

  private final ProductService productService;

  public ProductsController(ProductService productService) {
    this.productService = productService;
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity addProduct(@RequestBody Product prod) {
    return productService.addProduct(prod);
  }

  @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity updateProduct(@PathVariable("id") Long id,
      @RequestBody JSONObject prod) {
    return productService.updateProduct(id, prod);
  }

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity getProdById(@PathVariable("id") Long id) {
    return productService.getProdById(id);
  }

  @GetMapping(params = "category")
  public ResponseEntity getProductsByCategory(@RequestParam String category) {
    return productService.getProductsByCategory(category);
  }

  @GetMapping(params = {"category", "availability"})
  public ResponseEntity getProdsByCatAndAvailability(
      @RequestParam(value = "category") String category,
      @RequestParam(value = "availability") int availability) {
    return productService.getProdsByCatAndAvailability(category, availability);
  }

  @GetMapping
  public Iterable<Product> getAllProducts() {
    return productService.getAllProducts();
  }

}
