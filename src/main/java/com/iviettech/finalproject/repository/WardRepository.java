package com.iviettech.finalproject.repository;

import com.iviettech.finalproject.entity.DistrictEntity;
import com.iviettech.finalproject.entity.WardEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface WardRepository extends CrudRepository<WardEntity, Integer> {
    @Query(value = "select * from wards w where w.district_code = ?1",
            nativeQuery = true)
    List<WardEntity> findByDistrict_Code(int districtCode);
}
