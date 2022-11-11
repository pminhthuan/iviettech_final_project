package com.iviettech.finalproject.repository;

import com.iviettech.finalproject.entity.OrderEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends CrudRepository<OrderEntity, Integer> {
//    @Modifying
//    @Transactional
//    @Query(value = "SELECT o FROM orders o WHERE o.require_date = ?1", nativeQuery = true)
    List<OrderEntity> findByRequireDate(java.util.Date date);

    @Query(value = "SELECT * FROM orders", nativeQuery = true)
    List<OrderEntity> getOrder();

    @Query(value = "SELECT * FROM orders WHERE date_part('week',require_date) = date_part('week',CURRENT_DATE)", nativeQuery = true)
    List<OrderEntity> getOrderWeek();

    @Query(value = "SELECT * FROM orders WHERE date_trunc('month',require_date) = date_trunc('month',CURRENT_DATE)", nativeQuery = true)
    List<OrderEntity> getOrderMonth();

    @Query(value = "SELECT * FROM orders WHERE date_trunc('year',require_date) = date_trunc('year',CURRENT_DATE)", nativeQuery = true)
    List<OrderEntity> getOrderYear();


    @Query(value = "SELECT * FROM orders WHERE (require_date BETWEEN ?1 AND ?2) AND order_status = ?3", nativeQuery = true)
    List<OrderEntity> getOrderFromTo(Date date1, Date date2, int status);

    List<OrderEntity> findTop5ByOrderStatusOrderByIdDesc(int orderStatus);

    @Query(value = "SELECT sum(total_amount) FROM orders WHERE date_part('day',require_date) = date_part('day',CURRENT_DATE) AND order_status = 0", nativeQuery = true)
    Double getTotalDay();

}
