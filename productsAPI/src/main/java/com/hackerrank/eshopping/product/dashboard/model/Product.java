package com.hackerrank.eshopping.product.dashboard.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Product {

    @Id
    private Long id;
    private String name;
    private String category;
    private Double retail_price;
    private Double discounted_price;
    private Boolean availability;

    public Product() {
    }

    public Product(Long id, String name, String category, Double retail_price, Double discounted_price, Boolean availability) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.retail_price = retail_price;
        this.discounted_price = discounted_price;
        this.availability = availability;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getRetail_price() {
        return retail_price;
    }

    public void setRetail_price(Double retailPrice) {
        this.retail_price = retailPrice;
    }

    public Double getDiscounted_price() {
        return discounted_price;
    }

    public void setDiscounted_price(Double discountedPrice) {
        this.discounted_price = discountedPrice;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        return "{\"id\": "+ id +"," +
                " \"name\": \""+ name +"\"," +
                " \"category\": \""+ category +"\"," +
                " \"retail_price\": "+ retail_price +"," +
                " \"discounted_price\": "+ discounted_price +"," +
                " \"availability\": "+ availability +"}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
