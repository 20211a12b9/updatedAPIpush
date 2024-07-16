package com.vms.medxbid.repositories;

import com.vms.medxbid.models.DistUser;
import com.vms.medxbid.models.Distributor;
import org.hibernate.sql.ast.tree.expression.JdbcParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DistrubutorRepo extends JpaRepository<Distributor,Integer> {
    @Query("SELECT d.distributorName FROM Distributor d WHERE d.distUser.userId = :distUserId")
    List<String> findDistributorNamesByDistUserId(@Param("distUserId") Long distUserId);
}
