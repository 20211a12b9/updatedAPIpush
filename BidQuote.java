package com.vms.medxbid.models;
import java.io.Serializable;
import java.sql.Time;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;



/**
 * The persistent class for the bid_quote database table.
 *
 */
@Entity
@Table(name="bid_quote")
@NamedQuery(name="BidQuote.findAll", query="SELECT b FROM BidQuote b")
public class BidQuote implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="bid_quote_id")
	private Integer bidQuoteId;

	private double price;

	private Integer discount;

	private String deliveryIn;

	private String userId;
	//bi-directional many-to-one association to BidRequest
	@ManyToOne
	@JoinColumn(name="bid_request_id")
	@JsonBackReference(value = "bidQuote-backref")
	private BidRequest bidRequest;

	//bi-directional many-to-one association to DistUser
	@ManyToOne
	@JoinColumn(name="dist_user_id")
	@JsonIgnore
	private DistUser distUser;

	public BidQuote() {
	}

	public Integer getBidQuoteId() {
		return this.bidQuoteId;
	}

	public void setBidQuoteId(Integer bidQuoteId) {
		this.bidQuoteId = bidQuoteId;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public void setDeliveryIn(String deliveryIn) {
		this.deliveryIn = deliveryIn;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public BidRequest getBidRequest() {
		return this.bidRequest;
	}

	public void setBidRequest(BidRequest bidRequest) {
		this.bidRequest = bidRequest;
	}

	public DistUser getDistUser() {
		return this.distUser;
	}

	public void setDistUser(DistUser distUser) {
		this.distUser = distUser;
	}

	public void setDistributor(Long distributorId) {
	}

	public void setPrice(Double aDouble) {
		this.price=price;
	}
}
