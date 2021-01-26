package com.hackerrank.eshopping.product.dashboard.model;

public class ComplexProduct implements Comparable<ComplexProduct> {

    private Product product;
    private Double disc;

    public ComplexProduct(Product product, Double disc) {
        this.product = product;
        this.disc = disc;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Double getDisc() {
        return disc;
    }

    public void setDisc(Double disc) {
        this.disc = disc;
    }

    @Override
    public int compareTo(ComplexProduct o) {
       return o.getDisc().compareTo(this.getDisc());
    }
}
