package com.iviettech.finalproject.repository;

import com.iviettech.finalproject.entity.ProductEntity;
import com.iviettech.finalproject.entity.ProductImageEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductRepository extends CrudRepository<ProductEntity, Integer> {
    @Query(value = "select p.category_detail_id from products p where p.id = ?1", nativeQuery = true)
    int getCategoryDetailIdByProductId(int id);
}
