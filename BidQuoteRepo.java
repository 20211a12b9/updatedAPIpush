package com.vms.medxbid.repositories;


import com.vms.medxbid.models.BidQuote;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BidQuoteRepo extends JpaRepository<BidQuote, Long> {
    @Query("SELECT b.distUser.userId, b.price FROM BidQuote b WHERE b.bidRequest.bidRequestId = :bidRequestId")
    List<Object[]> findDistUserIdAndPriceByBidRequestId(@Param("bidRequestId") Long bidRequestId);
//    @Query("SELECT b.distUser.id, b.price,b.bidRequest.bidRequestId FROM BidQuote b")
//    List<Object[]> findAllDistUserIdAndPrice();

    @Query(value = "SELECT b.dist_user_id, b.price, b.bid_request_id, b.discount, b.delivery_in " +
            "FROM bid_quote b " +
            "INNER JOIN bid_request br ON b.bid_request_id = br.bid_request_id " +
            "WHERE br.customer_status = false " +
            "AND br.price_done = true " +
            "AND b.user_id = :userId " +  // Added condition to filter by userId
            "ORDER BY br.bid_request_id DESC",
            nativeQuery = true)
    List<Object[]> findAllDistUserIdAndPrice(@Param("userId") String userId);




}
