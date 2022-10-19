package com.iviettech.finalproject.repository;

import com.iviettech.finalproject.entity.ProductDetailEntity;
import com.iviettech.finalproject.entity.ProductImageEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductDetailRepository extends CrudRepository<ProductDetailEntity, Integer> {

    @Query(value = "select distinct d.color from ProductDetailEntity as d inner join ProductEntity as p on p.id = d.product.id\n" +
            "where d.product.id = ?1")
    List<String> getColorByProductId(int id);

    @Query(value = "select distinct d.size from ProductDetailEntity as d inner join ProductEntity as p on p.id = d.product.id\n" +
            "where d.product.id = ?1 order by d.size asc")
    List<String> getSizeByProductId(int id);

}
