package com.iviettech.finalproject.repository;

import com.iviettech.finalproject.entity.OrderEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends CrudRepository<OrderEntity, Integer> {
//    @Modifying
//    @Transactional
//    @Query(value = "SELECT o FROM orders o WHERE o.require_date = ?1", nativeQuery = true)
    List<OrderEntity> findByRequireDate(java.util.Date date);

    @Query(value = "SELECT * FROM orders WHERE date_part('week',require_date) = date_part('week',CURRENT_DATE)", nativeQuery = true)
    List<OrderEntity> getOrderWeek();

    @Query(value = "SELECT * FROM orders WHERE date_part('month',require_date) = date_part('month',CURRENT_DATE)", nativeQuery = true)
    List<OrderEntity> getOrderMonth();

    @Query(value = "SELECT * FROM orders WHERE date_part('year',require_date) = date_part('year',CURRENT_DATE)", nativeQuery = true)
    List<OrderEntity> getOrderYear();


    @Query(value = "SELECT * FROM orders WHERE require_date BETWEEN ?1 AND ?2", nativeQuery = true)
    List<OrderEntity> getOrderEntitiesByRequireDateInMonth(Date date1, Date date2);

//    List<OrderEntity> findByRequireDateMonth(Date date);

}
