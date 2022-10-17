package com.iviettech.finalproject.repository;

import com.iviettech.finalproject.entity.OrderEntity;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<OrderEntity, Integer> {
        }
