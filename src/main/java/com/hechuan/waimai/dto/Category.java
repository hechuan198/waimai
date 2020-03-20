package com.hechuan.waimai.dto;

import com.hechuan.waimai.util.VeDate;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
@Data
@ToString
@NoArgsConstructor
public class Category {
    private String id  = "C" + VeDate.getStringId();

    private String parentId;

    private String name;

    private Integer status;

    private Integer sortOrder;

    private String createTime;

    private String updateTime;


}