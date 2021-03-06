package com.hechuan.waimai.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@ToString
public class OrderCountDTO {

    private Integer newOrderCount;

    private Integer doingOrderCount;

    private Integer doneOrderCount;
    @ExcelProperty(value = "月份",index = 0)
    private String months;
    @ExcelProperty(value = "订单数",index = 1)
    private Integer orderCount;


}
