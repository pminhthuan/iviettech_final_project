package com.iviettech.finalproject.repository;

import com.iviettech.finalproject.entity.ProvinceEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProvinceRepository extends CrudRepository<ProvinceEntity, Integer> {
    @Query(value = "select d.code, d.name_en from provinces p inner join districts d on p.code = d.province_code\n" +
            "where p.code = ?1",
            nativeQuery = true)
    List<Object[]> getDistrictByProvince(int provinceId);
}
