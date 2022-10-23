package com.iviettech.finalproject.repository;

import com.iviettech.finalproject.entity.OrderDetailEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderDetailRepository extends CrudRepository<OrderDetailEntity, Integer> {

    List<OrderDetailEntity> findByOrderEntityId(int id);
}
