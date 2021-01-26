package com.hackerrank.eshopping.product.dashboard.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.base.Functions;
import com.google.common.collect.Ordering;
import com.hackerrank.eshopping.product.dashboard.model.ComplexProduct;
import com.hackerrank.eshopping.product.dashboard.repository.Repository;
import com.hackerrank.eshopping.product.dashboard.model.Product;
import org.hibernate.sql.ordering.antlr.OrderingSpecification;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
                                                   .thenComparing(Product::getDiscounted_price,Comparator.reverseOrder())
                                                   .thenComparing(Product::getId);

        List<Product> sortedProds = productsByCat.stream().sorted(comparator.reversed()).collect(Collectors.toList());

        return new ResponseEntity(sortedProds.toString(), HttpStatus.OK);
    }

    public ResponseEntity getProdsByCatAndAvailability(String category, int availability) {

        category = category.replace("%20", " ");
        ArrayList<ComplexProduct> prods = new ArrayList<>();

        productsRepo.findProductsByCategoryAndAvailability(category,availability != 0).forEach((Product p) ->
                prods.add(new ComplexProduct(p,
                        Math.round(((p.getRetail_price()-p.getDiscounted_price())/p.getRetail_price())*100)*100.0/100.0)));
        Collections.sort(prods);

        ArrayList<Product> ordered = new ArrayList<>();
        prods.forEach((ComplexProduct c) -> ordered.add(c.getProduct()));

        return new ResponseEntity(ordered,HttpStatus.OK);
    }

    public Iterable<Product> getAllProducts() { return productsRepo.findAll();}

}
