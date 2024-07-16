package com.vms.medxbid.models;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * The persistent class for the bid_request database table.
 *
 */
@Entity
@Table(name="bid_request")
@NamedQuery(name="BidRequest.findAll", query="SELECT b FROM BidRequest b")
public class BidRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="bid_request_id")
	private Integer bidRequestId;

	@Temporal(TemporalType.DATE)
	@Column(name="bid_date", insertable = false)
	private Date bidDate;

	@Column(name="CustomerStatus")
	private boolean customerstatus;

	@Column(name="DistStatus")
	private boolean diststatus ;

	@Column(name="DelStatus")
	private boolean delstatus ;

	@Column(name="PriceDone")
	private boolean pricedone ;

	@Column(name="paymentin") // Ensure the column name matches your database schema
	private String paymentIN;

	// bi-directional many-to-one association to BidProduct
	@OneToMany(mappedBy="bidRequest", cascade = CascadeType.ALL)
	@JsonManagedReference(value = "bidRequest-backref")
	private List<BidProduct> bidProducts;

	// bi-directional many-to-one association to BidQuote
	@OneToMany(mappedBy="bidRequest")
	@JsonManagedReference(value = "bidQuote-backref")
	private List<BidQuote> bidQuotes;

	// bi-directional many-to-one association to CustUser
	@ManyToOne
	@JoinColumn(name="user_id")
	@JsonBackReference(value = "user-backref")
	private User CustUser;



	public Integer getBidRequestId() {
		return this.bidRequestId;
	}

	public void setBidRequestId(Integer bidRequestId) {
		this.bidRequestId = bidRequestId;
	}

	public Date getBidDate() {
		return this.bidDate;
	}

	public void setBidDate(Date bidDate) {
		this.bidDate = bidDate;
	}

//	public boolean isCustomerstatus() {
//		return customerstatus;
//	}
//
//	public void setCustomerstatus() {
//		this.customerstatus = customerstatus;
//	}
//
//	public boolean isDiststatus() {
//		return diststatus;
//	}
//
//	public void setDiststatus(boolean diststatus) {
//		this.diststatus = diststatus;
//	}
//
//	public boolean isDelstatus() {
//		return delstatus;
//	}

//	public void setDelstatus(boolean delstatus) {
//		this.delstatus = delstatus;
//	}

	public List<BidProduct> getBidProducts() {
		return this.bidProducts;
	}

	public void setBidProducts(List<BidProduct> bidProducts) {
		this.bidProducts = bidProducts;
	}

	public BidProduct addBidProduct(BidProduct bidProduct) {
		getBidProducts().add(bidProduct);
		bidProduct.setBidRequest(this);

		return bidProduct;
	}

	public BidProduct removeBidProduct(BidProduct bidProduct) {
		getBidProducts().remove(bidProduct);
		bidProduct.setBidRequest(null);

		return bidProduct;
	}

	public List<BidQuote> getBidQuotes() {
		return this.bidQuotes;
	}

	public void setBidQuotes(List<BidQuote> bidQuotes) {
		this.bidQuotes = bidQuotes;
	}

	public BidQuote addBidQuote(BidQuote bidQuote) {
		getBidQuotes().add(bidQuote);
		bidQuote.setBidRequest(this);

		return bidQuote;
	}

	public BidQuote removeBidQuote(BidQuote bidQuote) {
		getBidQuotes().remove(bidQuote);
		bidQuote.setBidRequest(null);

		return bidQuote;
	}

	public User getCustUser() {
		return this.CustUser;
	}

	public void setCustUser(User custUser) {
		this.CustUser = custUser;
	}
	public void setCustomerstatus(boolean customerstatus) {
		this.customerstatus = customerstatus;
	}
	public void setdiststatus(boolean diststatus) {
		this.diststatus = diststatus;
	}
	public void setdeliverstatus(boolean delstatus) {
		this.delstatus = delstatus;
	}
	public boolean isPricedone() {
		return pricedone;
	}

	public void setPricedone(boolean pricedone) {
		this.pricedone = pricedone;
	}

	public String getPaymentIN() {
		return paymentIN;
	}

	public void setPaymentIN(String paymentIN) {
		this.paymentIN = paymentIN;
	}
}
