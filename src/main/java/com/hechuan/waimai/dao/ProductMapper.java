package com.hechuan.waimai.dao;

import com.hechuan.waimai.dto.Product;
import com.hechuan.waimai.dto.ProductRequest;
import com.hechuan.waimai.dto.ProductListRequest;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductRequest ProductRequest);

    int insertSelective(ProductRequest record);

    Product selectByPrimaryKey(Integer id);


    int updateByPrimaryKeySelective(ProductRequest record);

    int updateByPrimaryKey(ProductRequest record);

    /**
     * 根据分页信息查询list
     * @param productListRequest
     * @return
     */
    List<Product> queryProductList(ProductListRequest productListRequest);

    /**
     * 修改商品
     * @param productRequest
     */
    void updataProduct(ProductRequest productRequest);

    /**
     * 查询商品总数
     * @return
     */
    Integer queryProductCount();

    /**
     * 根据商品名查询商品
     * @param productRequest
     * @return
     */
    Product queryProduct(ProductRequest productRequest);
}