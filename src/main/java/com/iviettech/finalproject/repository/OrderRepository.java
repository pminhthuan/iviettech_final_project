package com.iviettech.finalproject.repository;

import com.iviettech.finalproject.entity.OrderEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

public interface OrderRepository extends CrudRepository<OrderEntity, Integer> {
    @Modifying
    @Transactional
    @Query(value = "SELECT o FROM orders o WHERE o.require_date = ?1", nativeQuery = true)
    List<OrderEntity> getOrderEntitiesByRequireDate1(String dt);

    @Query(value = "SELECT o FROM orders o WHERE o.require_date BETWEEN ?1 AND ?2", nativeQuery = true)
    Date getOrderEntitiesByRequireDate2(String dt1, String dt2);

}
