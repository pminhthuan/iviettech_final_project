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

    List<ProductDetailEntity> findProductDetailEntityByProduct_Id(int id);

    @Modifying
    @Transactional
    @Query(value = "update product_detail set quantity = quantity - ?1\n" +
            "where product_id = ?2 and color = ?3 and size = ?4",
            nativeQuery = true)
    void decreaseProductQuantity(int quantity,int id, String color, String size);

    @Query(value = "select p.id from product_detail p where p.product_id = ?1 and p.color = ?2 and p.size = ?3",
            nativeQuery = true)
    int findProductDetailId(int id, String color, String size);

}
