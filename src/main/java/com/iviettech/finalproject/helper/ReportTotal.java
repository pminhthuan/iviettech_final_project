package com.iviettech.finalproject.helper;

import com.iviettech.finalproject.entity.OrderEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReportTotal {
    private double totalPrice;

    private double totalYear;

    private double totalMonth;

    private double totalWeek;

    private long totalOrder;

    private long yearOrder;

    private long monthOrder;

    private long weekOrder;

    private int percentYear;

    private int percentMonth;

    private int percentWeek;

    public Double totalPrice(List<OrderEntity> listOrder) {
        double total = 0;
        for (OrderEntity orderEntity : listOrder) {
            total += orderEntity.getTotalAmount();
        }
        return total;
    }

    public long countOrder(List<OrderEntity> listOrder) {
        return listOrder.size();
    }
}
