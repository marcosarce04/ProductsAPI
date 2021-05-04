package com.hackerrank.eshopping.product.dashboard.model;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Product {

  @Id
  private Long id;
  private String name;
  private String category;
  private Double retail_price;
  private Double discounted_price;
  private Boolean availability;

  @Override
  public String toString() {
    return "{\"id\": " + id + "," +
        " \"name\": \"" + name + "\"," +
        " \"category\": \"" + category + "\"," +
        " \"retail_price\": " + retail_price + "," +
        " \"discounted_price\": " + discounted_price + "," +
        " \"availability\": " + availability + "}";
  }

  @Override
  public boolean equals(Object o) {
      if (this == o) {
          return true;
      }
      if (o == null || getClass() != o.getClass()) {
          return false;
      }
    Product product = (Product) o;
    return Objects.equals(id, product.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
