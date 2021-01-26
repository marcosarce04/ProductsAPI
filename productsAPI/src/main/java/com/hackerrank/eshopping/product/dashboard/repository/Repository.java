package com.hackerrank.eshopping.product.dashboard.repository;

import com.hackerrank.eshopping.product.dashboard.model.Product;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.ArrayList;

@RepositoryRestResource
@Qualifier("RestRepositoryData")
public interface Repository extends CrudRepository<Product,Long> {

    ArrayList<Product> findProductsByCategory(String category);

    ArrayList<Product> findProductsByCategoryAndAvailability(String category, Boolean availability);
}
