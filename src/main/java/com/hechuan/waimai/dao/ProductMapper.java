package com.hechuan.waimai.dao;

import com.hechuan.waimai.dto.ProductListRequest;
import com.neuedu.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer id);


    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    /**
     * 根据分页信息查询list
     * @param productListRequest
     * @return
     */
    List<Product> queryProductList(ProductListRequest productListRequest);
}