package com.vms.medxbid.models;

public class BidRequestQty {

    private Integer bidRequestId;
    private String bidDate;
    private Integer qty;
    private String paymentIN; // Include paymentIN field

    public BidRequestQty() {
    }

    public BidRequestQty(Integer bidRequestId, String bidDate, Integer qty, String paymentIN) {
        this.bidRequestId = bidRequestId;
        this.bidDate = bidDate;
        this.qty = qty;
        this.paymentIN = paymentIN;
    }

    public Integer getBidRequestId() {
        return bidRequestId;
    }

    public void setBidRequestId(Integer bidRequestId) {
        this.bidRequestId = bidRequestId;
    }

    public String getBidDate() {
        return bidDate;
    }

    public void setBidDate(String bidDate) {
        this.bidDate = bidDate;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public String getPaymentIN() {
        return paymentIN;
    }

    public void setPaymentIN(String paymentIN) {
        this.paymentIN = paymentIN;
    }

    @Override
    public String toString() {
        return "BidRequestQty [bidRequestId=" + bidRequestId + ", bidDate=" + bidDate + ", qty=" + qty + ", paymentIN=" + paymentIN + "]";
    }
}
