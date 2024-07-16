package com.vms.medxbid.repositories;

import com.vms.medxbid.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    @Query("SELECT c.address,c.firm_name,c.gst,c.Dl_number,c.pincode,c.drug_license FROM Customer c WHERE c.user.id = :userId")
    Optional<String> findAddressByUserId(Long userId);
}
