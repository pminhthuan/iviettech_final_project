package com.iviettech.finalproject.repository;

import com.iviettech.finalproject.entity.ProductEntity;
import com.iviettech.finalproject.entity.ProductImageEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductImageRepository extends CrudRepository<ProductImageEntity, Integer> {
    @Query(value = "select * from products as p left join product_image  as i on p.id = i.product_id\n" +
            "left join (select p.id, p.name, sum(pt.quantity) from products as p left join product_detail as pt\n" +
            "on pt.product_id = p.id group by p.id) as q on q.id = p.id\n" +
            "where i.is_main_image = 1 and q.sum > 0",
            nativeQuery = true)
    List<ProductImageEntity> getProductListWithImage();

    @Query(value = "select * from products as p left join product_image  as i on p.id = i.product_id\n" +
            "left join (select p.id, p.name, sum(pt.quantity) from products as p \n" +
            "left join product_detail as pt on pt.product_id = p.id group by p.id) as q on q.id = p.id\n" +
            "left join category_detail as cd ON cd.id = p.category_detail_id\n" +
            "left join categories as c ON c.id = cd.category_id\n" +
            "where i.is_main_image = 1 and q.sum > 0 and c.id = ?1 ",
            nativeQuery = true)
    List<ProductImageEntity> getProductListWithImageAndCategory(int id);


    @Query(value = "select * from products as p left join product_image  as i on p.id = i.product_id\n" +
            "left join (select p.id, p.name, sum(pt.quantity) from products as p \n" +
            "left join product_detail as pt on pt.product_id = p.id group by p.id) as q on q.id = p.id\n" +
            "where i.is_main_image = 1 and q.sum > 0 and p.category_detail_id = ?1 ",
            nativeQuery = true)
    List<ProductImageEntity> getProductListWithImageAndCategoryDetail(int id);

    @Query(value = "select * from products as p left join product_image  as i on p.id = i.product_id\n" +
            "left join (select od.product_id, sum(od.quantity) from order_details as od group by od.product_id) \n" +
            "as o on o.product_id = p.id \n" +
            "left join (select p.id, p.name, sum(pt.quantity) from products as p \n" +
            "left join product_detail as pt on pt.product_id = p.id group by p.id) as q on q.id = p.id\n" +
            "where i.is_main_image = 1 and q.sum > 0 and o.sum is not null\n" +
            "order by o.sum desc ",
            nativeQuery = true)
    List<ProductImageEntity> getProductListBestSeller();

    @Query(value = "select * from products as p left join product_image  as i on p.id = i.product_id\n" +
            "left join (select p.id, p.name, sum(pt.quantity) from products as p \n" +
            "left join product_detail as pt on pt.product_id = p.id group by p.id) as q on q.id = p.id\n" +
            "left join (select product_id, avg(star_value) from rating group by product_id) as r on r.product_id = p.id\n" +
            "where i.is_main_image = 1 and q.sum > 0 \n" +
            "order by r.avg desc",
            nativeQuery = true)
    List<ProductImageEntity> getProductListHighRating();

    @Query(value = "select * from products as p left join product_image  as i on p.id = i.product_id\n" +
            "left join (select p.id, p.name, sum(pt.quantity) from products as p \n" +
            "left join product_detail as pt on pt.product_id = p.id group by p.id) as q on q.id = p.id\n" +
            "where i.is_main_image = 1 and q.sum > 0 \n" +
            "order by p.add_date desc",
            nativeQuery = true)
    List<ProductImageEntity> getProductListNewness();

    @Query(value = "select * from products as p left join product_image  as i on p.id = i.product_id\n" +
            "left join (select p.id, p.name, sum(pt.quantity) from products as p \n" +
            "left join product_detail as pt on pt.product_id = p.id group by p.id) as q on q.id = p.id\n" +
            "where i.is_main_image = 1 and q.sum > 0 \n" +
            "order by p.actual_price asc",
            nativeQuery = true)
    List<ProductImageEntity> getProductListLowToHighPrice();

    @Query(value = "select * from products as p left join product_image  as i on p.id = i.product_id\n" +
            "left join (select p.id, p.name, sum(pt.quantity) from products as p \n" +
            "left join product_detail as pt on pt.product_id = p.id group by p.id) as q on q.id = p.id\n" +
            "where i.is_main_image = 1 and q.sum > 0 \n" +
            "order by p.actual_price desc",
            nativeQuery = true)
    List<ProductImageEntity> getProductListHighToLowPrice();


    List<ProductImageEntity> findByProduct_Id(int id);


    @Query(value = "select * from products as p left join product_image  as i on p.id = i.product_id\n" +
            "left join (select p.id, p.name, sum(pt.quantity) from products as p\n" +
            "left join product_detail as pt on pt.product_id = p.id group by p.id) as q on q.id = p.id\n" +
            "left join category_detail as cd ON cd.id = p.category_detail_id\n" +
            "where i.is_main_image = 1 and q.sum > 0 and cd.id = ?1\n" +
            "except select * from products as p left join product_image  as i on p.id = i.product_id\n" +
            "left join (select p.id, p.name, sum(pt.quantity) from products as p\n" +
            "left join product_detail as pt on pt.product_id = p.id group by p.id) as q on q.id = p.id\n" +
            "left join category_detail as cd ON cd.id = p.category_detail_id where p.id = ?2",
            nativeQuery = true)
    List<ProductImageEntity> getRelatedProductByCategoryDetail(int cateDetailId, int productId);


    @Query(value = "select * from products as p \n" +
            "left join (select * from product_image as i where i.is_main_image = 1) as i on p.id = i.product_id\n" +
            "left join (select p.id, p.name, sum(pt.quantity) from products as p \n" +
            "left join product_detail as pt on pt.product_id = p.id group by p.id) as q on q.id = p.id\n" +
            "left join category_detail as cd ON cd.id = p.category_detail_id\n" +
            "left join categories as c ON c.id = cd.category_id\n" +
            "where i.is_main_image = 1 and q.sum > 0 \n" +
            "and p.name ilike %:cate_name% or c.name ilike %:product_name%",
            nativeQuery = true)
    List<ProductImageEntity> getProductBySearch(@Param("cate_name") String cateName, @Param("product_name") String productName);

}
