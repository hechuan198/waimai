package com.hechuan.waimai.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;
@Data
@ToString
@NoArgsConstructor
public class OrderItem {
    private String id;

    private String userId;

    private String orderNo;

    private String productId;

    private String productName;

    private String productImage;

    private BigDecimal currentUnitPrice;

    private Integer quantity;

    private BigDecimal totalPrice;

    private String receiverAddress;

    private String createTime;

    private String updateTime;

    private Integer pageNum;

    private Integer pageSize;
}