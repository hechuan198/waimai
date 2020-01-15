package com.hechuan.waimai.controller;

import com.github.pagehelper.PageInfo;
import com.hechuan.waimai.dto.Product;
import com.hechuan.waimai.dto.ProductListRequest;
import com.hechuan.waimai.service.ProductService;
import com.hechuan.waimai.service.impl.ProductServiceImpl;
import com.hechuan.waimai.util.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("product/")
public class ProductController {

    @Autowired
    private ProductService productService;

    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);


    @PostMapping("queryProductList")
    public ResultVO queryProductList(ProductListRequest productListRequest){
        log.info("【请求参数】 = {}",productListRequest);

        PageInfo<Product> productList = productService.queryProductList(productListRequest);

        return ResultVO.success(productList);
    }


}
