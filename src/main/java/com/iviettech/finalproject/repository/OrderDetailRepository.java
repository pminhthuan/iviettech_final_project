package com.iviettech.finalproject.repository;

import com.iviettech.finalproject.entity.OrderDetailEntity;
import com.iviettech.finalproject.entity.ProvinceEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderDetailRepository extends CrudRepository<OrderDetailEntity, Integer> {

    List<OrderDetailEntity> findByOrderEntityId(int id);
}
