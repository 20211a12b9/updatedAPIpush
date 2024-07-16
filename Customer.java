package com.vms.medxbid.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="customer")
public class Customer {

    @Id
    @GeneratedValue
    @Column(name="customer_id")
    private Long customer_id;
    @Column(name="firm_name")
    private String firm_name;

    @Column(name = "drug_licence")
    private String drug_license ;

    // Method to set drug_license with the full path including filename

    @Column(name="Dl_number")
    private String Dl_number;
    @Column(name = "gst")
    private String gst;
    @Column(name="latitude")
    private String latitude;
    @Column(name="longitude")
    private String longitude;
    @Column(name="address")
    private String address;
    @Column(name = "pincode")
    private String pincode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "foreignkey") // Assuming this is the foreign key column in the customer table
    private User user;


    public String getdrugliseance() {
        return drug_license;
    }

//    public void setDrugLicense(String drug_license) {
//        this.drug_license=this.drug_license+drug_license;
//    }


//    public String getdrug_license() {
//        return drug_license;
//    }
}
