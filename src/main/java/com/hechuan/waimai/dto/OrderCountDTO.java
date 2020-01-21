package com.hechuan.waimai.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@ToString
public class OrderCountDTO {

    private Integer newOrderCount;

    private Integer doingOrderCount;

    private Integer doneOrderCount;


}
