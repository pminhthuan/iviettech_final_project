package com.iviettech.finalproject.repository;

import com.iviettech.finalproject.entity.OrderEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface OrderRepository extends CrudRepository<OrderEntity, Integer> {

        @Transactional
        @Modifying
        @Query("update OrderEntity o set o.confirmCode = null where o.email = ?1 and o.id = ?2")
        int confirmOrder(String email, String orderCode);
}
