package com.iviettech.finalproject.repository;

import com.iviettech.finalproject.entity.ProductDetailEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductDetailRepository extends CrudRepository<ProductDetailEntity, Integer> {

    @Query(value = "select d.color from product_detail as d inner join products as p on p.id = d.product_id\n" +
            "where d.product_id = ?1 group by d.color having sum(d.quantity) > 0", nativeQuery = true)
    List<String> getColorByProductId(int id);

    @Query(value = "select d.size from product_detail as d inner join products as p on p.id = d.product_id\n" +
            "where d.product_id = ?1 group by d.size having sum(d.quantity) > 0", nativeQuery = true)
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

    @Query(value = "select p.quantity from product_detail p where p.id = ?1",
            nativeQuery = true)
    int findQuantity(int id);

    @Query(value = "SELECT COUNT(*) AS productDetailInYear FROM product_detail INNER JOIN products ON products.id = product_detail.product_id WHERE date_part('year',add_date) = date_part('year',CURRENT_DATE)",
            nativeQuery = true)
    Integer getCountProductDetailInYear();

    @Query(value = "SELECT COUNT(*)  FROM product_detail", nativeQuery = true)
    Integer getCountAllProductDetail();

    @Query(value = "SELECT COUNT(*) AS productDetailInMonth FROM product_detail INNER JOIN products ON products.id = product_detail.product_id WHERE date_trunc('month',add_date) = date_trunc('month',CURRENT_DATE)",
            nativeQuery = true)
    Integer getCountProductDetailInMonth();

}
