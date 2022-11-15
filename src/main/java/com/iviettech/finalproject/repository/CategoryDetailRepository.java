package com.iviettech.finalproject.repository;

import com.iviettech.finalproject.entity.CategoryDetailEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryDetailRepository extends CrudRepository<CategoryDetailEntity, Integer> {

    @Query(value = "select * from categories c left join category_detail ct on c.id = ct.category_id\n" +
            "where ct.id = ?1",
            nativeQuery = true)
    CategoryDetailEntity findAllByCategoryDetailId(int cateDetailId);

    List<CategoryDetailEntity> findAllByCategory_Id(int categoryId);

    @Query(value = "select ct.category_id from category_detail ct where ct.id = ?1", nativeQuery = true)
    int getCategoryIdByCateDetail(int id);

    @Query(value = "SELECT count(*) FROM category_detail", nativeQuery = true)
    int getAllCategoryDetail();
}
