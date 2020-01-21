package com.hechuan.waimai.dao;

import com.hechuan.waimai.dto.Order;
import com.hechuan.waimai.dto.OrderRequest;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderRequest record);

    int updateByPrimaryKey(Order record);


    List<Order> queryOrderList(OrderRequest order);

    Integer queryOrderCount(@Param("status") Integer status);
}