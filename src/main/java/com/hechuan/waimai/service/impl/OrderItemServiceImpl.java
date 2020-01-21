package com.hechuan.waimai.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hechuan.waimai.dao.OrderItemMapper;
import com.hechuan.waimai.dto.Order;
import com.hechuan.waimai.dto.OrderItem;
import com.hechuan.waimai.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    private OrderItemMapper orderItemMapper;


    @Override
    public PageInfo<Order> queryOrderDetailList(OrderItem orderItem) {


        return PageHelper.startPage(orderItem.getPageNum(),orderItem.getPageSize()).doSelectPageInfo(() -> orderItemMapper.queryOrderDetailList(orderItem));
    }

}
