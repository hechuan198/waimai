package com.hechuan.waimai.service;

import com.github.pagehelper.PageInfo;
import com.hechuan.waimai.dto.Order;
import com.hechuan.waimai.dto.OrderItem;

public interface OrderItemService {

    PageInfo<Order> queryOrderDetailList(OrderItem orderItem);

}
