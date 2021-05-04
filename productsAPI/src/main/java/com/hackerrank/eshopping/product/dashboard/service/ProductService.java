package com.hackerrank.eshopping.product.dashboard.service;

import com.hackerrank.eshopping.product.dashboard.model.ComplexProduct;
import com.hackerrank.eshopping.product.dashboard.model.Product;
import com.hackerrank.eshopping.product.dashboard.repository.Repository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

  @Autowired
  @Qualifier("RestRepositoryData")
  private Repository productsRepo;

  public ResponseEntity addProduct(Product prod) {
    if (productsRepo.findById(prod.getId()).isPresent()) {
      return new ResponseEntity(HttpStatus.BAD_REQUEST);
    } else {
      productsRepo.save(prod);
      return new ResponseEntity(HttpStatus.CREATED);
    }
  }

  public ResponseEntity updateProduct(Long id, JSONObject prod) {
    Optional<Product> productOptional = productsRepo.findById(id);
    if (!productOptional.isPresent()) {
      return new ResponseEntity(HttpStatus.BAD_REQUEST);
    } else {
      Product product = productOptional.get();
      product.setRetail_price((Double) prod.get("retail_price"));
      product.setDiscounted_price((Double) prod.get("discounted_price"));
      product.setAvailability((Boolean) prod.get("availability"));
      productsRepo.save(product);
      return new ResponseEntity(HttpStatus.OK);
    }
  }

  public ResponseEntity getProdById(Long id) {
    Optional<Product> productOptional = productsRepo.findById(id);
    return productOptional.map(product -> new ResponseEntity(product.toString(), HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity(HttpStatus.NOT_FOUND));
  }

  public ResponseEntity getProductsByCategory(String category) {
    ArrayList<Product> productsByCat = productsRepo.findProductsByCategory(category);
    Comparator<Product> comparator = Comparator.comparing(Product::getAvailability)
        .thenComparing(Product::getDiscounted_price, Comparator.reverseOrder())
        .thenComparing(Product::getId);
    List<Product> sortedProds = productsByCat.stream().sorted(comparator.reversed())
        .collect(Collectors.toList());
    return new ResponseEntity(sortedProds.toString(), HttpStatus.OK);
  }

  public ResponseEntity getProdsByCatAndAvailability(String category, int availability) {
    category = category.replace("%20", " ");
    ArrayList<ComplexProduct> complexProducts = new ArrayList<>();
    productsRepo.findProductsByCategoryAndAvailability(category, availability != 0)
        .forEach((Product product) -> complexProducts.add(buildComplexProduct(product)));
    Collections.sort(complexProducts);
    ArrayList<Product> ordered = new ArrayList<>();
    complexProducts.forEach((ComplexProduct c) -> ordered.add(c.getProduct()));
    return new ResponseEntity(ordered, HttpStatus.OK);
  }

  public Iterable<Product> getAllProducts() {
    return productsRepo.findAll();
  }

  private ComplexProduct buildComplexProduct(Product product) {
    return ComplexProduct.builder()
        .product(product)
        .discount(calculateDiscount(product))
        .build();
  }

  private Double calculateDiscount(Product product) {
    return (Math.round(
        ((product.getRetail_price() - product.getDiscounted_price()) / product.getRetail_price())
            * 100)
        * 100.0 / 100.0);
  }
}
