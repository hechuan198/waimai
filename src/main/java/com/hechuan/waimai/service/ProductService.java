package com.hechuan.waimai.service;

import com.github.pagehelper.PageInfo;
import com.hechuan.waimai.dto.Product;
import com.hechuan.waimai.dto.ProductRequest;
import com.hechuan.waimai.dto.ProductListRequest;

public interface ProductService {

    PageInfo<Product> queryProductList(ProductListRequest productListRequest);

    void addProduct(ProductRequest productRequest);

    void updataProduct(ProductRequest productRequest);
}
