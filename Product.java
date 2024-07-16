package com.vms.medxbid.models;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;




/**
 * The persistent class for the product database table.
 * 
 */
@Entity
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="product_id")
	private Integer productId;

	@Column(name="product_desc")
	private String productDesc;

	@Column(name="product_manu")
	private String productManu;

	@Column(name="product_name")
	private String productName;

	//bi-directional many-to-one association to BidProduct
	@OneToMany(mappedBy="product")
    @JsonManagedReference(value = "product-backref")
	@JsonIgnore
	private List<BidProduct> bidProducts;

	//bi-directional many-to-one association to ProductCategory
	@ManyToOne
	@JoinColumn(name="category_id")
	private ProductCategory productCategory;

	@ManyToOne
	@JoinColumn(name="subcategory_id")
	private SubCategory subCategory;

	public Product() {
	}

	public Integer getProductId() {
		return this.productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductDesc() {
		return this.productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public String getProductManu() {
		return this.productManu;
	}

	public void setProductManu(String productManu) {
		this.productManu = productManu;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public List<BidProduct> getBidProducts() {
		return this.bidProducts;
	}

	public void setBidProducts(List<BidProduct> bidProducts) {
		this.bidProducts = bidProducts;
	}

	public BidProduct addBidProduct(BidProduct bidProduct) {
		getBidProducts().add(bidProduct);
		bidProduct.setProduct(this);

		return bidProduct;
	}

	public BidProduct removeBidProduct(BidProduct bidProduct) {
		getBidProducts().remove(bidProduct);
		bidProduct.setProduct(null);

		return bidProduct;
	}

	public ProductCategory getProductCategory() {
		return this.productCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}

}