package com.vms.medxbid.repositories;

import com.vms.medxbid.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepo extends JpaRepository<User,Long> {

    User findById(long id);
    Optional<User> findByUserName(String userName);

    @Query("SELECT userName FROM User  WHERE id = :userId")
    String findUsernameById(@Param("userId") Long userId);


}
