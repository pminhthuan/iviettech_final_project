package com.iviettech.finalproject.repository;

import com.iviettech.finalproject.entity.ManufactorEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ManufactorRepository extends CrudRepository<ManufactorEntity, Integer> {
    @Query(value = "SELECT count(*) FROM manufactors", nativeQuery = true)
    Integer getAllManu();
}
