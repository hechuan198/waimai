package com.hechuan.waimai.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hechuan.waimai.dao.OrderMapper;
import com.hechuan.waimai.dto.Order;
import com.hechuan.waimai.dto.OrderRequest;
import com.hechuan.waimai.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public PageInfo<Order> queryOrderList(OrderRequest order) {

        return PageHelper.startPage(order.getPageNum(),order.getPageSize()).doSelectPageInfo(() -> orderMapper.queryOrderList(order) );
    }
}
