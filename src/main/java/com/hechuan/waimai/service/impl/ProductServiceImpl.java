package com.hechuan.waimai.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hechuan.waimai.dao.ProductMapper;
import com.hechuan.waimai.dto.Product;
import com.hechuan.waimai.dto.ProductListRequest;
import com.hechuan.waimai.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    /**
     * 查询商品分页信息
     * @param productListRequest
     * @return
     */
    @Override
    public PageInfo<Product> queryProductList(ProductListRequest productListRequest) {

        PageInfo<Product> pageInfo =  PageHelper.startPage(productListRequest.getPageNum(),productListRequest.getPageSize()).doSelectPageInfo(() -> productMapper.queryProductList(productListRequest));
        log.info("【查询商品分页信息,返回结果】 = {}", JSON.toJSONString(pageInfo.getList()));
    return pageInfo;

    }
}
