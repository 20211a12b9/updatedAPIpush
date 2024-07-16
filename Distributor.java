package com.vms.medxbid.models;

import java.io.Serializable;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "distributor")
public class Distributor implements Serializable {


	@Id
	@GeneratedValue
	@Column(name = "distributor_id")
	private Long distributorId;
	@Column(name = "address")
	private String address;

	@Column(name = "distributor_name")
	private String distributorName;

	@Column(name = "drug_license")
	private String drug_license;
	@Column(name="Dl_number")
	private String Dl_number;
	@Column(name = "gst")
	private String gst;
	@Column(name = "latitude")
	private String latitude;
	@Column(name = "longitude")
	private String longitude;
	@Column(name = "pincode")
	private String pincode;
//	private String content;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private DistUser distUser;

	//	public String getContent() {
//		return content;
//	}
//
//	public void setContent(String content) {
//		this.content = content;
//	}
	public String getdrugliseance() {
		return drug_license;
	}

}
