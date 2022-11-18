package com.iviettech.finalproject.repository;

import com.iviettech.finalproject.entity.OrderDetailEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderDetailRepository extends CrudRepository<OrderDetailEntity, Integer> {

    List<OrderDetailEntity> findByOrderEntityId(int id);
//    @Query(value = "SELECT products.name,sum(order_details.quantity) as sumall FROM order_details inner join orders ON orders.id = order_details.order_id inner join products ON products.id = order_details.product_id where date_trunc('month',require_date) = date_trunc('month',CURRENT_DATE) GROUP BY products.name LIMIT 5", nativeQuery = true)
//    List<SumProductOrderInMonth> Top5ProductInMounth();
}
