package com.iviettech.finalproject.repository;

import com.iviettech.finalproject.entity.ProductEntity;
import com.iviettech.finalproject.entity.ProductImageEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductImageRepository extends CrudRepository<ProductImageEntity, Integer> {
    @Query(value = "select * from products as p left join product_image  as i on p.id = i.product_id\n" +
            "where i.is_main_image = 1",
            nativeQuery = true)
    List<ProductImageEntity> getProductListWithImage();
}
