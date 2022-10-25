package com.iviettech.finalproject.repository;

import com.iviettech.finalproject.entity.DistrictEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DistrictRepository extends CrudRepository<DistrictEntity, Integer> {
    @Query(value = "select * from districts d where d.province_code = ?1",
            nativeQuery = true)
    List<DistrictEntity>findByProvince_Code(int provinceCode);

    @Query(value = "select w.code, w.name_en from districts d inner join wards w on d.code = w.district_code\n" +
            "where d.code = ?1",
            nativeQuery = true)
    List<Object[]> getWardByDistrict(int districtId);
}
