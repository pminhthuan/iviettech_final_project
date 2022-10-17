package com.iviettech.finalproject.repository;

import com.iviettech.finalproject.entity.ProductEntity;
import com.iviettech.finalproject.entity.ProductImageEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductImageRepository extends CrudRepository<ProductImageEntity, Integer> {
//    @Transactional
//    @Modifying
//    @Query("select * from ProductEntity p inner join ProductImageEntity i on p.id = i.product.id where i.isMainImage = 1")
//    List<ProductImageEntity> getProductListWithImage();
}
