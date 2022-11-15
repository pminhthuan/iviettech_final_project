package com.iviettech.finalproject.repository;

import com.iviettech.finalproject.entity.CategoryEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<CategoryEntity, Integer> {
    @Query(value = "SELECT count(*) FROM categories",nativeQuery = true)
    Integer getAllCategory();
}
