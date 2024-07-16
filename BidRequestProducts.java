package com.vms.medxbid.models;

public class BidRequestProducts {
    private Integer quantity;
    private String productName;


    public BidRequestProducts() {
    }

    public BidRequestProducts(String productName,Integer quantity) {

        this.quantity = quantity;
        this.productName = productName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }



}
