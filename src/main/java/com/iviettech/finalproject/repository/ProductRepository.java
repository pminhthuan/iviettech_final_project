package com.iviettech.finalproject.repository;

import com.iviettech.finalproject.entity.ProductEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<ProductEntity, Integer> {
    @Query(value = "select p.category_detail_id from products p where p.id = ?1", nativeQuery = true)
    int getCategoryDetailIdByProductId(int id);

    @Query(value = "SELECT COUNT(*) AS productInYear FROM products WHERE date_part('year',add_date) = date_part('year',CURRENT_DATE)", nativeQuery = true)
    Integer getCountProductInYear();

    @Query(value = "SELECT COUNT(*) FROM products", nativeQuery = true)
    Integer getCountAllProduct();

    @Query(value = "SELECT COUNT(*) AS productInMonth FROM products WHERE date_trunc('month',add_date) = date_trunc('month',CURRENT_DATE)", nativeQuery = true)
    Integer getCountProductInMonth();

    @Query(value = "SELECT * FROM products WHERE date_part('year',add_date) = date_part('year',CURRENT_DATE)", nativeQuery = true)
    List<ProductEntity> getListProductInYear();

    @Query(value = "SELECT * FROM products WHERE date_trunc('month',add_date) = date_trunc('month',CURRENT_DATE)", nativeQuery = true)
    List<ProductEntity> getListProductInMonth();


}
