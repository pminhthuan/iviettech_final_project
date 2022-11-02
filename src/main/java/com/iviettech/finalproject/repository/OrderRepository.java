package com.iviettech.finalproject.repository;

import com.iviettech.finalproject.entity.CategoryDetailEntity;
import com.iviettech.finalproject.entity.OrderEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<OrderEntity, Integer> {

        @Query(value = "SELECT * FROM orders o where o.require_date = CURRENT_DATE",
                nativeQuery = true)
        CategoryDetailEntity findByRequireDateDuringTheDate();
        }
