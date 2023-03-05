package com.TechM.springDemoProject.Repositories;

import com.TechM.springDemoProject.Models.Customer;

import com.TechM.springDemoProject.Controllers.CustomerMarketDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {




    @Query(value = "SELECT m FROM Customer m")
    List<Customer> getAllCustomers();

    @Query(value = "SELECT c FROM Customer c where c.id= :customerID")
    Customer getCustomerById(@Param("customerID") Integer id);

    @Query(value = "SELECT c FROM Customer c where c.customerFirstName= :customerFirstName")
    Customer getCustomerByFirstName(@Param("customerFirstName") String customerFirstName);

    @Query(value = "SELECT c FROM Customer c where c.customerSecondName= :customerSecondName")
    Customer getCustomerBySecondName(@Param("customerSecondName") String customerSecondName);

    @Query(value = "SELECT c FROM Customer c where c.contact= :contact")
    Customer getCustomerByContact(@Param("contact") String contact);

    @Query(value = "SELECT c FROM Customer c WHERE c.market.id = :Market_Id")
    List<Customer> findByMarketId(@Param("Market_Id") Integer Market_Id);

    @Query(value = "SELECT new  com.TechM.springDemoProject.Controllers.CustomerMarketDTO(c.customerFirstName, c.customerSecondName, m.name) " + "FROM Customer c " + "JOIN c.market m " + "WHERE m.id = :Market_Id")
    List<CustomerMarketDTO> findCustomerByMarketId(@Param("Market_Id") Integer Market_Id);


    @Query("SELECT c FROM Customer c WHERE c.isActive = true")
    List<Customer> findAllActive();

    @Query("SELECT c FROM Customer c WHERE c.isActive = false")
    List<Customer> findAllInActive();





    @Query("SELECT c FROM Customer c WHERE c.id = (SELECT MAX(c.id) FROM Customer c)")

    Customer findTopByOrderById();

    @Query("SELECT c FROM Customer c ORDER BY c.updatedDate DESC")
    List<Customer> findTopByOrderByUpdated();

    @Modifying
    @Transactional
    @Query("DELETE FROM Customer c WHERE c.id = :id")
    void deleteByIdIsActive(@Param("id") Integer id);


    @Modifying
    @Transactional
    @Query("DELETE FROM Customer c WHERE c.customerFirstName = :customerFirstName")
    void deleteByCustomerFirstName(@Param("customerFirstName") String customerFirstName);


    @Modifying
    @Query("DELETE FROM Customer e")
    void deleteAll();
}



