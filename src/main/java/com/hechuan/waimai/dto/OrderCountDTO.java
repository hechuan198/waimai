package com.hechuan.waimai.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@ToString
public class OrderCountDTO {

    private Integer newOrderCount;

    private Integer doingOrderCount;

    private Integer doneOrderCount;

    private String months;

    private Integer orderCount;


}
