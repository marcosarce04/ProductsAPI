package com.hackerrank.eshopping.product.dashboard.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ComplexProduct implements Comparable<ComplexProduct> {

  private Product product;
  private Double discount;

  @Override
  public int compareTo(ComplexProduct o) {
    return o.getDiscount().compareTo(this.getDiscount());
  }
}
