package com.hechuan.waimai.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class ProductReportDTO {

    @ExcelProperty(value = "菜品名",index = 0)
    private String name;
    @ExcelProperty(value = "销量",index = 1)
    private String sellnum;
}
