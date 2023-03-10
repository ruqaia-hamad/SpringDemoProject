package com.TechM.springDemoProject.Repositories;

import com.TechM.springDemoProject.Models.Customer;
import com.TechM.springDemoProject.Models.Item;
import com.TechM.springDemoProject.Models.Market;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface MarketRepository extends JpaRepository<Market, Integer> {

    Iterable<Market> findByUpdatedDate(@Param("updatedDate") Date updatedDate);
    Iterable<Market> findByCreatedDate(@Param("createdDate")Date createdDate);
    Iterable<Market> findByCreatedDateAfter(Date date);
    @Query(value = "SELECT m FROM Market m")
    List<Market> getAllMarkets();

    @Query(value = "SELECT m FROM Market m where m.id= :marketId")
    Market getMarketById(@Param("marketId") Integer id);


    @Query(value = "SELECT m FROM Market m where m.name=:name")
    Market getMarketByName(@Param("name") String name);

    @Query("SELECT m FROM Market m WHERE m.isActive = true")
    List<Market> findAllActive();
    @Query("SELECT m FROM Market m WHERE m.isActive = false")
    List<Market> findAllInActive();

    @Query("SELECT m FROM Market m WHERE m.id = (SELECT MAX(m.id) FROM Market m)")

    Market findTopByOrderById();

    @Modifying
    @Transactional
    @Query("DELETE FROM Market m WHERE m.id = :id")
    void deleteByIdIsActive(@Param("id") Integer id);
    @Modifying
    @Transactional
    @Query("DELETE FROM Market m WHERE m.name = :name")
    void deleteByMarketName(@Param("name") String name);

    @Modifying
    @Query("DELETE FROM Market m")
    void deleteAll();

    @Query(value = "SELECT m.id, m.market_name, COUNT(c.id) as customer_count " +
            "FROM Market m " +
            "LEFT JOIN Customer c ON m.id = c.market_id " +
            "GROUP BY m.id, m.market_name " +
            "ORDER BY customer_count DESC", nativeQuery = true)
    List<Object[]> findMarketWithCustomerNumber();



    @Query(value = "SELECT  m FROM Market m where m.createdDate= :createdDate")
    Market getMarketByCreatedDate(@Param("createdDate") Date createdDate);


    @Query(value = "SELECT  m FROM Market m where m.updatedDate= :updatedDate")
    Market getMarketByUpdatedDate(@Param("updatedDate") Date updatedDate);

}
