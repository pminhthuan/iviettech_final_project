package com.iviettech.finalproject.repository;

import com.iviettech.finalproject.entity.CategoryDetailEntity;
import com.iviettech.finalproject.entity.CategoryEntity;
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
}
