package com.hechuan.waimai.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class CensusDTO {

    private Integer productCount;

    private Integer userCount;

    private Integer cateCount;

    private Integer orderCount;


}
