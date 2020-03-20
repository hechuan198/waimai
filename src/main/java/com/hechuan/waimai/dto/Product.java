package com.hechuan.waimai.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hechuan.waimai.util.VeDate;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
@Data
@NoArgsConstructor
@ToString
public class Product {
    private String id = "F" + VeDate.getStringId();

    private String categoryId;

    private String name;

    private String subtitle;

    private String mainImage;

    private String detail;

    private BigDecimal price;

    private String sellnum;

    private String hits;

    private Integer status;
//    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private String createTime;
//    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private String updateTime;

}