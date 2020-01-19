package com.hechuan.waimai.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@Data
@ToString
@NoArgsConstructor
public class OrderRequest {
    /**
     * 订单状态
     */
    private Integer status;


    private Integer pageNum;

    private Integer pageSize;

}