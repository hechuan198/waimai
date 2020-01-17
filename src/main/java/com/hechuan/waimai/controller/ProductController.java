package com.hechuan.waimai.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.hechuan.waimai.dto.Product;
import com.hechuan.waimai.dto.ProductRequest;
import com.hechuan.waimai.dto.ProductListRequest;
import com.hechuan.waimai.service.ProductService;
import com.hechuan.waimai.service.impl.ProductServiceImpl;
import com.hechuan.waimai.util.IsBigdecimal;
import com.hechuan.waimai.util.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("product/")
public class ProductController {

    @Autowired
    private ProductService productService;

    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    /**
     * 分页查询商品
     *
     * @param productListRequest
     * @return
     */
    @PostMapping("queryProductList")
    public ResultVO queryProductList(@RequestBody ProductListRequest productListRequest) {
        log.info("【请求参数】 = {}", productListRequest);

        PageInfo<Product> productList = productService.queryProductList(productListRequest);
        log.info("【分页查询商品结果，productList】 = {}",JSON.toJSONString(productList));
        return ResultVO.success(productList);
    }

    /**
     * 添加商品
     * @param productRequest
     * @return
     */
    @PostMapping("addProduct")
    public ResultVO addProduct(@Valid ProductRequest productRequest, BindingResult bindingResult){
        log.info("【添加商品，请求参数】 = {}", JSON.toJSONString(productRequest));
        if (bindingResult.hasErrors()){
            String message = bindingResult.getFieldError().getDefaultMessage();
            return ResultVO.error(message);
        }
        if (IsBigdecimal.isBigdecimal(productRequest.getPrice()) == false) {
            return ResultVO.error("商品价格格式不正确");
        }
        productService.addProduct(productRequest);
        return ResultVO.success("添加商品成功");
    }

    /**
     * 修改商品
     * @param productRequest
     * @return
     */
    @PostMapping("updataProduct")
    public ResultVO updataProduct(ProductRequest productRequest){

        log.info("【修改商品，请求参数】 = {}",JSON.toJSONString(productRequest));

        productService.updataProduct(productRequest);

        return ResultVO.success("商品改变状态成功");
    }

}
