package com.hechuan.waimai.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.xml.stream.events.StartDocument;
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

    /**
     * 开始时间
     */
    private String startTime;
    /**
     * 结束时间
     */
    private String endTime;
    /**
     * 订单号
     */
    private Long orderNo;

    private Integer pageNum;

    private Integer pageSize;

}