package com.hechuan.waimai.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

@Data
@ToString
@NoArgsConstructor
public class ProductListRequest {
    /**
     * 开始价格
     */
    @NotEmpty
    private BigDecimal startPrice;
    /**
     * 结束价格
     */

    private BigDecimal endPrice;
    /**
     * 类目
     */
    private Integer categoryId;
    /**
     * 是否上架
     */
    private Integer status;

    private Integer pageSize;

    private Integer pageNum;

}
