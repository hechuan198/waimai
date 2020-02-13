package com.hechuan.waimai.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hechuan.waimai.dao.ProductMapper;
import com.hechuan.waimai.dto.Product;
import com.hechuan.waimai.dto.ProductRequest;
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
     * 分页查询商品
     * @param productListRequest
     * @return
     */
    @Override
    public PageInfo<Product> queryProductList(ProductListRequest productListRequest) {

        PageInfo<Product> pageInfo =  PageHelper.startPage(productListRequest.getPageNum(),productListRequest.getPageSize()).doSelectPageInfo(() -> productMapper.queryProductList(productListRequest));
        // TODO: 2020/1/15 0015 增加类目递归
        log.info("【查询商品分页信息,返回结果】 = {}", JSON.toJSONString(pageInfo.getList()));
    return pageInfo;

    }

    /**
     * 添加商品
     * @param productRequest
     */
    @Override
    public void addProduct(ProductRequest productRequest) {

        try {
            productMapper.insertSelective(productRequest);
        } catch (Exception e) {
            log.error("【添加失败】= {}",e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 改变商品状态
     * @param productRequest
     */
    @Override
    public void updataProduct(ProductRequest productRequest) {

        productMapper.updataProduct(productRequest);


    }

    /**
     * 查询一个商品
     * @param productRequest
     * @return
     */
    @Override
    public Product queryProduct(ProductRequest productRequest) {

        Product product = productMapper.queryProduct(productRequest);

        return product;
    }
}
