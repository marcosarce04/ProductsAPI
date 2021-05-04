package com.hackerrank.eshopping.product.dashboard.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ComplexProduct implements Comparable<ComplexProduct> {

    private Product product;
    private Double disc;

    @Override
    public int compareTo(ComplexProduct o) {
       return o.getDisc().compareTo(this.getDisc());
    }
}
