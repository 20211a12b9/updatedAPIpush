package com.vms.medxbid.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vms.medxbid.models.BidRequest;

import jakarta.persistence.Tuple;

@Repository
public interface BidRequestRepository extends JpaRepository<BidRequest, Long>{

//
    @Query(value = "SELECT bid_request.bid_request_id AS bid_request_id, " +
            "TO_CHAR(bid_date, 'DD/MM/YYYY HH24:MI:SS') AS bid_date, " +
            "SUM(bid_products.qty) AS qty, " +
            "bid_request.paymentin AS paymentIN " +
            "FROM bid_request " +
            "INNER JOIN bid_products ON bid_request.bid_request_id = bid_products.bid_request_id " +
            "WHERE bid_request.user_id = :userId " +
            "GROUP BY bid_request.bid_request_id, bid_date, bid_request.paymentin " +
            "ORDER BY bid_request.bid_request_id DESC", nativeQuery = true)
    List<Tuple> findBidRequestQty(int userId);
    //
    @Query(value = "select product.product_name, sum(bid_products.qty) " +
            "from bid_request " +
            "inner join bid_products on bid_request.bid_request_id = bid_products.bid_request_id " +
            "inner join product on bid_products.product_id = product.product_id " +
            "where bid_request.bid_request_id = :id " +

            "group by product.product_name", nativeQuery = true)
    List<Tuple> fetchBidRequestProductDetails(Long id);

    @Query(value = "SELECT " +
            "   p.product_name, " +
            "   SUM(bp.qty) AS total_quantity, " +
            "   bq.price " +
            "FROM " +
            "   bid_request br " +
            "   INNER JOIN bid_products bp ON br.bid_request_id = bp.bid_request_id " +
            "   INNER JOIN product p ON bp.product_id = p.product_id " +
            "   LEFT JOIN bid_quote bq ON br.bid_request_id = bq.bid_request_id " + // Adjust to LEFT JOIN if bid_quote might be missing
            "WHERE " +
            "   br.bid_request_id = :id " +
            "GROUP BY " +
            "   p.product_name, " +
            "   bq.price", // Include price in GROUP BY to handle NULL prices
            nativeQuery = true)
    List<Tuple> fetchBidRequestProductDetailsWithPrice(Long id);


    @Query(value = "SELECT br.bid_request_id AS bid_request_id, " +
            "TO_CHAR(br.bid_date, 'DD/MM/YYYY HH24:MI:SS') AS bid_date, " +
            "COUNT(DISTINCT bp.product_id) AS qty " +
            "FROM bid_request br " +
            "INNER JOIN bid_products bp ON br.bid_request_id = bp.bid_request_id " +
            "WHERE br.customer_status = false " +
            "GROUP BY br.bid_request_id, br.bid_date " +
            "ORDER BY br.bid_request_id DESC", nativeQuery = true)
    List<Tuple> findAllBidRequests();
    @Query(value = "SELECT br.bid_request_id AS bid_request_id, " +
            "TO_CHAR(br.bid_date, 'DD/MM/YYYY HH24:MI:SS') AS bid_date, " +
            "SUM(bp.qty) AS qty " +
            "FROM bid_request br " +
            "INNER JOIN bid_products bp ON br.bid_request_id = bp.bid_request_id " +
            "WHERE br.customer_status = false " +
            "AND br.del_status = false " +
            "AND br.dist_status = false " +
            "AND br.price_done = false "+
            "GROUP BY br.bid_request_id, br.bid_date " +
            "ORDER BY br.bid_request_id DESC", nativeQuery = true)
    List<Tuple> findAllBidDistRequests();
    @Query(value = "SELECT COUNT(br.bid_request_id) " +
            "FROM bid_request br " +
            "INNER JOIN bid_products bp ON br.bid_request_id = bp.bid_request_id " +
            "WHERE br.customer_status = false " +
            "AND br.del_status = false " +
            "AND br.dist_status = false " +
            "AND br.price_done = false", nativeQuery = true)
    Long findCountAllBidDistRequests();

    @Query(value = "SELECT br.bid_request_id AS bid_request_id, " +
            "TO_CHAR(br.bid_date, 'DD/MM/YYYY HH24:MI:SS') AS bid_date, " +
            "SUM(bp.qty) AS qty " +
            "FROM bid_request br " +
            "INNER JOIN bid_products bp ON br.bid_request_id = bp.bid_request_id " +
            "WHERE br.customer_status = true " +
            "AND br.del_status = false " +
            "AND br.dist_status = false " +
            "GROUP BY br.bid_request_id, br.bid_date " +
            "ORDER BY br.bid_request_id DESC", nativeQuery = true)
    List<Tuple> findAllBidPendingRequests();
    @Query(value = "SELECT br.bid_request_id AS bid_request_id, " +
            "TO_CHAR(br.bid_date, 'DD/MM/YYYY HH24:MI:SS') AS bid_date, " +
            "SUM(bp.qty) AS qty " +
            "FROM bid_request br " +
            "INNER JOIN bid_products bp ON br.bid_request_id = bp.bid_request_id " +
            "WHERE br.customer_status = true " +
            "AND br.del_status = false " +
            "AND br.dist_status = true " +
            "GROUP BY br.bid_request_id, br.bid_date " +
            "ORDER BY br.bid_request_id DESC", nativeQuery = true)
    List<Tuple> findAllBidProcessRequests();
    @Query(value = "SELECT br.bid_request_id AS bid_request_id, " +
            "TO_CHAR(br.bid_date, 'DD/MM/YYYY HH24:MI:SS') AS bid_date, " +
            "SUM(bp.qty) AS qty " +
            "FROM bid_request br " +
            "INNER JOIN bid_products bp ON br.bid_request_id = bp.bid_request_id " +
            "WHERE br.customer_status = true " +
            "AND br.del_status = false " +
            "AND br.dist_status = false " +
            "AND br.user_id = :userId " +
            "GROUP BY br.bid_request_id, br.bid_date " +
            "ORDER BY br.bid_request_id DESC", nativeQuery = true)
    List<Tuple> findAllCustomerBidRequests(@Param("userId") Long userId);
    @Query(value = "SELECT br.bid_request_id AS bid_request_id, " +
            "TO_CHAR(br.bid_date, 'DD/MM/YYYY HH24:MI:SS') AS bid_date, " +
            "SUM(bp.qty) AS qty " +
            "FROM bid_request br " +
            "INNER JOIN bid_products bp ON br.bid_request_id = bp.bid_request_id " +
            "WHERE br.customer_status = true " +
            "AND br.del_status = true " +
            "AND br.dist_status = true " +
            "GROUP BY br.bid_request_id, br.bid_date " +
            "ORDER BY br.bid_request_id DESC", nativeQuery = true)
    List<Tuple> findAllBidDeliveredRequests();


    @Query("SELECT b.CustUser.id FROM BidRequest b WHERE b.bidRequestId = :bidRequestId")
    Integer findUserIdByBidRequestId(Integer bidRequestId);
}
