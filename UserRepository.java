package com.vms.medxbid.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.vms.medxbid.models.User;
import java.util.Optional;

@Repository
public interface UserRepository extends  JpaRepository<User, Long>{
    
    Optional<User> findByUserName(String userName);
}


