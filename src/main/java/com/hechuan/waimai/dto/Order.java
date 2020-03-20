package com.hechuan.waimai.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;
@Data
@ToString
@NoArgsConstructor
public class Order {
    private String id;

    private String orderNo;

    private String userId;

    private BigDecimal payment;

    private Integer paymentType;

    private Integer postage;

    private String status;

    private String paymentTime;

    private String sendTime;

    private String endTime;

    private String closeTime;

    private String createTime;

    private String updateTime;

    private Integer pageNum;

    private Integer pageSize;

}