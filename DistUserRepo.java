package com.vms.medxbid.repositories;

import com.vms.medxbid.models.DistUser;
import com.vms.medxbid.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DistUserRepo extends JpaRepository<DistUser,Integer> {


    Optional<DistUser> findByUserName(String userName);
}
