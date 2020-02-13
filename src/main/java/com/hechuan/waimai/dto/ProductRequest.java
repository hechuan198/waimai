package com.hechuan.waimai.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@ToString
public class ProductRequest {
    /**
     * 商品图片地址
     */
    @NotEmpty(message = "图片不能为空")
    private String mainImage;
    /**
     * 商品名称
     */
    @NotEmpty(message = "商品名称不能为空")
    private String name;
    /**
     * 分类
     */
    @NotEmpty(message = "分类不能为空")
    private Integer categoryId;
//    /**
//     * 商品副标题
//     */
//    @NotEmpty(message = "商品副标题不能为空")
//    private String subtitle;
    /**
     * 商品价格
     */
//    @Pattern(regexp = "^(([1-9]{1}\\d*)|(0{1}))(\\.\\d{1,2})?$", message = "商品价格格式不正确")
    private BigDecimal price;
    /**
     * 描述
     */
    @NotEmpty(message = "商品描述不能为空")
    private String detail;

    /**
     * 商品状态
     */
    private Integer status;

}
