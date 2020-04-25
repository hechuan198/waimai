package com.hechuan.waimai.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class UserReportDTO {

    /**
     * 月份
     */
    @ExcelProperty(value = "月份",index = 0)
    private String months;

    /**
     * 用户数量
     */
    @ExcelProperty(value = "用户数量",index = 1)
    private Integer userCount;

}
