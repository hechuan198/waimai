package com.hechuan.waimai.dto;

import com.alibaba.excel.annotation.ExcelProperty;
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

    @ExcelProperty(value = "菜品名",index = 0)
    private String name;

    private String subtitle;

    private String mainImage;

    private String detail;

    private BigDecimal price;
    @ExcelProperty(value = "销量",index = 1)
    private String sellnum;

    private String hits;

    private Integer status;
//    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private String createTime;
//    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private String updateTime;

    private String category;

}