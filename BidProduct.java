package com.vms.medxbid.models;


import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;


/**
 * The persistent class for the bid_products database table.
 * 
 */
@Entity
@Table(name="bid_products")
@NamedQuery(name="BidProduct.findAll", query="SELECT b FROM BidProduct b")
public class  BidProduct implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="bid_product_id")
	private Integer bidProductId;


	//bi-directional many-to-one association to BidRequest
	@ManyToOne
	@JoinColumn(name="bid_request_id")
	@JsonBackReference(value = "bidRequest-backref")
	private BidRequest bidRequest;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="product_id")
	@JsonBackReference(value = "product-backref")
	private Product product;

	@Column(name="qty")
	private Integer quantity;

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BidProduct() {
	}

	public Integer getBidProductId() {
		return this.bidProductId;
	}

	public void setBidProductId(Integer bidProductId) {
		this.bidProductId = bidProductId;
	}

	public BidRequest getBidRequest() {
		return this.bidRequest;
	}

	public void setBidRequest(BidRequest bidRequest) {
		this.bidRequest = bidRequest;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}